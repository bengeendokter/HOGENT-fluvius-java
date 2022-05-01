package domein;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class PasswordHasher {

	
	public static String hash(String password)
	{
		// salt 32 bytes
        // Hash length 64 bytes
        Argon2 argon2 = Argon2Factory.create(
                Argon2Factory.Argon2Types.ARGON2id,
                16,
                32);

        char[] passwordArray = password.toCharArray();
        String hash = argon2.hash(3, // Number of iterations
                64 * 1024, // 64mb
                1, // how many parallel threads to use
                passwordArray);
        
        return hash;
    }
	
	public static boolean verify(String hash, String password)
	{
		Argon2 argon2 = Argon2Factory.create(
                Argon2Factory.Argon2Types.ARGON2id,
                16,
                32);
		
		char[] passwordArray = password.toCharArray();
		System.out.println("password: "+ password);
		System.out.println("Hash: "+ hash);
		  System.out.println("Password verification success: "+ argon2.verify(hash, passwordArray));
        return argon2.verify(hash, passwordArray);
    }

	
}
