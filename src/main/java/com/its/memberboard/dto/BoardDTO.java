package com.its.memberboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardTitle;
    private String boardContents;

    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdateTime;

    private int boardHits;
}
