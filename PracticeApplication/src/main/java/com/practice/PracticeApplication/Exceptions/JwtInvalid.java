package com.practice.PracticeApplication.Exceptions;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;

public class JwtInvalid extends ExpiredJwtException {
    public JwtInvalid(Header h, Claims c, String msg){
        super(h,c,msg);
    }
}
