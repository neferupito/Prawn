package com.nefee.prawn.logic.service;

import com.nefee.prawn.data.dao.WowSpecRepository;
import com.nefee.prawn.data.entity.WowSpec;
import com.nefee.prawn.web.dto.request.BiSRequest;
import com.nefee.prawn.web.dto.request.PawnString;
import com.nefee.prawn.web.dto.request.WishRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransformerService {

    @Autowired
    private WowSpecRepository wowSpecRepository;

    public WishRequest transformRequest(BiSRequest biSRequest) {

        String pawnString = biSRequest.getPawnstring();
        double criticRating = pawnStringParser(pawnString, "CritRating");
        double hasteRating = pawnStringParser(pawnString, "HasteRating");
        double masteryRating = pawnStringParser(pawnString, "MasteryRating");
        double versatilityRating = pawnStringParser(pawnString, "Versatility");
        double intellectRating = pawnStringParser(pawnString, "Intellect");
        double agilityRating = pawnStringParser(pawnString, "Agility");
        double strengthRating = pawnStringParser(pawnString, "Strength");
        double staminaRating = pawnStringParser(pawnString, "Stamina");
        double armorRating = pawnStringParser(pawnString, "Armor");

        return WishRequest.builder()
                .title(findTitle(pawnString))
                .wowSpec(wowSpecRepository.findByWowId(biSRequest.getWowSpecId()))
                .pawnString(PawnString.builder()
                        .originalPawnString(pawnString)
                        .armor(armorRating)
                        .stamina(staminaRating)
                        .intellect(intellectRating)
                        .agility(agilityRating)
                        .strength(strengthRating)
                        .criticalStrike(criticRating)
                        .haste(hasteRating)
                        .mastery(masteryRating)
                        .versatility(versatilityRating)
                        .build())
                .build();
    }

    private WowSpec findWowSpec(Integer wowSpecId) {
        return wowSpecRepository.findByWowId(wowSpecId);
    }

    private String findTitle(String pawnString) {
        String[] parts = pawnString.split("\"");
        return parts[1];
    }

    private double pawnStringParser(String pawnString, String ratingKey) {
        pawnString = pawnString.replace("(", "");
        pawnString = pawnString.replace(")", "");
        pawnString = pawnString.replace(" ", "");

        String[] parts = pawnString.split(ratingKey + "=");
        if (parts.length == 1) {
            return 0.0;
        }
        String[] parts2 = parts[1].split(",");
        return Double.parseDouble(parts2[0]);
    }

}
