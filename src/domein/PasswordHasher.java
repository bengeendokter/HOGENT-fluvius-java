package domein;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import at.gadermaier.argon2.Argon2Factory;

public class PasswordHasher {
	public static String hash(String password, byte[] bytes)
	{
		String hash = Argon2Factory.create()
       .setIterations(2)
        .setMemory(14)
        .setParallelism(1)
        .hash(password.getBytes(StandardCharsets.UTF_8), bytes);
		
		return hash;
	}
}
