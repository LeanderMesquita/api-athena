package com.llm.athena.core.http.response;

import com.llm.athena.core.entity.News;
import com.llm.athena.core.entity.User;

import java.time.Instant;

public record NewsResponseDto(
        String id,
        Instant createdAt,
        Instant updatedAt,
        User author,
        String title,
        String lead,
        String description
) {
    public NewsResponseDto (News news) {
        this(
                news.getId(),
                news.getCreatedAt(),
                news.getUpdatedAt(),
                news.getAuthor(),
                news.getTitle(),
                news.getLead(),
                news.getDescription()
        );
    }
}






