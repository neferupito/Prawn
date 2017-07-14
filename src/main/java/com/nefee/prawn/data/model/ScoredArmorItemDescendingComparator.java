package com.nefee.prawn.data.model;

import com.nefee.prawn.data.entity.ScoredArmorItem;

import java.util.Comparator;
import java.util.Objects;

public class ScoredArmorItemDescendingComparator implements Comparator<ScoredArmorItem> {

    @Override
    public int compare(ScoredArmorItem o1, ScoredArmorItem o2) {
        if (Objects.equals(o1.getScore(), o2.getScore())) {
            return 0;
        } else if (o1.getScore() <
                o2.getScore()) {
            return 1;
        }
        return -1;
    }

}
