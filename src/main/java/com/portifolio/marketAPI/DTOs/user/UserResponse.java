package com.portifolio.marketAPI.DTOs.user;

import com.portifolio.marketAPI.entity.enums.UserRole;

public record UserResponse(
        String id,

        String name,

        UserRole userRole
) {
}
