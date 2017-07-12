package com.nefee.prawn.logic.service;

import com.nefee.prawn.data.model.ArmorType;
import lombok.Getter;

public enum WowClassEnum {

    DEATHKNIGHT("Death Knight", "Chevalier de la Mort", ArmorType.PLATE, "ideathknights"),
    DEMONHUNTER("Demon Hunter", "Chasseur de Démons", ArmorType.LEATHER, "idemonhunters"),
    DRUID("Druid", "Druide", ArmorType.LEATHER, "idruids"),
    HUNTER("Hunter", "Chasseur", ArmorType.MAIL, "ihunters"),
    MAGE("Mage", "Mage", ArmorType.CLOTH, "images"),
    MONK("Monk", "Moine", ArmorType.LEATHER, "imonks"),
    PALADIN("Paladin", "Paladin", ArmorType.PLATE, "iplaladins"),
    PRIEST("Priest", "Prêtre", ArmorType.CLOTH, "ipriests"),
    ROGUE("Rogue", "Voleur", ArmorType.LEATHER, "irogues"),
    SHAMAN("Shaman", "Chaman", ArmorType.MAIL, "ishamans"),
    WARLOCK("Warlock", "Démoniste", ArmorType.CLOTH, "iwarlocks"),
    WARRIOR("Warrior", "Guerrier", ArmorType.PLATE, "iwarriors");

    @Getter
    private String nameEn;
    @Getter
    private String nameFr;
    @Getter
    private ArmorType armorType;
    @Getter
    private String iconName;

    WowClassEnum(String nameEn, String nameFr, ArmorType armorType, String iconName) {
        this.nameEn = nameEn;
        this.nameFr = nameFr;
        this.armorType = armorType;
        this.iconName = iconName;
    }

}
