package com.nefee.prawn.data.entity;

import com.nefee.prawn.data.model.ArmorType;
import com.nefee.prawn.data.model.Slot;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class ArmorItem extends PrawnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long wowId;

    private String name;

    @Enumerated(EnumType.STRING)
    private Slot slot;

    @Enumerated(EnumType.STRING)
    private ArmorType armorType;

    private Integer armor = 0;

    private Integer stamina = 0;

    private Integer intellect = 0;

    private Integer agility = 0;

    private Integer strength = 0;

    private Integer criticalStrike = 0;

    private Integer mastery = 0;

    private Integer versatility = 0;

    private Integer haste = 0;

    @ManyToOne
    private Boss boss;

}
