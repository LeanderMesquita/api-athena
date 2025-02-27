package com.llm.athena.core.http.request;

public record SubscribeUpdateRequestDto(
        String subscribedId,
        String description
) {
}
