package com.jc.cloud.common.util.jwt;


import com.jc.cloud.common.constant.ContextConstants;
import com.jc.cloud.common.constant.ErrorConstants;
import com.jc.cloud.common.util.StringHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

/**
 * @ClassName JWTHelper
 * @Description //TODO
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/
public class JWTHelper {
    private static RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();
    /**
     * 密钥加密token
     *
     * @param jwtInfo
     * @param priKeyPath
     * @param expire
     * @return
     * @throws Exception
     */
    public static String generateToken(IJWTInfo jwtInfo, String priKeyPath, int expire) throws Exception {
        String compactJws = Jwts.builder()
                .setSubject(jwtInfo.getUserId())
                .claim(ContextConstants.JWT_KEY_USER_ID,jwtInfo.getUserId())
                .claim(ContextConstants.JWT_KEY_USER_NAME,jwtInfo.getUserName())
                .claim(ContextConstants.JWT_KEY_SCOPE, jwtInfo.getScope())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKeyPath))
                .compact();
        return compactJws;
    }

    /**
     * 密钥加密token
     * @param jwtInfo
     * @param priKey
     * @param expire
     * @return
     * @throws Exception
     */
    public static String generateToken(IJWTInfo jwtInfo, byte priKey[], int expire) throws Exception {
        String compactJws = Jwts.builder()
                .setSubject(jwtInfo.getUserId())
                .claim(ContextConstants.JWT_KEY_USER_ID,jwtInfo.getUserId())
                .claim(ContextConstants.JWT_KEY_USER_NAME,jwtInfo.getUserName())
                .claim(ContextConstants.JWT_KEY_SCOPE, jwtInfo.getScope())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKey))
                .compact();
        return compactJws;
    }

    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserToken(String token, String pubKeyPath) throws Exception {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(pubKeyPath)).parseClaimsJws(token);
        return claimsJws;
    }
    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserToken(String token, byte[] pubKey) throws Exception {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(pubKey)).parseClaimsJws(token);
        return claimsJws;
    }
    /**
     * 获取token中的用户信息
     *
     * @param token
     * @param pubKeyPath
     * @return
     * @throws Exception
     */
    public static IJWTInfo getInfoFromToken(String token, String pubKeyPath) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, pubKeyPath);
        Claims body = claimsJws.getBody();

        return new JWTInfo(
                StringHelper.getObjectValue(body.get(ContextConstants.JWT_KEY_USER_ID)),
                StringHelper.getObjectValue(body.get(ContextConstants.JWT_KEY_USER_NAME)),
                StringHelper.getObjectValue(body.get(ContextConstants.JWT_KEY_SCOPE))
            );
    }
    /**
     * 获取token中的用户信息
     * @param token
     * @param pubKey
     * @return
     * @throws Exception
     */
    public static IJWTInfo getInfoFromToken(String token, byte[] pubKey) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, pubKey);
        Claims body = claimsJws.getBody();
        return new JWTInfo(
                StringHelper.getObjectValue(body.get(ContextConstants.JWT_KEY_USER_ID)),
                StringHelper.getObjectValue(body.get(ContextConstants.JWT_KEY_USER_NAME)),
                StringHelper.getObjectValue(body.get(ContextConstants.JWT_KEY_SCOPE))
        );
    }
}
