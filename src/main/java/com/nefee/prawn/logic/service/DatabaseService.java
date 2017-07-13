package com.nefee.prawn.logic.service;


import com.nefee.prawn.data.dao.*;
import com.nefee.prawn.data.entity.*;
import com.nefee.prawn.data.model.ArmorType;
import com.nefee.prawn.data.model.RelicType;
import com.nefee.prawn.data.model.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DatabaseService {

    @Autowired
    private ArmorItemRepository armorItemRepository;
    @Autowired
    private RaidRepository raidRepository;
    @Autowired
    private BossRepository bossRepository;
    @Autowired
    private RelicRepository relicRepository;
    @Autowired
    private WowClassRepository wowClassRepository;
    @Autowired
    private WowSpecRepository wowSpecRepository;

    private Boss goroth;
    private Boss inquisition;
    private Boss harjatan;
    private Boss sasszine;
    private Boss moonSisters;
    private Boss host;
    private Boss maiden;
    private Boss avatar;
    private Boss kiljaeden;

    public void load() {

        loadClasses();

        // RAID & BOSSES
        loadBosses();

        // RELICS
        relicRepository.save(addRelics());

        // HEADS
        armorItemRepository.save(addHeads());

        // NECKS
        armorItemRepository.save(addNecks());

        // SHOULDERS
        armorItemRepository.save(addShoulders());

        // BACKS
        armorItemRepository.save(addBacks());
    }

    private void loadClasses() {
        for (WowClassEnum wowClass : WowClassEnum.values()) {
            WowClass wowClassEntity = WowClass.builder()
                    .armorType(wowClass.getArmorType())
                    .name(wowClass.getNameEn())
                    .build();
            wowClassEntity = wowClassRepository.save(wowClassEntity);

            List<WowSpecEnum> wowSpecEnums = WowSpecEnum.getAllFromClass(wowClass);
            for (WowSpecEnum wowSpecEnum : wowSpecEnums) {
                wowSpecRepository.save(WowSpec.builder()
                        .name(wowSpecEnum.getNameEn())
                        .wowClass(wowClassEntity)
                        .wowId(Integer.parseInt(wowSpecEnum.getWowheadId()))
                        .relic1(wowSpecEnum.getRelic1())
                        .relic2(wowSpecEnum.getRelic2())
                        .relic3(wowSpecEnum.getRelic3())
                        .cssClass(wowSpecEnum.name().toLowerCase().replace("_", "-"))
                        .build());
            }
        }
    }

    private void loadBosses() {
        // RAID
        Raid tombe = Raid.builder()
                .name("Tomb of Sargeras")
                .build();
        raidRepository.save(tombe);

        // BOSSES

        Boss.BossBuilder bossBuilder = Boss.builder()
                .lootBonusId("3563:1512")
                .raid(tombe);

        goroth = bossBuilder
                .name("Goroth")
                .wowId(115844L)
                .build();

        inquisition = bossBuilder
                .name("Demonic Inquisition")
                .wowId(120996L)
                .build();

        harjatan = bossBuilder
                .name("Harjatan")
                .wowId(116407L)
                .build();

        sasszine = bossBuilder
                .name("Mistress Sassz'ine")
                .wowId(115767L)
                .build();

        moonSisters = bossBuilder
                .name("Sisters of the Moon")
                .wowId(118523L)
                .build();

        host = bossBuilder
                .name("The Desolate Host")
                .wowId(118460L)
                .build();

        maiden = bossBuilder
                .name("Maiden of Vigilance")
                .wowId(118289L)
                .build();

        avatar = bossBuilder
                .name("Fallen Avatar")
                .wowId(120436L)
                .build();

        kiljaeden = bossBuilder
                .name("Kil'jaeden")
                .lootBonusId("3563:1522")
                .wowId(117269L)
                .build();

        bossRepository.save(Arrays.asList(goroth, inquisition, harjatan, sasszine, moonSisters, host, maiden, avatar, kiljaeden));
    }

    private List<ArmorItem> addHeads() {
        ArmorItem.ArmorItemBuilder ArmorItemBuilder = ArmorItem.builder()
                .slot(Slot.HEAD);
        List<ArmorItem> items = new ArrayList<>();

        items.add(ArmorItemBuilder
                .wowId(146990L)
                .name("Hundred-Fathom Veil")
                .boss(sasszine)
                .armorType(ArmorType.CLOTH)
                .armor(281)
                .stamina(4305)
                .agility(0)
                .strength(0)
                .intellect(2870)
                .haste(0)
                .criticalStrike(0)
                .mastery(1241)
                .versatility(607)
                .build());

        items.add(ArmorItemBuilder
                .wowId(146991L)
                .name("Blackened Mask of Disgrace")
                .boss(avatar)
                .armorType(ArmorType.CLOTH)
                .armor(281)
                .stamina(4305)
                .agility(0)
                .strength(0)
                .intellect(2870)
                .haste(1122)
                .criticalStrike(726)
                .mastery(0)
                .versatility(0)
                .build());

        items.add(ArmorItemBuilder
                .wowId(147033L)
                .name("Lunar-Wrath Headgear")
                .boss(moonSisters)
                .armorType(ArmorType.LEATHER)
                .armor(349)
                .stamina(4305)
                .agility(2870)
                .strength(0)
                .intellect(2870)
                .haste(1202)
                .criticalStrike(0)
                .mastery(647)
                .versatility(0)
                .build());

        items.add(ArmorItemBuilder
                .wowId(147034L)
                .name("Shadow-Scarred Headcover")
                .boss(kiljaeden)
                .armorType(ArmorType.LEATHER)
                .armor(361)
                .stamina(4726)
                .agility(3150)
                .strength(0)
                .intellect(3150)
                .haste(630)
                .criticalStrike(1288)
                .mastery(0)
                .versatility(0)
                .build());

        items.add(ArmorItemBuilder
                .wowId(147049L)
                .name("Azure Squallshaper's Helm")
                .boss(sasszine)
                .armorType(ArmorType.MAIL)
                .armor(431)
                .stamina(4305)
                .agility(2870)
                .strength(0)
                .intellect(2870)
                .haste(0)
                .criticalStrike(0)
                .mastery(726)
                .versatility(1122)
                .build());

        items.add(ArmorItemBuilder
                .wowId(147050L)
                .name("Crown of Discarded Hope")
                .boss(maiden)
                .armorType(ArmorType.MAIL)
                .armor(431)
                .stamina(4305)
                .agility(2870)
                .strength(0)
                .intellect(2870)
                .haste(1162)
                .criticalStrike(686)
                .mastery(0)
                .versatility(0)
                .build());

        items.add(ArmorItemBuilder
                .wowId(147063L)
                .name("Armet of the Rotten Mind")
                .boss(host)
                .armorType(ArmorType.PLATE)
                .armor(662)
                .stamina(4305)
                .agility(0)
                .strength(2870)
                .intellect(2870)
                .haste(1122)
                .criticalStrike(0)
                .mastery(726)
                .versatility(0)
                .build());

        return items;
    }

    private List<ArmorItem> addNecks() {
        ArmorItem.ArmorItemBuilder ArmorItemBuilder = ArmorItem.builder()
                .slot(Slot.NECK)
                .armorType(ArmorType.ACCESSORY);

        List<ArmorItem> items = new ArrayList<>();

        items.add(ArmorItemBuilder
                .wowId(147013L)
                .name("String of Extracted Incisors")
                .boss(inquisition)
                .armor(0)
                .stamina(2422)
                .agility(0)
                .strength(0)
                .intellect(0)
                .haste(2112)
                .criticalStrike(0)
                .mastery(0)
                .versatility(1408)
                .build());

        items.add(ArmorItemBuilder
                .wowId(147014L)
                .name("Locket of Splintered Souls")
                .boss(host)
                .armor(0)
                .stamina(2422)
                .agility(0)
                .strength(0)
                .intellect(0)
                .haste(0)
                .criticalStrike(1609)
                .mastery(1911)
                .versatility(0)
                .build());

        return items;
    }

    private List<ArmorItem> addShoulders() {
        ArmorItem.ArmorItemBuilder ArmorItemBuilder = ArmorItem.builder()
                .slot(Slot.SHOULDERS);
        List<ArmorItem> items = new ArrayList<>();

        items.add(ArmorItemBuilder
                .wowId(146996L)
                .name("Mantle of Broken Spirits")
                .boss(inquisition)
                .armorType(ArmorType.CLOTH)
                .armor(260)
                .stamina(3229)
                .agility(0)
                .strength(0)
                .intellect(2153)
                .haste(0)
                .criticalStrike(0)
                .mastery(901)
                .versatility(485)
                .build());

        items.add(ArmorItemBuilder
                .wowId(146997L)
                .name("Shoulderpads of Whispering Twilight")
                .boss(moonSisters)
                .armorType(ArmorType.CLOTH)
                .armor(260)
                .stamina(3229)
                .agility(0)
                .strength(0)
                .intellect(2153)
                .haste(512)
                .criticalStrike(871)
                .mastery(0)
                .versatility(0)
                .build());

        items.add(ArmorItemBuilder
                .wowId(147037L)
                .name("Dripping Arcfin Shoulderpads")
                .boss(moonSisters)
                .armorType(ArmorType.LEATHER)
                .armor(322)
                .stamina(3229)
                .agility(2153)
                .strength(0)
                .intellect(2153)
                .haste(842)
                .criticalStrike(0)
                .mastery(545)
                .versatility(0)
                .build());

        items.add(ArmorItemBuilder
                .wowId(147053L)
                .name("Pauldrons of the Gibbering Eye")
                .boss(inquisition)
                .armorType(ArmorType.MAIL)
                .armor(398)
                .stamina(3229)
                .agility(2153)
                .strength(0)
                .intellect(2153)
                .haste(574)
                .criticalStrike(812)
                .mastery(0)
                .versatility(0)
                .build());

        items.add(ArmorItemBuilder
                .wowId(147054L)
                .name("Mantle of Waning Radiance")
                .boss(moonSisters)
                .armorType(ArmorType.MAIL)
                .armor(398)
                .stamina(3229)
                .agility(2153)
                .strength(0)
                .intellect(2153)
                .haste(901)
                .criticalStrike(0)
                .mastery(485)
                .versatility(0)
                .build());

        items.add(ArmorItemBuilder
                .wowId(147069L)
                .name("Shoulderplates of Crackling Flame")
                .boss(goroth)
                .armorType(ArmorType.PLATE)
                .armor(611)
                .stamina(3229)
                .agility(0)
                .strength(2153)
                .intellect(2153)
                .haste(455)
                .criticalStrike(0)
                .mastery(931)
                .versatility(0)
                .build());

        items.add(ArmorItemBuilder
                .wowId(147070L)
                .name("Grave-Dredged Pauldrons")
                .boss(host)
                .armorType(ArmorType.PLATE)
                .armor(611)
                .stamina(3229)
                .agility(0)
                .strength(2153)
                .intellect(2153)
                .haste(0)
                .criticalStrike(842)
                .mastery(931)
                .versatility(545)
                .build());

        return items;
    }

    private List<ArmorItem> addBacks() {
        ArmorItem.ArmorItemBuilder ArmorItemBuilder = ArmorItem.builder()
                .slot(Slot.BACK)
                .armorType(ArmorType.ACCESSORY);
        List<ArmorItem> items = new ArrayList<>();

        items.add(ArmorItemBuilder
                .wowId(146984L)
                .name("Cloak of Stifling Brimstone")
                .boss(goroth)
                .armor(173)
                .stamina(2422)
                .agility(1615)
                .strength(1615)
                .intellect(1615)
                .haste(676)
                .criticalStrike(0)
                .mastery(364)
                .versatility(0)
                .build());

        items.add(ArmorItemBuilder
                .wowId(146985L)
                .name("Shroud of the Drowned Adherent")
                .boss(sasszine)
                .armor(173)
                .stamina(2422)
                .agility(1615)
                .strength(1615)
                .intellect(1615)
                .haste(0)
                .criticalStrike(0)
                .mastery(609)
                .versatility(430)
                .build());

        items.add(ArmorItemBuilder
                .wowId(147193L)
                .name("Cape of Mindless Fury")
                .boss(avatar)
                .armor(173)
                .stamina(2422)
                .agility(1615)
                .strength(1615)
                .intellect(1615)
                .haste(408)
                .criticalStrike(631)
                .mastery(0)
                .versatility(0)
                .build());

        return items;
    }

    private List<Relic> addRelics() {
        Relic.RelicBuilder arcaneRelicBuilder = Relic.builder()
                .relicType(RelicType.ARCANE);
        Relic.RelicBuilder bloodRelicBuilder = Relic.builder()
                .relicType(RelicType.BLOOD);
        Relic.RelicBuilder felRelicBuilder = Relic.builder()
                .relicType(RelicType.FEL);
        Relic.RelicBuilder fireRelicBuilder = Relic.builder()
                .relicType(RelicType.FIRE);
        Relic.RelicBuilder frostRelicBuilder = Relic.builder()
                .relicType(RelicType.FROST);
        Relic.RelicBuilder holyRelicBuilder = Relic.builder()
                .relicType(RelicType.HOLY);
        Relic.RelicBuilder ironRelicBuilder = Relic.builder()
                .relicType(RelicType.IRON);
        Relic.RelicBuilder lifeRelicBuilder = Relic.builder()
                .relicType(RelicType.LIFE);
        Relic.RelicBuilder shadowRelicBuilder = Relic.builder()
                .relicType(RelicType.SHADOW);
        Relic.RelicBuilder stormRelicBuilder = Relic.builder()
                .relicType(RelicType.STORM);

        List<Relic> items = new ArrayList<>();

        // ARCANE
        items.add(arcaneRelicBuilder
                .wowId(147076L)
                .name("Charred Hymnal of the Moon")
                .boss(goroth)
                .build());
        items.add(arcaneRelicBuilder
                .wowId(147077L)
                .name("Inexorable Truth Serum")
                .boss(inquisition)
                .build());
        items.add(arcaneRelicBuilder
                .wowId(147078L)
                .name("Mote of Astral Suffusion")
                .boss(moonSisters)
                .build());
        items.add(arcaneRelicBuilder
                .wowId(147079L)
                .name("Torn Fabric of Reality")
                .boss(kiljaeden)
                .build());

        // BLOOD
        items.add(bloodRelicBuilder
                .wowId(147080L)
                .name("Blood of the Unworthy")
                .boss(inquisition)
                .build());
        items.add(bloodRelicBuilder
                .wowId(147081L)
                .name("Pungent Chum")
                .boss(sasszine)
                .build());
        items.add(bloodRelicBuilder
                .wowId(151189L)
                .name("Tears of the Maiden")
                .boss(maiden)
                .build());
        items.add(bloodRelicBuilder
                .wowId(147082L)
                .name("Man'ari Blood Pact")
                .boss(kiljaeden)
                .build());

        // FEL
        items.add(felRelicBuilder
                .wowId(147084L)
                .name("Imploding Infernal Star")
                .boss(goroth)
                .build());
        items.add(felRelicBuilder
                .wowId(147085L)
                .name("Mutated Nautilus")
                .boss(sasszine)
                .build());
        items.add(felRelicBuilder
                .wowId(147086L)
                .name("Befouled Effigy of Elune")
                .boss(host)
                .build());
        items.add(felRelicBuilder
                .wowId(147087L)
                .name("Ruinous Ashes")
                .boss(kiljaeden)
                .build());

        // FIRE
        items.add(fireRelicBuilder
                .wowId(147088L)
                .name("Smoldering Thumbscrews")
                .boss(inquisition)
                .build());
        items.add(fireRelicBuilder
                .wowId(147089L)
                .name("Ferocity of the Devout")
                .boss(moonSisters)
                .build());
        items.add(fireRelicBuilder
                .wowId(147090L)
                .name("Stabilized Extinction Protocol")
                .boss(maiden)
                .build());
        items.add(fireRelicBuilder
                .wowId(147091L)
                .name("Cleansing Ignition Catalyst")
                .boss(avatar)
                .build());

        // FROST
        items.add(frostRelicBuilder
                .wowId(147092L)
                .name("Ice-Threaded Conch")
                .boss(harjatan)
                .build());
        items.add(frostRelicBuilder
                .wowId(147093L)
                .name("Globe of Frothing Eddies")
                .boss(sasszine)
                .build());
        items.add(frostRelicBuilder
                .wowId(147094L)
                .name("Virus of Lethargy")
                .boss(maiden)
                .build());
        items.add(frostRelicBuilder
                .wowId(147095L)
                .name("Sphere of Entropy")
                .boss(kiljaeden)
                .build());

        // HOLY
        items.add(holyRelicBuilder
                .wowId(147096L)
                .name("Inquisition's Master Key")
                .boss(inquisition)
                .build());
        items.add(holyRelicBuilder
                .wowId(147097L)
                .name("Blessing of the White Lady")
                .boss(moonSisters)
                .build());
        items.add(holyRelicBuilder
                .wowId(147098L)
                .name("Fragment of Grace")
                .boss(maiden)
                .build());
        items.add(holyRelicBuilder
                .wowId(147099L)
                .name("Boon of the Prophet")
                .boss(kiljaeden)
                .build());

        // IRON
        items.add(ironRelicBuilder
                .wowId(147100L)
                .name("Calcified Barnacle")
                .boss(harjatan)
                .build());
        items.add(ironRelicBuilder
                .wowId(147101L)
                .name("Chiseled Starlight")
                .boss(moonSisters)
                .build());
        items.add(ironRelicBuilder
                .wowId(147102L)
                .name("Reactive Pylon Casing")
                .boss(avatar)
                .build());

        // LIFE
        items.add(lifeRelicBuilder
                .wowId(147104L)
                .name("Icon of Perverse Animation")
                .boss(goroth)
                .build());
        items.add(lifeRelicBuilder
                .wowId(147105L)
                .name("Moontalon's Feather")
                .boss(moonSisters)
                .build());
        items.add(lifeRelicBuilder
                .wowId(147106L)
                .name("Glowing Prayer Candle")
                .boss(host)
                .build());
        items.add(lifeRelicBuilder
                .wowId(147107L)
                .name("Valorous Spark of Hope")
                .boss(avatar)
                .build());

        // SHADOW
        items.add(shadowRelicBuilder
                .wowId(147108L)
                .name("Brand of Relentless Agony")
                .boss(goroth)
                .build());
        items.add(shadowRelicBuilder
                .wowId(147109L)
                .name("Harjatan's Leering Eye")
                .boss(harjatan)
                .build());
        items.add(shadowRelicBuilder
                .wowId(147110L)
                .name("Grimacing Highborne Skull")
                .boss(host)
                .build());
        items.add(shadowRelicBuilder
                .wowId(147111L)
                .name("Scornful Reflection")
                .boss(kiljaeden)
                .build());

        // STORM
        items.add(stormRelicBuilder
                .wowId(147112L)
                .name("Felsoul Vortex")
                .boss(goroth)
                .build());
        items.add(stormRelicBuilder
                .wowId(147113L)
                .name("Flawless Hurricane Pearl")
                .boss(sasszine)
                .build());
        items.add(stormRelicBuilder
                .wowId(147114L)
                .name("Preserved Starlight Incense")
                .boss(host)
                .build());
        items.add(stormRelicBuilder
                .wowId(147115L)
                .name("Unfurling Origination")
                .boss(maiden)
                .build());

        return items;
    }

}
