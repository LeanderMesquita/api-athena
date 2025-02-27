package com.llm.athena.core.http.request;

public record SubscribeCreateRequestDto (
        String subscribedId,
        String description
) {}
