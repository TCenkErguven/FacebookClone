package com.cenk.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {
    /**
     * JWT token üretiminde kullanılacak olan kriptolama algoritmalarının
     * kullanacağın nahatar bilgisini burada veriyoruz.
     * !!!!!!!DİKKAT!!!!!!!
     * Bu anahtar bilgisinin açık bir şekilde kod içinde var olması bir güvenlik açığıdır.Anahtarın
     * kod içinden alınarak ENV şeklinde başka bir repo üzerinden erişilebilir olması daha güvenli olacaktır.
     */
   /*
    @Value("${my-application.jwt.secret-key}")  //application.yml'da ki jwt kırılımını belirtiyoruz.
    private String SecretKey;
   */
    private final String sifreAnahtari = "X$so*wASwU*!@+ruyOnL9!lchex&fu?@?6e=rO$r$t5-ecrlzuJEml*rAqls4AB6";    //her bir karakter için 80 deneme yapılması gerekmektedir. 80^64
    private Long exDate = 1000L *   60 * 2;
    /**
     * Kod iççerisinde şifre belirtilmemeli SecretKey'deki gibi şifreler belirtilir.
     * JWT üretimi yapılırken belli bilgilerin payload için eklenmesi gereklidir.
     * Yani token verdiğimiz kullanıcıya ait kimlik bilgilerini doğrulayabilecek
     * bilgiler olabilir. Ama dikkat etmeliyiz ki burada bulunan bilgiler açık bir şekilde iletilmektedir.
     * Bu nedenle Claim nesnelerinin içine eklenecek bilgilerin özel gizli bilgiler
     * olamamasına dikkat etmeliyiz.
     */
    public Optional<String> createToken(Long id){
        String token;
        try{
            token = JWT.create().withAudience()
                    .withClaim("id",id) //Payload dataları
                    .withClaim("howtopage","AuthMicroService")  //Payload dataları
                    .withClaim("isOne",true)    //Payload dataları
                    .withIssuer("Java7")    //token üreten uygulama
                    .withIssuedAt(new Date())   //token oluşturma tarihi
                    .withExpiresAt(new Date(System.currentTimeMillis() + exDate))   //token in sona erme zamanı
                    .sign(Algorithm.HMAC512(sifreAnahtari));
            return Optional.of(token);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    /**
     * İlk olarak bir JWT token bilgisinin bize ait olup olmadığının, kullanım süresinin dolup dolmadığının
     * kontrolünün yapılması gereklidir.
     * Token içinde önceden bizim tarafımızdan eklenn bilgilerin token Claim nesneleri içinde alınması gereklidir.
     * @param token
     * @return
     */
    public Optional<Long> getIdFromToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(sifreAnahtari);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Java7")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if(decodedJWT==null) return Optional.empty();
            return Optional.of(decodedJWT.getClaim("id").asLong());
        }catch (Exception e){
            return Optional.empty();
        }
    }
}
