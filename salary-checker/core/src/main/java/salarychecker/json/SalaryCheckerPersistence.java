package salarychecker.json;

import com.fasterxml.jackson.databind.MapperFeature;
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

/**
 * Class for persistence using jackson serializer and deserializer
 */
public class SalaryCheckerPersistence {

  private ObjectMapper mapper;

  public SalaryCheckerPersistence() {
    mapper = new ObjectMapper();
    //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.registerModule(new SalaryCheckerModule());
  }

  private Path saveFilePath = null;

  public void setSaveFile(String saveFile) {
    this.saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);
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
}
