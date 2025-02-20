package com.llm.athena.core.http.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record UserCreateRequestDto(
    @NotBlank(message = "Não pode ser vazio.")
    String name,
    @NotBlank(message = "Não pode ser vazio.")
    String lastName,
    @Email
    @Pattern(regexp = "^[A-Za-z0-9.]+@meirelesefreitas\\..+$", message = "Email inválido, Entre em contato com suporte.")
    String email,
    @NotBlank(message = "Não pode ser vazio.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%¨|&*()_+=;:?.^])[A-Za-z\\d!@#$%¨|&*()_+=;:?.^]{8,}$",
            message = "A senha deve conter pelo menos um caractere minúsculo, um caractere maiúsculo, um caractere especial, um número e deve ter pelo menos 8 caracteres."
    )
    String password,
    @NotBlank(message = "Não pode ser vazio.")
    @Pattern(regexp = "^\\d{3}.\\d{3}\\.\\d{3}-\\d{2}|\\d{2}.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$", message = "Insira o formato correto.")
    String cpfCnpj,
    @NotBlank(message = "Não pode ser vazio.")
    String jobPosition,
    @NotBlank(message = "Não pode ser vazio.")
    String company,
    @NotNull(message = "Não pode ser nulo.")
    LocalDate birthdate
) {}
