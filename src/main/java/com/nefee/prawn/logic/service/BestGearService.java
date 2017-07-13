package com.nefee.prawn.logic.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nefee.prawn.data.dao.*;
import com.nefee.prawn.data.entity.*;
import com.nefee.prawn.data.model.ArmorType;
import com.nefee.prawn.data.model.RelicType;
import com.nefee.prawn.data.model.Slot;
import com.nefee.prawn.web.dto.request.PawnString;
import com.nefee.prawn.web.dto.request.WishRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BestGearService {

    @Autowired
    private ArmorItemRepository armorItemRepository;
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private RelicRepository relicDao;
    @Autowired
    private ArmorSetRepository armorSetRepository;
    @Autowired
    private ScoredArmorItemRepository scoredArmorItemRepository;
    @Autowired
    private DetailedReportRepository detailedReportRepository;
    @Autowired
    private DetailedArmorSetRepository detailedArmorSetRepository;

    private WishRequest request;
    private Gson gson;

    private List<DetailedArmorSet> detailedArmorSets;

    public String findBestGear(WishRequest request) {

        gson = new GsonBuilder().setPrettyPrinting().create();
        this.request = request;
        detailedArmorSets = new ArrayList<>();

        WowClass wowClass = request.getWowSpec().getWowClass();
        ArmorType armorType = wowClass.getArmorType();

        ArmorSet bis = ArmorSet.builder()
                .head(findBestItem(armorType, Slot.HEAD))
                .neck(findBestItem(ArmorType.ACCESSORY, Slot.NECK))
                .shoulders(findBestItem(armorType, Slot.SHOULDERS))
                .back(findBestItem(ArmorType.ACCESSORY, Slot.BACK))
                .chest(findBestItem(armorType, Slot.CHEST))
                .wrists(findBestItem(armorType, Slot.WRISTS))
                .hands(findBestItem(armorType, Slot.HANDS))
                .waist(findBestItem(armorType, Slot.WAIST))
                .legs(findBestItem(armorType, Slot.LEGS))
                .feet(findBestItem(armorType, Slot.FEET))
                .relics1(findRelicsForType(request.getWowSpec().getRelic1()))
                .relics2(findRelicsForType(request.getWowSpec().getRelic2()))
                .relics3(findRelicsForType(request.getWowSpec().getRelic3()))
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
                .title(request.getTitle())
                .wowSpec(request.getWowSpec())
                .armorSet(bis)
                .pawnstring(request.getPawnString().getOriginalPawnString())
                .build());

        DetailedReport detailedReport = detailedReportRepository.save(DetailedReport.builder()
                .detailedArmorSets(detailedArmorSets)
                .reportId(reportId)
                .build());
        System.out.println("\n\n" + gson.toJson(detailedReport) + "\n\n");
        System.out.println("\n\n" + gson.toJson(report) + "\n\n");


        return reportId;
    }

    private ArmorItem findBestItem(ArmorType armorType, Slot slot) {

        List<ArmorItem> armorItemEntities = armorItemRepository.findBySlot(slot);
        List<ScoredArmorItem> scoredArmorItems = new ArrayList<>(armorItemEntities.size());

        if (!armorItemEntities.isEmpty()) {
            ArmorItem bis = null;
            double bestScore = 0.0;
            for (ArmorItem item : armorItemEntities) {
                if (item.getArmorType().equals(armorType)) {

                    double score = calculateScore(request.getPawnString(), item);
                    if (score > bestScore) {
                        bestScore = score;
                        bis = item;
                    }

                    ScoredArmorItem scoredArmorItem = scoredArmorItemRepository.save(ScoredArmorItem.builder().score(score).armorItem(item).build());
                    scoredArmorItems.add(scoredArmorItem);
                }
            }

            detailedArmorSets.add(detailedArmorSetRepository.save(
                    DetailedArmorSet.builder()
                            .slot(slot)
                            .scoredArmorItems(scoredArmorItems)
                            .build()));

            return bis;

        } else {
            return null;
        }
    }

    private ArmorItem[] findBestRings() {

        List<ArmorItem> armorItemEntities = armorItemRepository.findBySlot(Slot.RING);
        List<ScoredArmorItem> scoredArmorItems = new ArrayList<>(armorItemEntities.size());

        if (!armorItemEntities.isEmpty()) {
            ArmorItem[] bis = new ArmorItem[2];
            double bestScore = 0.0;

            Map<Double, ArmorItem> rings = new TreeMap<>();

            for (ArmorItem item : armorItemEntities) {
                double score = calculateScore(request.getPawnString(), item);
                rings.put(score, item);
                ScoredArmorItem scoredArmorItem = scoredArmorItemRepository.save(ScoredArmorItem.builder().score(score).armorItem(item).build());
                scoredArmorItems.add(scoredArmorItem);
            }

            int i = rings.size();
            System.out.println("=== Content of Rings ===");
            for (Map.Entry<Double, ArmorItem> entry : rings.entrySet()) {
                if (i == 2) {
                    bis[1] = entry.getValue();
                }
                if (i == 1) {
                    bis[0] = entry.getValue();
                }
                System.out.println(i-- + " " + entry.getKey() + " " + entry.getValue().getName());
            }
            System.out.println("========================");

            detailedArmorSets.add(detailedArmorSetRepository.save(
                    DetailedArmorSet.builder()
                            .slot(Slot.RING)
                            .scoredArmorItems(scoredArmorItems)
                            .build()));

            int y = 0;
            for (ArmorItem item : bis) {
                System.out.println(y++ + " " + item.getName());
            }

            return bis;

        } else {
            return null;
        }
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

    private List<Relic> findRelicsForType(RelicType type) {
        return relicDao.findByRelicType(type);
    }


}
