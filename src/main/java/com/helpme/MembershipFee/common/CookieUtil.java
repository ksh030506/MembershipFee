package com.helpme.MembershipFee.common;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
//쿠키 생성 및 조회 클래스
public class CookieUtil {

    //쿠키 생성
    public Cookie createCookie(String cookieName, String value){
        Cookie token = new Cookie(cookieName,value);
        token.setHttpOnly(true);
        //모든 path에 수정되었다고 전달
        token.setPath("/");
        return token;
    }

    //쿠키 조회
    public boolean getCookie(HttpServletRequest req, String cookieName) throws Exception {
        final Cookie[] cookies = req.getCookies();
        if (cookies == null) throw new Exception("로그인 해주세요");
        for (Cookie cookie : cookies) {
            if (!cookie.getName().equals(cookieName))
                throw new Exception("쿠키의 이름이 같지 않습니다");
        }
        return true;
    }

    //쿠키 값 조회
    public String getCookieValue(HttpServletRequest req, String cookieName) throws Exception {
        final Cookie[] cookies = req.getCookies();
        if(cookies == null) return null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(cookieName))
                return cookie.getValue();
        }
        return null;
    }
}
