package domein;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import at.gadermaier.argon2.Argon2Factory;

public class PasswordHasher {
	public static String hash(String password, byte[] bytes)
	{
		byte[] pwd = password.getBytes(StandardCharsets.UTF_8);
		String hash = Argon2Factory.create()
       .setIterations(2)
        .setMemory(14)
        .setParallelism(1)
        .hash(pwd, bytes);
		
		return hash;
	}
}
