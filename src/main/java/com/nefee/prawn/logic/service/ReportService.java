package com.nefee.prawn.logic.service;

import com.nefee.prawn.data.dao.ArmorItemRepository;
import com.nefee.prawn.data.entity.*;
import com.nefee.prawn.web.dto.response.ArmorItemDto;
import com.nefee.prawn.web.dto.response.RelicDto;
import com.nefee.prawn.web.dto.response.ReportWebDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ArmorItemRepository armorItemRepository;

    public ReportWebDto transformReportDto(Report report) {
        List<RelicDto> relics1 = new ArrayList<>(report.getArmorSet().getRelics1().size());
        List<RelicDto> relics2 = new ArrayList<>(report.getArmorSet().getRelics2().size());
        List<RelicDto> relics3 = new ArrayList<>(report.getArmorSet().getRelics3().size());

        report.getArmorSet().getRelics1().forEach(relic -> relics1.add(RelicDto.builder()
                .wowheadUrl(buildWowheadUrl(relic, report.getWowSpec().getWowId()))
                .bossWowheadUrl(buildBossWowheadUrl(relic.getBoss()))
                .name(relic.getName())
                .build()));

        report.getArmorSet().getRelics2().forEach(relic -> relics2.add(RelicDto.builder()
                .wowheadUrl(buildWowheadUrl(relic, report.getWowSpec().getWowId()))
                .bossWowheadUrl(buildBossWowheadUrl(relic.getBoss()))
                .name(relic.getName())
                .build()));
        report.getArmorSet().getRelics3().forEach(relic -> relics3.add(RelicDto.builder()
                .wowheadUrl(buildWowheadUrl(relic, report.getWowSpec().getWowId()))
                .bossWowheadUrl(buildBossWowheadUrl(relic.getBoss()))
                .name(relic.getName())
                .build()));

        ArmorSet armorSet = report.getArmorSet();
        return ReportWebDto.builder()
                .head(ArmorItemDto.builder()
                        .wowheadUrl(buildWowheadUrl(armorSet.getHead(), report.getWowSpec().getWowId()))
                        .name(armorSet.getHead().getName())
                        .bossWowheadUrl(buildBossWowheadUrl(armorSet.getHead().getBoss()))
                        .build())
                .neck(ArmorItemDto.builder()
                        .wowheadUrl(buildWowheadUrl(armorSet.getNeck(), report.getWowSpec().getWowId()))
                        .name(armorSet.getNeck().getName())
                        .bossWowheadUrl(buildBossWowheadUrl(armorSet.getNeck().getBoss()))
                        .build())
                .shoulders(ArmorItemDto.builder()
                        .wowheadUrl(buildWowheadUrl(armorSet.getShoulders(), report.getWowSpec().getWowId()))
                        .name(armorSet.getShoulders().getName())
                        .bossWowheadUrl(buildBossWowheadUrl(armorSet.getShoulders().getBoss()))
                        .build())
                .back(ArmorItemDto.builder()
                        .wowheadUrl(buildWowheadUrl(armorSet.getBack(), report.getWowSpec().getWowId()))
                        .name(armorSet.getBack().getName())
                        .bossWowheadUrl(buildBossWowheadUrl(armorSet.getBack().getBoss()))
                        .build())
//                .chest(ArmorItemDto.builder()
//                        .wowheadUrl(buildWowheadUrl(armorSet.getChest(), report.getWowSpec().getWowId()))
//                        .name(armorSet.getChest().getName())
//                        .bossWowheadUrl(buildBossWowheadUrl(armorSet.getChest().getBoss()))
//                        .build())
//                .wrists(ArmorItemDto.builder()
//                        .wowheadUrl(buildWowheadUrl(armorSet.getWrists(), report.getWowSpec().getWowId()))
//                        .name(armorSet.getWrists().getName())
//                        .bossWowheadUrl(buildBossWowheadUrl(armorSet.getWrists().getBoss()))
//                        .build())
//                .hands(ArmorItemDto.builder()
//                        .wowheadUrl(buildWowheadUrl(armorSet.getHands(), report.getWowSpec().getWowId()))
//                        .name(armorSet.getHands().getName())
//                        .bossWowheadUrl(buildBossWowheadUrl(armorSet.getHands().getBoss()))
//                        .build())
//                .waist(ArmorItemDto.builder()
//                        .wowheadUrl(buildWowheadUrl(armorSet.getWaist(), report.getWowSpec().getWowId()))
//                        .name(armorSet.getWaist().getName())
//                        .bossWowheadUrl(buildBossWowheadUrl(armorSet.getWaist().getBoss()))
//                        .build())
//                .legs(ArmorItemDto.builder()
//                        .wowheadUrl(buildWowheadUrl(armorSet.getLegs(), report.getWowSpec().getWowId()))
//                        .name(armorSet.getLegs().getName())
//                        .bossWowheadUrl(buildBossWowheadUrl(armorSet.getLegs().getBoss()))
//                        .build())
//                .feet(ArmorItemDto.builder()
//                        .wowheadUrl(buildWowheadUrl(armorSet.getFeet(), report.getWowSpec().getWowId()))
//                        .name(armorSet.getFeet().getName())
//                        .bossWowheadUrl(buildBossWowheadUrl(armorSet.getFeet().getBoss()))
//                        .build())
//                .ring1(ArmorItemDto.builder()
//                        .wowheadUrl(buildWowheadUrl(armorSet.getRing1(), report.getWowSpec().getWowId()))
//                        .name(armorSet.getRing1().getName())
//                        .bossWowheadUrl(buildBossWowheadUrl(armorSet.getRing1().getBoss()))
//                        .build())
//                .ring2(ArmorItemDto.builder()
//                        .wowheadUrl(buildWowheadUrl(armorSet.getRing2(), report.getWowSpec().getWowId()))
//                        .name(armorSet.getRing2().getName())
//                        .bossWowheadUrl(buildBossWowheadUrl(armorSet.getRing2().getBoss()))
//                        .build())
                .relicType1(report.getWowSpec().getRelic1())
                .relics1(relics1)
                .relicType2(report.getWowSpec().getRelic2())
                .relics2(relics2)
                .relicType3(report.getWowSpec().getRelic3())
                .relics3(relics3)
                .build();
    }

    private String buildWowheadUrl(ArmorItem armorItem, Integer wowSpecId) {
        if (armorItem != null) {
            return "http://www.wowhead.com/item=" + armorItem.getWowId() + "/" + armorItem.getName().toLowerCase().replace(" ", "-") + "&bonus=" + armorItem.getBoss().getLootBonusId() + "&spec=" + wowSpecId;
        }
        return null;
    }

    private String buildWowheadUrl(Relic relic, Integer wowSpecId) {
        return "http://www.wowhead.com/item=" + relic.getWowId() + "/" + relic.getName().toLowerCase().replace(" ", "-") + "&bonus=" + relic.getBoss().getLootBonusId() + "&spec=" + wowSpecId;
    }

    private String buildBossWowheadUrl(Boss boss) {
        return "http://www.wowhead.com/npc=" + boss.getWowId();
    }

}
