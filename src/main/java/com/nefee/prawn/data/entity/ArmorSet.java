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
public class ArmorSet extends PrawnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private ArmorItem head;
    private String headWowheadUrl;
    @ManyToOne
    private ArmorItem neck;
    private String neckWowheadUrl;
    @ManyToOne
    private ArmorItem shoulders;
    @ManyToOne
    private ArmorItem back;
    @ManyToOne
    private ArmorItem chest;
    @ManyToOne
    private ArmorItem wrists;
    @ManyToOne
    private ArmorItem hands;
    @ManyToOne
    private ArmorItem waist;
    @ManyToOne
    private ArmorItem legs;
    @ManyToOne
    private ArmorItem feet;

    @OneToMany
    private List<Relic> relics1;
    @OneToMany
    private List<Relic> relics2;
    @OneToMany
    private List<Relic> relics3;

}
