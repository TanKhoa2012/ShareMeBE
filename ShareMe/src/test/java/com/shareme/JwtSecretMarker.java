package com.shareme;

import org.junit.jupiter.api.Test;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;

public class JwtSecretMarker {
	@Test
	public void generateSecretKey() {
		SecretKey key = Jwts.SIG.HS512.key().build();
		String encodedKey = DatatypeConverter.printHexBinary(key.getEncoded());
		System.out.println(encodedKey);
	}

}
