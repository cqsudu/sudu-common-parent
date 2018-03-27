package com.ledo.jwt;

import com.ledo.jwt.config.JwtConfig;
import com.ledo.jwt.provider.JwtTokenProvider;
import com.ledo.jwt.provider.Payload;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * Created by konghang on 2017/8/8.
 */
public class Main {

    public static void main(String[] args) {
        JwtConfig config = new JwtConfig();
        config.setIssuer("我是签发者");
        config.setSecret("5+Pga5Fp7/I6kuBb7fv2WaOwPJSU4nw9eMkuiNijup3Bbbvxh+As4lPHe8OawEipHgj7B4dPpVMlZfT3eOL+nJyGvY1ihkO55COPMvnYxF7sNh56yWQx91QGo1DYZ9m6JNORQA==");
        config.setSubject("我的用户群体");
        config.setTtlMillis(1000000000L);

        JwtTokenProvider provider = new JwtTokenProvider(config);
        UserContext context = new UserContext();
        context.setUserId(123L);
        context.setName("konghang");
        String jwt = provider.createJwtToken(context);
        System.out.println(jwt);
        Jws<Claims> claimsJws = provider.parseJwtToken(jwt);
        String signature = claimsJws.getSignature();
        System.out.println(signature);
        Payload payload1 = provider.parseJwtToken(jwt, UserContext.class);
        System.out.println(payload1);
    }

    public static class UserContext implements Payload{

        private Long userId;

        private String name;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        /**
         * 键
         *
         * @return
         */
        @Override
        public String key() {
            return "data";
        }
    }
}
