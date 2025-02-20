package com.llm.athena.core.http.request;

import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record UserUpdateRequestDto(
   String name,
   String lastName,
   @Pattern(regexp = "^\\d{3}.\\d{3}\\.\\d{3}-\\d{2}|\\d{2}.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$", message = "Insira o formato correto.")
   String cpfCnpj,
   String jobPosition,
   String company,
   LocalDate birthdate
) {}
