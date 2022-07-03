package ru.comavp.authservice;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.SecureRandom;

class GenerateSecretKey {

	@Test
	void generate() {
		byte[] bytes = new byte[32];
		new SecureRandom().nextBytes(bytes);
		String secretKey = new BigInteger(1, bytes).toString(16);
		System.out.println("Secret key: " + secretKey);
	}

}
