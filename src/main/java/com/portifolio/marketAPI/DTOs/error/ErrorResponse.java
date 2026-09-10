package com.portifolio.marketAPI.DTOs.error;

public record ErrorResponse(
        int error,
        String message
) {
}
