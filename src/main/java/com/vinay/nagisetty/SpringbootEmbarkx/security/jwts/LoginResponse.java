package com.vinay.nagisetty.SpringbootEmbarkx.security.jwts;

import java.util.List;

public record LoginResponse(String username, List<String> roles, String jwtToken) {}
