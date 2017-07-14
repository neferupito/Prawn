package com.nefee.prawn.web.dto.request;

import com.nefee.prawn.data.entity.WowSpec;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WishRequest {

    private String title;
    private WowSpec wowSpec;
    private PawnString pawnString;
    private boolean is2PiecesRequired = false;
    private boolean is4PiecesRequired = false;

}
