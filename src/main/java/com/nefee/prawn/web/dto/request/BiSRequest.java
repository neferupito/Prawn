package com.nefee.prawn.web.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BiSRequest {

    private String pawnstring;
    private Integer wowSpecId;
    private Integer numberT20Pieces;

}
