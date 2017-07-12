package com.nefee.prawn.logic.service;

import com.nefee.prawn.data.dao.ArmorItemRepository;
import com.nefee.prawn.data.dao.ArmorSetRepository;
import com.nefee.prawn.data.dao.RelicRepository;
import com.nefee.prawn.data.dao.ReportRepository;
import com.nefee.prawn.data.entity.*;
import com.nefee.prawn.data.model.ArmorType;
import com.nefee.prawn.data.model.RelicType;
import com.nefee.prawn.data.model.Slot;
import com.nefee.prawn.web.dto.request.PawnString;
import com.nefee.prawn.web.dto.request.WishRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    private WishRequest request;

    public String findBestGear(WishRequest request) {
        this.request = request;

        System.err.println(request.toString());
        WowClass wowClass = request.getWowSpec().getWowClass();
        System.err.println(wowClass.getName());
        System.err.println(wowClass.getArmorType());
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

//        http://www.wowhead.com/item=147063/armet-of-the-rotten-mind&bonus=3562:1497&spec=250
        bis.setHeadWowheadUrl("http://www.wowhead.com/item=" + bis.getHead().getWowId() + "/bonus=" + bis.getHead().getBoss().getLootBonusId() + "&spec=" + request.getWowSpec().getWowId());
        bis.setNeckWowheadUrl("http://www.wowhead.com/item=" + bis.getNeck().getWowId() + "/bonus=" + bis.getNeck().getBoss().getLootBonusId() + "&spec=" + request.getWowSpec().getWowId());

        System.out.println(bis.toString());

        bis = armorSetRepository.save(bis);

        String reportId = UUID.randomUUID().toString();

        reportRepository.save(Report.builder()
                .reportId(reportId)
                .title(request.getTitle())
                .wowSpec(request.getWowSpec())
                .armorSet(bis)
                .pawnstring(request.getPawnString().getOriginalPawnString())
                .build());

        return reportId;
    }

    private ArmorItem findBestItem(ArmorType armorType, Slot slot) {
        List<ArmorItem> armorItemEntities = armorItemRepository.findBySlot(slot);
        if (!armorItemEntities.isEmpty()) {
            ArmorItem bis = null;
            double bestScore = 0.0;

            for (ArmorItem item : armorItemEntities) {
                double score = calculateScore(request.getPawnString(), item);
                if (score > bestScore) {
                    bis = item;
                }
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

        System.err.println(item.getName() + "got score " + score);

        return score;
    }

    private List<Relic> findRelicsForType(RelicType type) {
        return relicDao.findByRelicType(type);
    }

}
