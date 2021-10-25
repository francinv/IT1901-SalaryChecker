package salarychecker.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * Class to encrypt and decrypt user information;
 * @author serans
 * @author jakobk
 * @author francinv
 */
public class EncryptDecrypt {

    private KeyStore keyStore;
    private final static String ALGORTIHM = "AES/ECB/PKCS5Padding";
    
    public EncryptDecrypt() {
       try {
            keyStore = KeyStore.getInstance("JCEKS");
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
    }

    public KeyStore getKeyStore() {
        return keyStore;
    }

    /**
     * Creates a keystore to store the generated secretkey
     * Uses the users name as alias.
     * @param alias
     * @param secretKey
     * @throws KeyStoreException
     * @throws IOException
     * @throws CertificateException
     * @throws NoSuchAlgorithmException
     * @throws Exception
     */
    public void storeToKeyStore(String alias, SecretKey secretKey) throws KeyStoreException,
        NoSuchAlgorithmException, CertificateException, IOException {

        String path = System.getProperty("user.home") + "/SalarycheckerKeystore.jks";
        File file = new File(path);
        char[] jksPassword = "changeit".toCharArray();
        InputStream readCert = null;
        OutputStream writStream = null;

        try {
            if (!file.exists()) {
                keyStore.load(null, null);
            } else {
                readCert = new FileInputStream(path);
                keyStore.load(readCert, jksPassword);
            }

            keyStore.setKeyEntry(alias, secretKey, jksPassword, null);
            writStream = new FileOutputStream(path);
            keyStore.store(writStream, jksPassword);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (readCert != null) {
                    readCert.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (writStream != null) {
                    writStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public SecretKey loadFromKeyStore(String alias) {

        String path = System.getProperty("user.home") + "/SalarycheckerKeystore.jks";
        char[] jksPassword = "changeit".toCharArray();

        try {
            InputStream readStream = new FileInputStream(path);
            keyStore.load(readStream, jksPassword);
            SecretKey secretKey = (SecretKey) keyStore.getKey(alias, jksPassword);
            readStream.close();
            return secretKey;
            
        } catch (UnrecoverableKeyException | CertificateException |
                 KeyStoreException | NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }

        
        return null;
    }
    
    /**
     * Generates a seceret key. The key size is set to 128 bits.
     * @return a generated secretkey
     * @throws NoSuchAlgorithmException
     */
    public SecretKey generateKey() throws NoSuchAlgorithmException {
        //Key size
        int n = 128;
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    public String encrypt(String strToEncrypt, String alias) 
        throws NoSuchPaddingException, NoSuchAlgorithmException,
        InvalidAlgorithmParameterException, InvalidKeyException,
        BadPaddingException, IllegalBlockSizeException {

        SecretKey secretKey = generateKey();
    
        Cipher cipher = Cipher.getInstance(ALGORTIHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        try {
            storeToKeyStore(alias, secretKey);
        } catch (KeyStoreException | CertificateException | IOException e) {
            e.printStackTrace();
        }
        byte[] cipherText = cipher.doFinal(strToEncrypt.getBytes(Charset.forName("UTF-8")));
        return Base64.getEncoder()
            .encodeToString(cipherText);
    }

    public String decrypt(String cipherText, String alias) 
        throws NoSuchPaddingException, NoSuchAlgorithmException,
        InvalidAlgorithmParameterException, InvalidKeyException,
        BadPaddingException, IllegalBlockSizeException {

        SecretKey secretKey = loadFromKeyStore(alias);
    
        Cipher cipher = Cipher.getInstance(ALGORTIHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
            .decode(cipherText));
        return new String(plainText, StandardCharsets.UTF_8);
    }
}
