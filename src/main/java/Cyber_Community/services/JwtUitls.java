package Cyber_Community.services;

import Cyber_Community.repositories.UserRepository;
import Cyber_Community.entities.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Enzo Cotter on 02/05/2022.
 */
//@Component
//@ConfigurationProperties("jwt.config")
public class JwtUitls {

    @Autowired
    UserRepository userRepository;
    /**
     * The duration of the token is about 10 minutes
     */
    private static final long EXPIRE_TIME=10*60*1000;

    /**
     * Key for encryption
     */

    private static final String KEY="secure";

    /**
     * Generate token
     * @param user
     * @return
     */

    public String getToken(User user){
        Map<String,Object>header=new HashMap<>();
        header.put("typ","JWT");
        header.put("alg","HS256");
        String id=Long.toString(user.getId_User()) ;
        JwtBuilder builder= Jwts.builder().setHeader(header)
                            .setId(id)
                            .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_TIME))
                            .setSubject(user.getNickname())
                            .setIssuedAt(new Date())
                            .signWith(SignatureAlgorithm.HS256,KEY);
        return builder.compact();
    }

    /**
     * Verify if the token is valid
     * @param token
     * @return   1-Expired 0 (Verified o Failed)
     */
    public static int verify(String token){
        Claims claims=null;
        try {
            //When the token is expired, will throw ExpiredJwtException
            claims=Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            return 1;
        }

        return 0;

    }

    public Claims parseJwt(String  token){
        Claims claims=Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        return claims;
    }
}
