package com.nefee.prawn.data.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Report extends PrawnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reportId;

    private String title;

    @ManyToOne
    private WowSpec wowSpec;

    private String pawnstring;

    @OneToOne
    private ArmorSet armorSet;

}
