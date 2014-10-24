package ie.gmit;
import javax.crypto.*;

import java.io.Serializable;
import java.security.Key;

public class Encryption implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static Key makeKey() throws Exception {		
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128);
		Key key = keyGen.generateKey();
		return key;
	}
	
	public static byte[] encrypt(String plainText, Key key) throws Exception {
		Cipher cypher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cypher.init(Cipher.ENCRYPT_MODE, key);
		byte[] cypherText = cypher.doFinal(plainText.getBytes());
		return cypherText;		
	}
	
	public static String decrypt(byte[] encryptedText, Key key) throws Exception{
		Cipher cypher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cypher.init(Cipher.DECRYPT_MODE, key);
		byte[] originalText = cypher.doFinal(encryptedText);
		return new String(originalText);		
	}
}
