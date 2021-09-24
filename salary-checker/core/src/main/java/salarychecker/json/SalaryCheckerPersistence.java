package salarychecker.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import salarychecker.core.Accounts;
import salarychecker.core.User;

/**
 * Class for persistence using jackson serializer and deserializer
 */
public class SalaryCheckerPersistence {

  private ObjectMapper mapper;

  public SalaryCheckerPersistence() {
    mapper = new ObjectMapper();
    //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.registerModule(new SalaryCheckerModule());
  }

  public User readUser(Reader reader) throws IOException {
    return mapper.readValue(reader, User.class);
  }

  public void writeUser(User user, Writer writer) throws IOException {
    mapper.writerWithDefaultPrettyPrinter().writeValue(writer, user);
  }

  private Path saveFilePath = null;

  public void setSaveFile(String saveFile) {
    this.saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);
  }

  /**
   * Loads a User from the saved file (saveFilePath) in the user.home folder.
   *
   * @return the loaded User
   */
  public User loadUser() throws IOException, IllegalStateException {
    if (saveFilePath == null) {
      throw new IllegalStateException("Save file path is not set, yet");
    }
    try (Reader reader = new FileReader(saveFilePath.toFile(), StandardCharsets.UTF_8)) {
      return readUser(reader);
    }
  }

  /**
   * Saves a User to the saveFilePath in the user.home folder.
   *
   * @param user the User to save
   */
  public void saveUser(User user) throws IOException, IllegalStateException {
    if (saveFilePath == null) {
      throw new IllegalStateException("Save file path is not set, yet");
    }
    try (Writer writer = new FileWriter(saveFilePath.toFile(), StandardCharsets.UTF_8)) {
      writeUser(user, writer);
    }
  }

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

  public Accounts loadAccounts() throws IOException, IllegalStateException {
    if (saveFilePath == null) {
      throw new IllegalStateException("Save file path is not set, yet");
    }
    try (Reader reader = new FileReader(saveFilePath.toFile(), StandardCharsets.UTF_8)) {
      return readAccounts(reader);
    }
  }

  public Accounts readAccounts(Reader reader) throws IOException {
    return mapper.readValue(reader, Accounts.class);
  }

  public static void main(String[] args) throws IOException {
    User user = new User("firstname", "lastname", "email", "password", 55555555555L, 55555, "employerEmail", 30.0);
    User user3 = new User("firstname2", "w", "  w", " ", 222222222222L, 333333, "employerE2mail", 23.0);
    
    Accounts accounts = new Accounts();
    accounts.addUser(user);
    accounts.addUser(user3);

    SalaryCheckerPersistence salaryCheckerPersistence = new SalaryCheckerPersistence();
    salaryCheckerPersistence.setSaveFile("SaveTest.json");
    salaryCheckerPersistence.saveAccounts(accounts);

    Accounts accounts2 = salaryCheckerPersistence.loadAccounts();
    System.out.println(accounts.getAccounts());
    System.out.println(accounts2.getAccounts());
  }
}
