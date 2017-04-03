package controllers;

import com.avaje.ebean.Ebean;

import com.fasterxml.jackson.databind.ObjectMapper;
import filters.Hash;
import play.libs.Json;
import play.mvc.Result;

import shared.models.*;
import play.data.Form;
import java.util.*;

import static com.avaje.ebean.Ebean.json;
import play.mvc.Http;

import static com.avaje.ebean.Ebean.update;
import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
public class AccessController {

    public Result studentRegister()  {
        try {
            Form<Student> form = Form.form(Student.class).bindFromRequest();

            if(form.data().get("user_type").equals("student")) {
                Student updateStudent = form.get();
                System.out.println(updateStudent);
                updateStudent.insert();
            }
            else
            {
                Form<LibraryAdmin> admin = Form.form(LibraryAdmin.class).bindFromRequest();
                LibraryAdmin libraryAdmin = admin.get();
                libraryAdmin.insert();
                libraryAdmin.setPassword(Hash.generateHash(libraryAdmin.getPassword()));
                libraryAdmin.save();
            }


            //Student student = Ebean.find(Student.class).where()
              //      .eq("registrationToken", updateStudent.registrationToken).findUnique();
            /*
            if (student != null) {
                long expiryTime = student.tokenCreatedOn.getTime() + (1000*60*60);
                if(System.currentTimeMillis() > expiryTime)
                    return ok(Json.toJson("Registration Token expired. Try Again."));
                else {
                    Http.MultipartFormData body = request().body().asMultipartFormData();
                    Http.MultipartFormData.FilePart image = body.getFile("imageUrl");
                    if (image != null) {
                        if (ImageUtil.isImage(image)) {
                            return ok(resultBuilder(false, "Please upload right format of image"));
                        }
                        student.setImageUrl(ImageUtil.save(image, Constants.keys.studentKey));
                    }

                    student.setEmail(updateStudent.email);
                    student.setMobileNumber(updateStudent.mobileNumber);
                    student.setPassword(Hash.generateHash(updateStudent.password));
                    student.setIsClaimed(true);
                    if (form.field("date").value() != null) {
                        student.setDateOfBirth(DateMapper.dateMapper(form.field("date").value()));
                    }

                    String emailToken = LoginUtil.generateRandomHash();
                    student.setEmailToken(emailToken);
                    student.setEmailTokenCreatedOn(new Timestamp(System.currentTimeMillis()));
                    student.setIsEmailVerified(false);
                    student.update();

                    String verifyEmailUrl;

                    if(System.getenv("SERVER").equals("PRODUCTION")) {
                        verifyEmailUrl = Constants.server.productionBackendUrl + "student/email/reset?emailToken=" +
                                student.getEmailToken() + "&email=" + student.getEmail();
                    } else {
                        verifyEmailUrl = Constants.server.stagingBackendUrl + "student/email/reset?emailToken=" +
                                student.getEmailToken() + "&email=" + student.getEmail();
                    }

                    // Send the welcome Email
                    String welcomeEmailBody = "<br><br><br>Hi " + student.getFirstName() +
                            "<br><br>Welcome to acadview. " +
                            "We are dedicated to help you in creating amazing career opportunities for you " +
                            "through our programs, scholarships and mentorship.<br><br>Acadview programs can " +
                            "make you ready for internships and jobs with startups with 30-45 days. " +
                            "Just visit our <a href=\"https://acadview.com/programs\">programs</a> " +
                            "page to know more.<br><br>Please take a moment to verify " +
                            "<a href=\"" + verifyEmailUrl + "\">Verify</a>" + " this email address." +
                            "<br><br>Really excited to have you onboard.<br><br><br><br>Thanks," +
                            "<br><br><br><br>Himanshu Batra,<br><br><br><br>CEO, AcadView";

                    String welcomeEmailSubject = "Welcome to Acadview";

                    EmailManager.sendMailWrapper(student.getEmail(), welcomeEmailSubject,
                            welcomeEmailBody, Constants.emailType.registration);
                    return ok(resultBuilder(true, "The student is registered"));
                }
            } else {
                return ok(Json.toJson("Student doesn't exist. Try Again."));
            }
            */
            return ok(Json.toJson("Student doesn't exist. Try Again."));
        } catch (Exception e) {

            e.printStackTrace();
            return badRequest(Json.toJson("hgg"));
        }
    }
}
