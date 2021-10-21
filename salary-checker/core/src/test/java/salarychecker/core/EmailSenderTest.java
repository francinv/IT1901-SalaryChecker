package salarychecker.core;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import salarychecker.core.*;


/**
 * Class for testing AdminUser
 * @author hammads
 */

public class EmailSenderTest{ 
    @Test
    public void testsendMail(){

        assertNotNull(EmailSender.myAccountEmail);
        assertNotNull(EmailSender.password);
    }
}
    @Test
    public void testPrepareMessage{

        assertThrows(message, ex)
    }
}
}
