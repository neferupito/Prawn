package com.nefee.prawn.logic.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nefee.prawn.data.dao.*;
import com.nefee.prawn.data.entity.*;
import com.nefee.prawn.data.model.ArmorType;
import com.nefee.prawn.data.model.RelicType;
import com.nefee.prawn.data.model.ScoredArmorItemDescendingComparator;
import com.nefee.prawn.data.model.Slot;
import com.nefee.prawn.web.dto.request.PawnString;
import com.nefee.prawn.web.dto.request.WishRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FindMyBestInSlotsService {

    @Autowired
    private ArmorItemRepository armorItemRepository;
    @Autowired
    private RelicRepository relicRepository;
    @Autowired
    private ArmorSetRepository armorSetRepository;
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private DetailedReportRepository detailedReportRepository;
    @Autowired
    private DetailedArmorSetRepository detailedArmorSetRepository;
    @Autowired
    private ScoredArmorItemRepository scoredArmorItemRepository;

    private List<ScoredArmorItem> headItems;
    private List<ScoredArmorItem> neckItems;
    private List<ScoredArmorItem> shouldersItems;
    private List<ScoredArmorItem> backItems;
    private List<ScoredArmorItem> chestItems;
    private List<ScoredArmorItem> wristsItems;
    private List<ScoredArmorItem> handsItems;
    private List<ScoredArmorItem> waistItems;
    private List<ScoredArmorItem> legsItems;
    private List<ScoredArmorItem> feetItems;
    private List<ScoredArmorItem> ringItems;
    private List<ScoredArmorItem> classSetItems;

    private ScoredArmorItem[] headItemsSorted;
    private ScoredArmorItem[] neckItemsSorted;
    private ScoredArmorItem[] shouldersItemsSorted;
    private ScoredArmorItem[] backItemsSorted;
    private ScoredArmorItem[] chestItemsSorted;
    private ScoredArmorItem[] wristsItemsSorted;
    private ScoredArmorItem[] handsItemsSorted;
    private ScoredArmorItem[] waistItemsSorted;
    private ScoredArmorItem[] legsItemsSorted;
    private ScoredArmorItem[] feetItemsSorted;
    private ScoredArmorItem[] ringItemsSorted;

    private List<DetailedArmorSet> detailedArmorSets;


    private Map<Slot, ScoredArmorItem> temporaryMap;
    private int numberEnsemble;
    private int requiredNumberEnsemble;
    private Comparator<ScoredArmorItem> comparator;

    private WishRequest wishRequest;

    private Gson gson;

    public String findBestInSlots(WishRequest wishRequest) {
        gson = new GsonBuilder().setPrettyPrinting().create();
        this.wishRequest = wishRequest;
        comparator = new ScoredArmorItemDescendingComparator();

        requiredNumberEnsemble = 0;

        loadLists();

        if (putInMapFirstParse(headItemsSorted)) {
            numberEnsemble++;
        }
        if (putInMapFirstParse(neckItemsSorted)) {
            numberEnsemble++;
        }
        if (putInMapFirstParse(shouldersItemsSorted)) {
            numberEnsemble++;
        }
        if (putInMapFirstParse(backItemsSorted)) {
            numberEnsemble++;
        }
        if (putInMapFirstParse(chestItemsSorted)) {
            numberEnsemble++;
        }
        if (putInMapFirstParse(wristsItemsSorted)) {
            numberEnsemble++;
        }
        if (putInMapFirstParse(handsItemsSorted)) {
            numberEnsemble++;
        }
        if (putInMapFirstParse(waistItemsSorted)) {
            numberEnsemble++;
        }
        if (putInMapFirstParse(legsItemsSorted)) {
            numberEnsemble++;
        }
        if (putInMapFirstParse(feetItemsSorted)) {
            numberEnsemble++;
        }

        if (wishRequest.is4PiecesRequired()) {
            requiredNumberEnsemble = 4;
        } else if (wishRequest.is2PiecesRequired()) {
            requiredNumberEnsemble = 2;
        }

        if (numberEnsemble < requiredNumberEnsemble) {
            calculateDifference();
        }

        return buildReport();
    }

    private String buildReport() {
        ArmorSet bis = ArmorSet.builder()
                .head(temporaryMap.get(Slot.HEAD).getArmorItem())
                .neck(temporaryMap.get(Slot.NECK).getArmorItem())
                .shoulders(temporaryMap.get(Slot.SHOULDERS).getArmorItem())
                .back(temporaryMap.get(Slot.BACK).getArmorItem())
                .chest(temporaryMap.get(Slot.CHEST).getArmorItem())
                .wrists(temporaryMap.get(Slot.WRISTS).getArmorItem())
                .hands(temporaryMap.get(Slot.HANDS).getArmorItem())
                .waist(temporaryMap.get(Slot.WAIST).getArmorItem())
                .legs(temporaryMap.get(Slot.LEGS).getArmorItem())
                .feet(temporaryMap.get(Slot.FEET).getArmorItem())
                .relics1(findRelicsForType(wishRequest.getWowSpec().getRelic1()))
                .relics2(findRelicsForType(wishRequest.getWowSpec().getRelic2()))
                .relics3(findRelicsForType(wishRequest.getWowSpec().getRelic3()))
                .build();

        ArmorItem[] rings = findBestRings();
        if (rings != null) {
            bis.setRing1(rings[0]);
            bis.setRing2(rings[1]);
        }

        bis = armorSetRepository.save(bis);
        String reportId = UUID.randomUUID().toString();

        Report report = reportRepository.save(Report.builder()
                .reportId(reportId)
                .title(wishRequest.getTitle())
                .wowSpec(wishRequest.getWowSpec())
                .armorSet(bis)
                .pawnstring(wishRequest.getPawnString().getOriginalPawnString())
                .build());

        DetailedReport detailedReport = detailedReportRepository.save(DetailedReport.builder()
                .detailedArmorSets(detailedArmorSets)
                .reportId(reportId)
                .build());
        System.out.println("\n\n" + gson.toJson(detailedReport) + "\n\n");
        System.out.println("\n\n" + gson.toJson(report) + "\n\n");

        return reportId;
    }

    private void calculateDifference() {
        Map<Double, ScoredArmorItem> differencesMap = new HashMap<>();

        for (ScoredArmorItem item : classSetItems) {
            double scoreItem = item.getScore();
            double selectedItem = temporaryMap.get(item.getArmorItem().getSlot()).getScore();
            double diff = selectedItem - scoreItem;
            differencesMap.put(diff, item);
        }

        List<Double> diffs = new ArrayList<>(differencesMap.keySet());
        Collections.sort(diffs);

        for (Double difference : diffs) {
            if (numberEnsemble < requiredNumberEnsemble) {
                ScoredArmorItem item = differencesMap.get(difference);
                temporaryMap.put(item.getArmorItem().getSlot(), item);
                numberEnsemble++;
            }
        }
    }

    private boolean putInMapFirstParse(ScoredArmorItem[] armorItems) {
        temporaryMap.put(armorItems[0].getArmorItem().getSlot(), armorItems[0]);
        if (armorItems[0].getArmorItem().isSetPart()) {
            classSetItems.remove(armorItems[0]);
            return true;
        }
        return false;
    }

    private void loadLists() {
        classSetItems = new ArrayList<>();
        ArmorType armorType = wishRequest.getWowSpec().getWowClass().getArmorType();

        headItems = calculateScoresBySlot(armorType, Slot.HEAD);
        neckItems = calculateScoresBySlot(ArmorType.ACCESSORY, Slot.NECK);
        shouldersItems = calculateScoresBySlot(armorType, Slot.SHOULDERS);
        backItems = calculateScoresBySlot(ArmorType.ACCESSORY, Slot.BACK);
        chestItems = calculateScoresBySlot(armorType, Slot.CHEST);
        wristsItems = calculateScoresBySlot(armorType, Slot.WRISTS);
        handsItems = calculateScoresBySlot(armorType, Slot.HANDS);
        waistItems = calculateScoresBySlot(armorType, Slot.WAIST);
        legsItems = calculateScoresBySlot(armorType, Slot.LEGS);
        feetItems = calculateScoresBySlot(armorType, Slot.FEET);

        headItems.sort(comparator);
        neckItems.sort(comparator);
        shouldersItems.sort(comparator);
        backItems.sort(comparator);
        chestItems.sort(comparator);
        wristsItems.sort(comparator);
        handsItems.sort(comparator);
        waistItems.sort(comparator);
        legsItems.sort(comparator);
        feetItems.sort(comparator);
        classSetItems.sort(comparator);

//        headItemsSorted = new ScoredArmorItem[headItems.size()];
//        neckItemsSorted = new ScoredArmorItem[neckItems.size()];
//        shouldersItemsSorted = new ScoredArmorItem[shouldersItems.size()];
//        backItemsSorted = new ScoredArmorItem[backItems.size()];
//        chestItemsSorted = new ScoredArmorItem[chestItems.size()];
//        wristsItemsSorted = new ScoredArmorItem[wristsItems.size()];
//        handsItemsSorted = new ScoredArmorItem[handsItems.size()];
//        waistItemsSorted = new ScoredArmorItem[waistItems.size()];
//        legsItemsSorted = new ScoredArmorItem[legsItems.size()];
//        feetItemsSorted = new ScoredArmorItem[feetItems.size()];

        headItemsSorted = (ScoredArmorItem[]) headItems.toArray();
        neckItemsSorted = (ScoredArmorItem[]) neckItems.toArray();
        shouldersItemsSorted = (ScoredArmorItem[]) shouldersItems.toArray();
        backItemsSorted = (ScoredArmorItem[]) backItems.toArray();
        chestItemsSorted = (ScoredArmorItem[]) chestItems.toArray();
        wristsItemsSorted = (ScoredArmorItem[]) wristsItems.toArray();
        handsItemsSorted = (ScoredArmorItem[]) handsItems.toArray();
        waistItemsSorted = (ScoredArmorItem[]) waistItems.toArray();
        legsItemsSorted = (ScoredArmorItem[]) legsItems.toArray();
        feetItemsSorted = (ScoredArmorItem[]) feetItems.toArray();
    }

    private List<ScoredArmorItem> calculateScoresBySlot(ArmorType armorType, Slot slot) {
        List<ArmorItem> armorItemEntities = armorItemRepository.findBySlot(slot);
        List<ScoredArmorItem> scoredArmorItems = new ArrayList<>(armorItemEntities.size());

        if (!armorItemEntities.isEmpty()) {
            for (ArmorItem item : armorItemEntities) {
                double score = calculateScore(wishRequest.getPawnString(), item);
                ScoredArmorItem scoredArmorItem = ScoredArmorItem.builder().score(score).armorItem(item).build();
                scoredArmorItem = scoredArmorItemRepository.save(scoredArmorItem);
                scoredArmorItems.add(scoredArmorItem);
                if (item.isSetPart() && item.getClassArmorSet().getWowClass().equals(wishRequest.getWowSpec().getWowClass())) {
                    classSetItems.add(scoredArmorItem);
                }
            }

        detailedArmorSets.add(detailedArmorSetRepository.save(
                DetailedArmorSet.builder()
                        .slot(slot)
                        .scoredArmorItems(scoredArmorItems)
                        .build()));
        }

        return scoredArmorItems;

    }

    private double calculateScore(PawnString pawnString, ArmorItem item) {
        double score = 0.0;

        score = score + item.getArmor().doubleValue() * pawnString.getArmor();
        score = score + item.getStamina().doubleValue() * pawnString.getStamina();

        score = score + item.getIntellect().doubleValue() * pawnString.getIntellect();
        score = score + item.getAgility().doubleValue() * pawnString.getAgility();
        score = score + item.getStrength().doubleValue() * pawnString.getStrength();

        score = score + item.getCriticalStrike().doubleValue() * pawnString.getCriticalStrike();
        score = score + item.getHaste().doubleValue() * pawnString.getHaste();
        score = score + item.getMastery().doubleValue() * pawnString.getMastery();
        score = score + item.getVersatility().doubleValue() * pawnString.getVersatility();

        return score;
    }

    private ArmorItem[] findBestRings() {

        ringItems = calculateScoresBySlot(ArmorType.ACCESSORY, Slot.RING);

        if (!ringItems.isEmpty()) {

            ringItems.sort(comparator);
            ringItemsSorted = (ScoredArmorItem[]) ringItems.toArray();

            ArmorItem[] bis = new ArmorItem[2];
            bis[0] = ringItemsSorted[0].getArmorItem();
            bis[1] = ringItemsSorted[1].getArmorItem();

            detailedArmorSets.add(detailedArmorSetRepository.save(
                    DetailedArmorSet.builder()
                            .slot(Slot.RING)
                            .scoredArmorItems(ringItems)
                            .build()));
            return bis;
        }

        return null;
    }

    private List<Relic> findRelicsForType(RelicType type) {
        return relicRepository.findByRelicType(type);
    }
}
