package com.webshop.products.service;

import io.jsonwebtoken.Claims;

public interface TokenService {

    Claims parseToken(String jwt);

}
