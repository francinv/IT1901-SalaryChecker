package salarychecker.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        encryptedString = encryptDecrypt.encrypt(stringToEncrypt, alias);
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
        assertEquals(stringToEncrypt, encryptDecrypt.decrypt(encryptedString, alias));
    }
}