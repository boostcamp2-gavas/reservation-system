package com.gavas.domain.dto;

import com.gavas.domain.UserComment;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCommentDto {
    private UserComment userComment;
    private String nickName;
    private Long fileId;
    private Long fileCount;
}
