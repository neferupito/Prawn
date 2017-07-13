package com.nefee.prawn.data.entity;

import com.nefee.prawn.data.model.Slot;
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
public class DetailedArmorSet extends PrawnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Slot slot;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<ScoredArmorItem> scoredArmorItems;

}
