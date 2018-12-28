package com.favoride.application;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * ***********************************************************************
 * Nom ...........: SecurityService.java
 * Description ...: Classe permettant d'effectuer les methodes utiles
 * ...............: a la logique concernant le cryptage et le decryptage 
 * ...............: d'un mot de passe (Singleton)
 * Auteur(s) .....: P. Gajland - https://github.com/GaPhil
 * Version .......: 1.0
 ***********************************************************************
 */

public class SecurityService {
	
	private static SecurityService instance = null;

	private static final String ALGO = "AES";
    private static final byte[] keyValue =
            new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};

    public static SecurityService getInstance() {
		
		if(instance == null)
			instance = new SecurityService();
		
		return instance;
	}
    
    /**
     * Crypte une chaine de caracteres avec l'algorithme AES
     *
     * @param la chaine a crypter
     * @return la chaine cryptee
     */
    public String encrypt(String data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encVal);
    }

    /**
     * Decrypte une chaine de caracteres avec l'algorithme AES
     *
     * @param la chaine de caracteres a decrypter
     * @return la chaine decryptee
     */
    public String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        return new String(decValue);
    }

    /**
     * Genere une nouvelle clef de cryptage
     */
    private static Key generateKey() throws Exception {
        
    	return new SecretKeySpec(keyValue, ALGO);
    }
}
