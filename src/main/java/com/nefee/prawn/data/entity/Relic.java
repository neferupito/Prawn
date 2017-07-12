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
public class Relic extends PrawnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long wowId;

    private String name;

    @ManyToOne
    private Boss boss;

    @Enumerated(EnumType.STRING)
    private RelicType relicType;

}
