package com.portifolio.marketAPI.DTOs.establishment;

import com.portifolio.marketAPI.DTOs.user.UserResponse;

import java.util.List;

public record EstablishmentResponse(
        String id,
        String name,
        List<UserResponse> employees
) {
}
