package com.reservalab.reservalab;

import java.util.UUID;

import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

public class Bcrypt {
	
	@Test
	public void test() {
		String key = UUID.randomUUID().toString();
		String hash =  BCrypt.hashpw(key, BCrypt.gensalt());
		String hash2 =  BCrypt.hashpw(key, BCrypt.gensalt());
		String hash3 =  BCrypt.hashpw(key, BCrypt.gensalt());
		String hash4 =  BCrypt.hashpw(key, BCrypt.gensalt());
		System.out.println("Chave -> " + key );
		System.out.println("hash1 -> " + hash + " válido -> " + BCrypt.checkpw(key, hash));
		System.out.println("hash2 -> " + hash2  + " válido -> " + BCrypt.checkpw(key, hash));
		System.out.println("hash3 -> " + hash3 + " válido -> " + BCrypt.checkpw(key, hash));
		System.out.println("hash4 -> " + hash4  + " válido -> " + BCrypt.checkpw(key, hash+"as"));
		
		 
	}

}
