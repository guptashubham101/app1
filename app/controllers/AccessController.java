package controllers;

import com.avaje.ebean.Ebean;

import com.fasterxml.jackson.databind.JsonNode;
import filters.Hash;
import filters.LoginUtil;
import play.libs.Json;
import play.mvc.Result;
import shared.models.*;
import play.data.Form;
import java.util.*;
import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class AccessController {

    public Result userRegister()  {
        try {
            Form<Student> form = Form.form(Student.class).bindFromRequest();

            if(form.data().get("user_type").equals("student")) {
                Student student = form.get();
                System.out.println(student);
                student.insert();
                Student user = Ebean.find(Student.class).select(Student.StudentView).where().idEq(student.id).findUnique();
                return ok(Json.toJson(user));
            }
            else
            {
                Form<LibraryAdmin> admin = Form.form(LibraryAdmin.class).bindFromRequest();
                LibraryAdmin libraryAdmin = admin.get();
                libraryAdmin.insert();
                libraryAdmin.setPassword(Hash.generateHash(libraryAdmin.getPassword()));
                libraryAdmin.save();
                LibraryAdmin user = Ebean.find(LibraryAdmin.class).select(LibraryAdmin.LibraryAdminView).where().idEq(libraryAdmin.id).findUnique();
                return ok(Json.toJson(user));
            }
        } catch (Exception e) {

            e.printStackTrace();
            return badRequest(Json.toJson("hgg"));
        }
    }

    public Result userLogin()  {
        try {
            JsonNode json = request().body().asJson();
            String userId, password;
            if(json == null) {
                return badRequest("Expecting Json data");
            } else {
                userId = json.findPath("email").textValue();
                password = json.findPath("password").textValue();
            }

            LibraryAdmin libraryAdmin = LoginUtil.isValidUser(userId, password);
            if (libraryAdmin == null)
                return ok(Json.toJson("Your credentials are wrong"));

            String sessionId = LoginUtil.createSession(libraryAdmin.id);

            HashMap<String, Object> dataMap = new HashMap<>();
            dataMap.put("sessionId",sessionId);

            return ok(Json.toJson(dataMap));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ok(Json.toJson("error"));
        }
    }

    public Result logout() {
        try {
            JsonNode json = request().body().asJson();
            String sessionId = json.findPath("sessionId").textValue();

            if (LoginUtil.clearSession(sessionId)) {
                return ok(Json.toJson("User logged out successfully!"));
            } else {
                return ok(Json.toJson("Log out failed"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return badRequest(Json.toJson("error"));
        }
    }
}
