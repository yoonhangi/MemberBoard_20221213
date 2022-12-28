package com.its.memberboard.dto;

import com.its.memberboard.entity.CommentEntity;
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

    public static CommentDTO toCommentDTO(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setBoardId(commentEntity.getBoardEntity().getId());
        commentDTO.setCommentCreatedTime(commentEntity.getCreatedTime());
        commentDTO.setCommentUpdateTime(commentEntity.getUpdatedTime());
        return commentDTO;
    }
}
