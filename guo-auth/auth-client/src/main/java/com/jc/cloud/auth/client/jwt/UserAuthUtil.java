package com.jc.cloud.auth.client.jwt;

import com.jc.cloud.auth.client.config.UserAuthConfig;
import com.jc.cloud.common.exception.auth.UserTokenException;
import com.jc.cloud.common.util.jwt.IJWTInfo;
import com.jc.cloud.common.util.jwt.JWTHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName UserAuthUtil
 * @Description //TODO 
 * @Author fangliai
 * @Date 2019/4/14 0014
 * @Version 1.0
 **/
@SuppressWarnings("ALL")
@Configuration
public class UserAuthUtil {
    @Autowired
    private UserAuthConfig userAuthConfig;
    public IJWTInfo getInfoFromToken(String token) throws Exception {
        try {
            return JWTHelper.getInfoFromToken(token, userAuthConfig.getPubKeyByte());
        }catch (ExpiredJwtException ex){
            throw new UserTokenException("User token expired!");
        }catch (SignatureException ex){
            throw new UserTokenException("User token signature error!");
        }catch (IllegalArgumentException ex){
            throw new UserTokenException("User token is null or empty!");
        }
    }
}
