package testHelpers;

import org.junit.Assert;
import utils.Utils;

import java.io.IOException;

public class AuthorizationTestHelper {

    public static String getConfirmationLink(String email) {
        String emailMessage = null;

        try {
            emailMessage = Utils.getTestEmailMessage(Utils.strToMd5Hex(email));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (emailMessage == null) {
            Assert.fail();
        }

        return Utils.getConfirmationLink(emailMessage);
    }
}
