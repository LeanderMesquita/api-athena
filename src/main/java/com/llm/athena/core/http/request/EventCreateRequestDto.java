package com.llm.athena.core.http.request;

import com.llm.athena.core.entity.User;

import java.time.LocalDate;

public record EventCreateRequestDto(
        User author,
        String description,
        String title,
        String rules,
        LocalDate endDate
) {
}
