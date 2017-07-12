package com.nefee.prawn.web.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class PawnString {

    private String originalPawnString;
    private Double armor;
    private Double stamina;
    private Double intellect;
    private Double agility;
    private Double strength;
    private Double criticalStrike;
    private Double mastery;
    private Double haste;
    private Double versatility;

}
