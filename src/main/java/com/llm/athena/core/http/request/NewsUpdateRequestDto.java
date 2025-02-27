package com.llm.athena.core.http.request;

public record NewsUpdateRequestDto(
        String description,
        String title,
        String lead
) {}
