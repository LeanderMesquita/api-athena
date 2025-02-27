package com.llm.athena.core.http.response;

import com.llm.athena.core.entity.Subscribe;
import com.llm.athena.core.entity.User;

import java.time.Instant;

public record SubscribeResponseDto (
        String id,
        Instant createdAt,
        Instant updatedAt,
        User author,
        User subscribed,
        String description
){
    public SubscribeResponseDto (Subscribe subscribe) {
        this(
                subscribe.getId(),
                subscribe.getCreatedAt(),
                subscribe.getUpdatedAt(),
                subscribe.getAuthor(),
                subscribe.getSubscribed(),
                subscribe.getDescription()
        );
    }
}
