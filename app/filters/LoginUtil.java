package filters;

import com.avaje.ebean.Ebean;
import shared.models.*;
import java.util.List;
import java.math.BigInteger;
import java.security.SecureRandom;

public class LoginUtil {

    public static LibraryAdmin isValidUser(String email, String password) {
        try {
            List<LibraryAdmin> libraryAdminList = Ebean.find(LibraryAdmin.class).where().conjunction()
                    .ieq("email", email).findList();
            System.out.println("1");
            System.out.println(libraryAdminList);
            if (libraryAdminList == null)
                return null;

            LibraryAdmin libraryAdmin = null;
            System.out.println("2");

            libraryAdmin = libraryAdminList.get(0);

            System.out.println(libraryAdmin);
            if(libraryAdmin == null) {
                System.out.println("abc");
                return null;
            }
            else if (password.equals(libraryAdmin.password)) {
                System.out.println("def");
                libraryAdmin.setPassword(Hash.generateHash(libraryAdmin.password));
                libraryAdmin.update();
                return libraryAdmin;
            }
            else {
                System.out.println("ghi");
                String encryptedPass = Hash.generateHash(password);
                System.out.println(encryptedPass);
                if (encryptedPass.equals(libraryAdmin.password))
                    return libraryAdmin;
                else
                    return null;
            }
        } catch (Exception e){
            return null;
        }
    }

    public static String createSession(int userId) {

        Session session = new Session();
        session.sessionId = generateRandomHash();
        session.isActive = true;
        session.userId = userId;
        session.save();

        return session.sessionId;
    }

    public static boolean clearSession(String sessionId) {
        System.out.println(sessionId);
        Session session = Ebean.find(Session.class).where().conjunction().eq("session_id", sessionId)
                .eq("is_active", true).findUnique();
        System.out.println(session);
        if (session != null) {
            System.out.println("123");
            session.setActive(false);
            session.update();
            return true;
        } else {
            System.out.println("345");
            return false;
        }
    }

    public static String generateRandomHash() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
}
