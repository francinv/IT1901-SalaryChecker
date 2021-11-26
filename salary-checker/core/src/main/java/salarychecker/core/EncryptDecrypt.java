package salarychecker.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
 * Class to encrypt and decrypt user information.
 *
 * @author serans
 * @author jakobk
 * @author francinv
 */
public class EncryptDecrypt {

  private KeyStore keyStore;
  private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

  /**
   * Constructor initziate key store object.
   */
  public EncryptDecrypt() {
    try {
      keyStore = KeyStore.getInstance("JCEKS");
    } catch (KeyStoreException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Access method for keystore.
   *
   * @return the keystore
   */
  public KeyStore getKeyStore() {
    return keyStore;
  }

  /**
   * Creates a keystore to store the generated secretKey
   * Uses the users name as alias.
   *
   * @param alias the alias
   * @param secretKey the secret key
   */
  public void storeToKeyStore(String alias, SecretKey secretKey) {

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
    } catch (IOException | NoSuchAlgorithmException 
      | CertificateException | KeyStoreException e) {
      System.out.println(e.getMessage());
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

  /**
   * Method to load secretKey from keyStore.
   *
   * @param alias the alias
   * @return the secretKey
   */
  public SecretKey loadFromKeyStore(String alias) {

    String path = System.getProperty("user.home") + "/SalarycheckerKeystore.jks";
    char[] jksPassword = "changeit".toCharArray();

    try {
      InputStream readStream = new FileInputStream(path);
      keyStore.load(readStream, jksPassword);
      SecretKey secretKey = (SecretKey) keyStore.getKey(alias, jksPassword);
      readStream.close();
      return secretKey;

    } catch (UnrecoverableKeyException | CertificateException 
        | KeyStoreException | NoSuchAlgorithmException | IOException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  /**
   * Generates a seceret key. The key size is set to 128 bits.
   *
   * @return a generated secretKey
   */
  public SecretKey generateKey() {
    //Key size
    int n = 128;
    KeyGenerator keyGenerator;
    SecretKey key = null;
    try {
      keyGenerator = KeyGenerator.getInstance("AES");
      keyGenerator.init(n);
      key = keyGenerator.generateKey();
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      System.out.println(e.getMessage());
    }
    return key;
  }

  /**
   * Encrypts a string.
   *
   * @param strToEncrypt the string to encrypt
   * @param alias the alias used to save in key store
   * @return the string encrypted
   */
  public String encrypt(String strToEncrypt, String alias) {

    SecretKey secretKey = generateKey();

    Cipher cipher;
    byte[] cipherText = null;
    try {
      cipher = Cipher.getInstance(ALGORITHM);
      cipher.init(Cipher.ENCRYPT_MODE, secretKey);
      storeToKeyStore(alias, secretKey);
      cipherText = cipher.doFinal(strToEncrypt.getBytes(Charset.forName("UTF-8")));
    } catch (NoSuchAlgorithmException | NoSuchPaddingException 
       | InvalidKeyException | IllegalBlockSizeException 
       | BadPaddingException e) {
      System.out.println(e.getMessage());
    }
    
    return Base64.getEncoder()
      .encodeToString(cipherText);
  }

  /**
   * Decrypts a string.
   *
   * @param cipherText the string to decrypt
   * @param alias the alias used to save in key store
   * @return the string decrypted
   */
  public String decrypt(String cipherText, String alias) {

    SecretKey secretKey = loadFromKeyStore(alias);

    Cipher cipher;
    byte[] plainText = null;
    try {
      cipher = Cipher.getInstance(ALGORITHM);
      cipher.init(Cipher.DECRYPT_MODE, secretKey);
      plainText = cipher.doFinal(Base64.getDecoder()
        .decode(cipherText));
    } catch (NoSuchAlgorithmException | NoSuchPaddingException 
      | InvalidKeyException | IllegalBlockSizeException 
      | BadPaddingException e) {
      // TODO Auto-generated catch block
      System.out.println(e.getMessage());
    }
    return new String(plainText, StandardCharsets.UTF_8);
  }
}
