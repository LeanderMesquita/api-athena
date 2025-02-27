package com.llm.athena.core.http.request;

import com.llm.athena.core.entity.User;

public record NewsCreateRequestDto(
        User author,
        String description,
        String title,
        String lead
) {}
