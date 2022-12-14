package com.its.memberboard.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class CommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;

    private LocalDateTime commentCreatedTime;
    private LocalDateTime commentUpdateTime;
    private Long boardId;
}
