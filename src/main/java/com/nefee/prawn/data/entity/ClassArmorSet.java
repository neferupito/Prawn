package com.nefee.prawn.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class ClassArmorSet extends PrawnEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private WowClass wowClass;

    @OneToMany (mappedBy = "classArmorSet")
    private List<ArmorItem> armorItems;

}
