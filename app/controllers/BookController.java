package controllers;

import com.avaje.ebean.Ebean;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import play.libs.Json;
import play.mvc.Result;
import shared.models.*;
import java.sql.Timestamp;
import java.util.*;
import static com.avaje.ebean.Ebean.json;
import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;


public class BookController {

    public Result issueBook() {
        try {

            JsonNode json = request().body().asJson();
            String admin_id = json.findPath("admin_id").textValue();
            String roll_number = json.findPath("roll_number").textValue();
            String isbn = json.findPath("isbn").textValue();

            Books book = Ebean.find(Books.class).where().conjunction().eq("isbn",isbn).findUnique();
            Student student = Ebean.find(Student.class).where().conjunction().eq("rollNumber",roll_number).findUnique();
            LibraryAdmin admin = Ebean.find(LibraryAdmin.class).where().conjunction().eq("email",admin_id).findUnique();

            if (book.isAvailibility())
            {
                BookIssued issueBook = new BookIssued();
                issueBook.setLibraryAdmin(admin);
                issueBook.setStudent(student);
                issueBook.setBooks(book);
                issueBook.setDateOfIssue(new Timestamp(System.currentTimeMillis()));
                issueBook.setDateOfSubmission(new Timestamp(System.currentTimeMillis() +  (1000*60*60)*360));
                issueBook.save();
                book.setQuantity(book.getQuantity() - 1);
                if(book.getQuantity() < 1) {
                    book.setAvailibility(false);
                    book.save();
                }
                book.save();
                return ok(Json.toJson("Book issued"));
            }
            else{
                return ok(json().toJson("Book out of stock"));
            }

        } catch (Exception e) {

            e.printStackTrace();
            return badRequest(Json.toJson("hhh"));
        }
    }

    public Result feesPaid() {
        try {

            JsonNode json = request().body().asJson();

            String roll_number = json.findPath("roll_number").textValue();
            String isbn = json.findPath("isbn").textValue();

            Books book = Ebean.find(Books.class).where().conjunction().eq("isbn",isbn).findUnique();
            Student student = Ebean.find(Student.class).where().conjunction().eq("rollNumber",roll_number).findUnique();

            Fine fine = Ebean.find(Fine.class).where().conjunction().eq("student",student).eq("books",book).findUnique();
            if (!fine.isPaid())
            {
                fine.setPaid(true);
                fine.save();
                return ok(Json.toJson("Fine Paid"));
            }
            else{
                return ok(Json.toJson("Fine already paid"));
            }

        } catch (Exception e) {

            e.printStackTrace();
            return badRequest(Json.toJson("hhh"));
        }
    }

    public Result addBook()  {
        try{

            String values = request().body().asJson().toString();
            System.out.println(values);

            Books book = new ObjectMapper().readValue(values, Books.class);
            System.out.println(book.getQuantity());
            if (book.getQuantity() > 0)
                book.setAvailibility(true);
            else
                book.setAvailibility(false);

            book.insert();
            book = Ebean.find(Books.class).select(Books.BookView).where().idEq(book.id).findUnique();

            return ok(Json.toJson(book));

        } catch (Exception e){

            e.printStackTrace();
            return badRequest(Json.toJson("hh"));
        }
    }
}





