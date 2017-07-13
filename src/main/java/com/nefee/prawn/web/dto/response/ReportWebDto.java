package com.nefee.prawn.web.dto.response;

import com.nefee.prawn.data.model.RelicType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportWebDto {

    private String title;

    private ArmorItemDto head;
    private ArmorItemDto neck;
    private ArmorItemDto shoulders;
    private ArmorItemDto back;
    private ArmorItemDto chest;
    private ArmorItemDto wrists;
    private ArmorItemDto hands;
    private ArmorItemDto waist;
    private ArmorItemDto legs;
    private ArmorItemDto feet;
    private ArmorItemDto ring1;
    private ArmorItemDto ring2;

    private RelicType relicType1;
    private List<RelicDto> relics1;
    private RelicType relicType2;
    private List<RelicDto> relics2;
    private RelicType relicType3;
    private List<RelicDto> relics3;

}
