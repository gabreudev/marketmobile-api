package com.gabreudev.marketmobile_api.entities.user;

public record RegisterDTO(String email, String password, UserRole role) {
}
