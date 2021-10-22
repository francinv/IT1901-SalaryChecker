package salarychecker.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import salarychecker.core.Accounts;
import salarychecker.core.AdminUser;
import salarychecker.core.User;
import salarychecker.json.internal.SalaryCheckerModule;

/**
 * Class for persistence using jackson serializer and deserializer
 */
public class SalaryCheckerPersistence {

  private ObjectMapper mapper;

  public SalaryCheckerPersistence() {
    mapper = createObjectMapper();
  }

  public static SimpleModule createJacksonModule() {
    return new SalaryCheckerModule();
  }

  public static ObjectMapper createObjectMapper() {
    return new ObjectMapper().registerModule(createJacksonModule());
  }

  private Path saveFilePath = null;

  public void setSaveFile(String saveFile) {
    this.saveFilePath = Paths.get(System.getProperty("user.home") + "/Downloads/", saveFile);
  }

  /**
   * Loads Accounts from the saved file (saveFilePath) in the user.home folder.
   *
   * @return the loaded Accounts
   */
  public Accounts loadAccounts() throws IOException, IllegalStateException {
    if (saveFilePath == null) {
      throw new IllegalStateException("Save file path is not set, yet");
    }
    try (Reader reader = new FileReader(saveFilePath.toFile(), StandardCharsets.UTF_8)) {
      return readAccounts(reader);
    }
  }
  
  /**
   * Saves a User to the saveFilePath in the user.home folder.
   *
   * @param user the User to save
   */
  public void saveAccounts(Accounts accounts) throws IOException, IllegalStateException {
    if (saveFilePath == null) {
      throw new IllegalStateException("Save file path is not set, yet");
    }
    try (Writer writer = new FileWriter(saveFilePath.toFile(), StandardCharsets.UTF_8)) {
      writeAccounts(accounts, writer);
    }
  }

  public void writeAccounts(Accounts accounts, Writer writer) throws IOException {
    mapper.writerWithDefaultPrettyPrinter().writeValue(writer, accounts);
  }

  public Accounts readAccounts(Reader reader) throws IOException {
    return mapper.readValue(reader, Accounts.class);
  }

  public static void main(String[] args) {

    Accounts accounts = new Accounts();
    User testuser1 = new User("Seran", "Shanmugathas", "seran@live.no", "Password123!", "22030191349", 12345, "employeer1@gmail.com", 30.0, 130);
    AdminUser testuser2 = new AdminUser("Francin", "Vincent", "francin.vinc@gmail.com", "Vandre333!");
    accounts.addUser(testuser1);
    accounts.addUser(testuser2);

    SalaryCheckerPersistence scp = new SalaryCheckerPersistence();
    scp.setSaveFile("Accounts.json");
    try {
      scp.saveAccounts(accounts);
    } catch (IllegalStateException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
}
