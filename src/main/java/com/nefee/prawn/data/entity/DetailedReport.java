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
public class DetailedReport extends PrawnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reportId;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<DetailedArmorSet> detailedArmorSets;

}
