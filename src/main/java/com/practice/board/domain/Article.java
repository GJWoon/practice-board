package com.practice.board.domain;

import java.time.LocalDateTime;

public class Article {
    private Long id;
    private String title;
    private String hashTag;
    private String content;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
}
