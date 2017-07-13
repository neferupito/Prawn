package com.nefee.prawn.logic.service;


import com.nefee.prawn.data.model.RelicType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public enum WowSpecEnum {

    DK_BLOOD(WowClassEnum.DEATHKNIGHT, "Blood", "Sang", "250", RelicType.BLOOD, RelicType.SHADOW, RelicType.IRON),
    DK_FROST(WowClassEnum.DEATHKNIGHT, "Frost", "Givre", "251", RelicType.FROST, RelicType.SHADOW, RelicType.FROST),
    DK_UNHOLY(WowClassEnum.DEATHKNIGHT, "Unholy", "Impie", "252", RelicType.FIRE, RelicType.SHADOW, RelicType.BLOOD),

    DH_HAVOC(WowClassEnum.DEMONHUNTER, "Havoc", "Dévastation", "577", RelicType.FEL, RelicType.SHADOW, RelicType.FEL),
    DH_VENGEANCE(WowClassEnum.DEMONHUNTER, "Vengeance", "Vengeance", "581", RelicType.IRON, RelicType.ARCANE, RelicType.FEL),

    DRUID_BALANCE(WowClassEnum.DRUID, "Balance", "Equilibre", "102", RelicType.ARCANE, RelicType.LIFE, RelicType.ARCANE),
    DRUID_FERAL(WowClassEnum.DRUID, "Feral", "Farouche", "103", RelicType.FROST, RelicType.BLOOD, RelicType.LIFE),
    DRUID_GUARDIAN(WowClassEnum.DRUID, "Guardian", "Gardien", "104", RelicType.FIRE, RelicType.BLOOD, RelicType.LIFE),
    DRUID_RESTORATION(WowClassEnum.DRUID, "Restoration", "Restauration", "105", RelicType.LIFE, RelicType.FROST, RelicType.LIFE),

    HUNTER_BEASTMASTERY(WowClassEnum.HUNTER, "Beast Master", "Maîtrise des Bêtes", "253", RelicType.STORM, RelicType.ARCANE, RelicType.IRON),
    HUNTER_MARKSMANSHIP(WowClassEnum.HUNTER, "Marksman", "Précision", "254", RelicType.STORM, RelicType.BLOOD, RelicType.LIFE),
    HUNTER_SURVIVAL(WowClassEnum.HUNTER, "Survival", "Survie", "255", RelicType.STORM, RelicType.IRON, RelicType.BLOOD),

    MAGE_ARCANE(WowClassEnum.MAGE, "Arcane", "Arcanes", "62", RelicType.ARCANE, RelicType.FROST, RelicType.ARCANE),
    MAGE_FIRE(WowClassEnum.MAGE, "Fire", "Feu", "63", RelicType.FIRE, RelicType.ARCANE, RelicType.FIRE),
    MAGE_FROST(WowClassEnum.MAGE, "Frost", "Givre", "64", RelicType.FROST, RelicType.ARCANE, RelicType.FROST),

    MONK_BREWMASTER(WowClassEnum.MONK, "Brewmaster", "Maître Brasseur", "268", RelicType.LIFE, RelicType.STORM, RelicType.IRON),
    MONK_WINDWALKER(WowClassEnum.MONK, "Windwalker", "Marche-Vent", "269", RelicType.STORM, RelicType.IRON, RelicType.STORM),
    MONK_MISTWEAVER(WowClassEnum.MONK, "Mistweaver", "Tisse-Brume", "270", RelicType.FROST, RelicType.LIFE, RelicType.STORM),

    PALADIN_HOLY(WowClassEnum.PALADIN, "Holy", "Sacré", "65", RelicType.HOLY, RelicType.LIFE, RelicType.HOLY),
    PALADIN_PROTECTION(WowClassEnum.PALADIN, "Protection", "Protection", "66", RelicType.HOLY, RelicType.IRON, RelicType.ARCANE),
    PALADIN_RETRIBUTION(WowClassEnum.PALADIN, "Retribution", "Vindicte", "70", RelicType.HOLY, RelicType.FIRE, RelicType.HOLY),

    PRIEST_DISCIPLINE(WowClassEnum.PRIEST, "Discipline", "Discipline", "256", RelicType.HOLY, RelicType.SHADOW, RelicType.HOLY),
    PRIEST_HOLY(WowClassEnum.PRIEST, "Holy", "Sacré", "257", RelicType.HOLY, RelicType.LIFE, RelicType.HOLY),
    PRIEST_SHADOW(WowClassEnum.PRIEST, "Shadow", "Ombre", "258", RelicType.SHADOW, RelicType.BLOOD, RelicType.SHADOW),

    ROGUE_ASSASSINATION(WowClassEnum.ROGUE, "Assassination", "Assassinat", "259", RelicType.SHADOW, RelicType.IRON, RelicType.BLOOD),
    ROGUE_SUBTLETY(WowClassEnum.ROGUE, "Subtlety", "Finesse", "260", RelicType.FEL, RelicType.SHADOW, RelicType.FEL),
    ROGUE_OUTLAW(WowClassEnum.ROGUE, "Outlaw", "Hors la Loi", "261", RelicType.BLOOD, RelicType.IRON, RelicType.STORM),

    SHAMAN_ELEMENTAL(WowClassEnum.SHAMAN, "Elemental", "Elementaire", "262", RelicType.STORM, RelicType.FROST, RelicType.STORM),
    SHAMAN_ENHANCEMENT(WowClassEnum.SHAMAN, "Enhancement", "Amélioration", "263", RelicType.FIRE, RelicType.IRON, RelicType.STORM),
    SHAMAN_RESTORATION(WowClassEnum.SHAMAN, "Restoration", "Restauration", "264", RelicType.LIFE, RelicType.FROST, RelicType.LIFE),

    WARLOCK_AFFLICTION(WowClassEnum.WARLOCK, "Affliction", "Affliction", "265", RelicType.SHADOW, RelicType.BLOOD, RelicType.SHADOW),
    WARLOCK_DEMONOLOGY(WowClassEnum.WARLOCK, "Demonology", "Démonologie", "266", RelicType.SHADOW, RelicType.FIRE, RelicType.FEL),
    WARLOCK_DESTRUCTION(WowClassEnum.WARLOCK, "Destruction", "Destruction", "267", RelicType.FEL, RelicType.FIRE, RelicType.FEL),

    WARRIOR_ARMS(WowClassEnum.WARRIOR, "Arms", "Armes", "71", RelicType.IRON, RelicType.BLOOD, RelicType.SHADOW),
    WARRIOR_FURY(WowClassEnum.WARRIOR, "Fury", "Furie", "72", RelicType.FIRE, RelicType.STORM, RelicType.IRON),
    WARRIOR_PROTECTION(WowClassEnum.WARRIOR, "Protection", "Protection", "73", RelicType.IRON, RelicType.BLOOD, RelicType.FIRE);

    @Getter
    private WowClassEnum wowClass;
    @Getter
    private String nameEn;
    @Getter
    private String nameFr;
    @Getter
    private String wowheadId;
    @Getter
    private RelicType relic1;
    @Getter
    private RelicType relic2;
    @Getter
    private RelicType relic3;

    WowSpecEnum(WowClassEnum wowClass, String nameEn, String nameFr, String wowheadId, RelicType relic1, RelicType relic2, RelicType relic3) {
        this.wowClass = wowClass;
        this.nameEn = nameEn;
        this.nameFr = nameFr;
        this.wowheadId = wowheadId;
        this.relic1 = relic1;
        this.relic2 = relic2;
        this.relic3 = relic3;
    }

    public static List<WowSpecEnum> getAllFromClass(WowClassEnum wowClass) {
        List<WowSpecEnum> wowSpecs = new ArrayList<>(3);
        for (WowSpecEnum spec : values()) {
            if (spec.getWowClass().equals(wowClass)) {
                wowSpecs.add(spec);
            }
        }
        return wowSpecs;
    }

    public static WowSpecEnum findByString(String name) {
        for (WowSpecEnum spec : values()) {
            if (spec.name().equals(name)) {
                return spec;
            }
        }
        return null;
    }

}
