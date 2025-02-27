package com.llm.athena.core.http.request;

import java.time.LocalDate;

public record EventUpdateRequestDto(
        String description,
        String title,
        String rules,
        LocalDate endDate
) {}
