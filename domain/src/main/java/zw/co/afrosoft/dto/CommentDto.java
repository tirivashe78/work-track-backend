package zw.co.afrosoft.dto;

import lombok.Data;

@Data

public class CommentDto {
    private Long taskId;
    private Long userId;
    private String content;
}
