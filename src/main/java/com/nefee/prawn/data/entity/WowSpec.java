package com.nefee.prawn.data.entity;

import com.nefee.prawn.data.model.RelicType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class WowSpec extends PrawnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer wowId;

    @ManyToOne
    private WowClass wowClass;

    @Enumerated(EnumType.STRING)
    private RelicType relic1;

    @Enumerated(EnumType.STRING)
    private RelicType relic2;

    @Enumerated(EnumType.STRING)
    private RelicType relic3;

}
