package salarychecker.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EncryptDecryptTest {

    private String alias = "testalias";
    private String stringToEncrypt = "Passord123!";
    private EncryptDecrypt encryptDecrypt;
    private String encryptedString;

    @BeforeEach
    void setUp(){
        encryptDecrypt = new EncryptDecrypt();
        try {
        
            encryptedString = encryptDecrypt.encrypt(stringToEncrypt, alias);

        } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
           e.printStackTrace();
        }
    }

    @Test
    public void testGetKeyStore(){
        assertNotNull(encryptDecrypt.getKeyStore());
    }

    @Test
    public void testEncrypt() {
        assertNotEquals(stringToEncrypt, encryptedString);
    }

    @Test
    public void testDecrypt() {
        try {
            assertEquals(stringToEncrypt, encryptDecrypt.decrypt(encryptedString, alias));
        } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            fail();
        }
    }
    
}
