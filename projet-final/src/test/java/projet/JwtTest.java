package projet;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import projet.config.jwt.JwtUtil;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class JwtTest {

	@Test
    public void generateToken() {
        // ARRANGE
        String username = "testUser";
        String password = "123456";
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

        // ACT
        String token = JwtUtil.generate(authentication);

        // ASSERT
        assertNotNull(token);
        assertFalse(token.isEmpty());
        assertTrue(token.contains("."));
        
        String payload = token.split("\\.")[1];
        String decodedPayload = new String(java.util.Base64.getDecoder().decode(payload));
        
        assertTrue(decodedPayload.contains(username));
    }
	
}
