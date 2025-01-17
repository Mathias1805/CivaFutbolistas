package Api.Futbolistas.Civa.JWT;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTservice {
    private static final String SECRET_KEY = "PdqFzPtrNwmMx1zI5FaZhZXyOpl3MlLTkfCWucP95Wk";
    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(),user);
    }
    public String getToken(Map<String,Object> extraClaims,UserDetails user) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1 * 24 * 60 * 60 * 1000))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Key getKey(){
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsuarioFromToken(String token) {
        return getClaim(token,Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String usuario = getUsuarioFromToken(token);
        return (usuario.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }
    private Claims getAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
    }
    public <T> T getClaim(String token, Function<Claims,T> ClaimsResolver){
        final Claims claims = getAllClaims(token);
        return ClaimsResolver.apply(claims);
    }
    private Date getExpiration(String token){
        return getClaim(token,Claims::getExpiration);
    }
    public boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}
