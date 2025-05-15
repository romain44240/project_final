package projet.service;

import java.security.interfaces.RSAPrivateKey;
import java.util.Date;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

@Service
public class JwtService {
	// private static Logger log = LoggerFactory.getLogger(JwtService.class);
	private static final int JWT_EXPIRATION = 3600000; // 1 heure

    @Value("classpath:privateKey.pem")
    private RSAPrivateKey privateKey;

    public String generate(Authentication authentication) {
		Date now = new Date();

		// log.debug("Generating token for user {} ...", authentication.getName());

		return Jwts.builder().setSubject(authentication.getName()) // On donne dans le sujet du JWT le nom d'utilisateur
				.setIssuedAt(now) // On précise la date du jeton
				.setExpiration(new Date(now.getTime() + JWT_EXPIRATION)) // On donne la date d'expiration
                .claim("roles",
                    authentication.getAuthorities()
                        .stream()
                        .map(a -> a.getAuthority())
                        .toList()
                )
				.signWith(this.privateKey) // On signe le JWT
				.compact(); // On retourne au format String
	}
}