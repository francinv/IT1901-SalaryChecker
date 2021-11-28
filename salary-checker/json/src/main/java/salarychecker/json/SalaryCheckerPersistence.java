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
import salarychecker.json.internal.SalaryCheckerModule;

/**
 * Class for persistence using jackson serializer and deserializer.
 */
public class SalaryCheckerPersistence {

  private final ObjectMapper mapper;
  private Path filePath;

  public SalaryCheckerPersistence() {
    mapper = createObjectMapper();
  }

  public static SimpleModule createJacksonModule() {
    return new SalaryCheckerModule();
  }

  public static ObjectMapper createObjectMapper() {
    return new ObjectMapper().registerModule(createJacksonModule());
  }

  public void setFilePath(String fileName) {
    String filePath = "/.salarychecker/"+fileName;
    this.filePath = Paths.get(System.getProperty("user.home"), filePath);
  }

  /**
   * Loads Accounts from the saved file (saveFilePath) in the user.home folder.
   *
   * @return the loaded Accounts
   */
  public Accounts loadAccounts() throws IOException, IllegalStateException {
    if (filePath == null) {
      throw new IllegalStateException("Save file path is not set, yet");
    }
    try (Reader reader = new FileReader(filePath.toFile(), StandardCharsets.UTF_8)) {
      return readAccounts(reader);
    }
  }
  
  /**
   * Saves a User to the saveFilePath in the user.home folder.
   *
   * @param accounts the Accounts to save
   */
  public void saveAccounts(Accounts accounts) throws IOException, IllegalStateException {
    if (filePath == null) {
      throw new IllegalStateException("Save file path is not set, yet");
    }
    try (Writer writer = new FileWriter(filePath.toFile(), StandardCharsets.UTF_8)) {
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