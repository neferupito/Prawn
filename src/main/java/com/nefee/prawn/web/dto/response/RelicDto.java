package com.nefee.prawn.web.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelicDto {

    private String wowheadUrl;
    private String name;
    private String bossWowheadUrl;

}
