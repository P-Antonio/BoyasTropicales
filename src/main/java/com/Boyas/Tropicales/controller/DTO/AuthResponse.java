package com.Boyas.Tropicales.controller.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message", "tokem", "status"})
public record AuthResponse(String username, String message, String token, boolean status) {

}
