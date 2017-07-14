package com.nefee.prawn.data.entity;

import com.nefee.prawn.data.model.ArmorType;
import com.nefee.prawn.data.model.Slot;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class ArmorItem extends PrawnEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Long wowId;

    @NotNull
    private String name;

    @NotNull
    @Enumerated (EnumType.STRING)
    private Slot slot;

    @NotNull
    @Enumerated (EnumType.STRING)
    private ArmorType armorType;

    @NotNull
    private Integer armor = 0;

    @NotNull
    private Integer stamina = 0;

    @NotNull
    private Integer intellect = 0;

    @NotNull
    private Integer agility = 0;

    @NotNull
    private Integer strength = 0;

    @NotNull
    private Integer criticalStrike = 0;

    @NotNull
    private Integer mastery = 0;

    @NotNull
    private Integer versatility = 0;

    @NotNull
    private Integer haste = 0;

    @NotNull
    @ManyToOne
    private Boss boss;

    private boolean isSetPart = false;

    @ManyToOne
    private ClassArmorSet classArmorSet = null;
}
