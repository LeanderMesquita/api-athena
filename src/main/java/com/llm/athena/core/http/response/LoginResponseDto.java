package com.llm.athena.core.http.response;

public record LoginResponseDto(
        String token,
        Long expiresIn
)
{
    public LoginResponseDto(String token, Long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }
}
