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
import static shared.models.Books.BookView;
import static shared.models.Fine.FineView;
import static shared.models.Student.StudentView;


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
                issueBook.setSubmitted(false);
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

            BookIssued issuedBook = Ebean.find(BookIssued.class).where().conjunction().eq("student",student).eq("books",book).findUnique();
            Fine fine = Ebean.find(Fine.class).where().conjunction().eq("bookIssued",issuedBook).findUnique();

            if (!fine.isPaid())
            {
                fine.setPaid(true);
                fine.save();
                issuedBook.setSubmitted(true);
                issuedBook.save();
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
            book = Ebean.find(Books.class).select(BookView).where().idEq(book.id).findUnique();

            return ok(Json.toJson(book));

        } catch (Exception e){

            e.printStackTrace();
            return badRequest(Json.toJson("hh"));
        }
    }

    public Result listBook(String isbn)  {
        try {
            System.out.println("2");

            Books book = Ebean.find(Books.class).where().conjunction().eq("isbn",isbn).findUnique();
            System.out.println(book.getisbn());
            List<BookIssued> bookList = Ebean.find(BookIssued.class).where().conjunction().eq("books",book).eq("isSubmitted",false).findList();

            for (BookIssued bookie : bookList)
            {
                int days = (int)((new Timestamp(System.currentTimeMillis()).getTime()-bookie.getDateOfSubmission().getTime())/(60*60*1000*24));
                int amount = days * 10;
                List<Fine> fine = Ebean.find(Fine.class).where().conjunction().eq("bookIssued",bookie).findList();
                if (fine.isEmpty()) {
                    Fine newFine = new Fine();
                    newFine.setBook_issue(bookie);
                    newFine.setPaid(false);
                    newFine.setAmount(amount);
                    newFine.setDays(days);
                    newFine.save();
                }
            }
            System.out.println("3456789");

            HashMap objMap = new HashMap();

            List<BookIssued> booksList = Ebean.find(BookIssued.class).select(BookIssued.BookIssuedView).fetch("student",StudentView).fetch("fineList",FineView).where().conjunction().eq("books", book).findList();

            objMap.put("book", book);
            objMap.put("list", booksList);
            
            return ok(json().toJson(objMap));
        } catch (Exception e) {

            e.printStackTrace();
            return ok(Json.toJson("hh"));
        }
    }

    public Result submitBook() {
        try {

            JsonNode json = request().body().asJson();

            String roll_number = json.findPath("roll_number").textValue();
            String isbn = json.findPath("isbn").textValue();

            Books book = Ebean.find(Books.class).where().conjunction().eq("isbn",isbn).findUnique();
            Student student = Ebean.find(Student.class).where().conjunction().eq("rollNumber",roll_number).findUnique();

            BookIssued issuedBook = Ebean.find(BookIssued.class).where().conjunction().eq("student",student).eq("books",book).findUnique();

            if (!issuedBook.isSubmitted())
            {
                issuedBook.setSubmitted(true);
                issuedBook.save();
                return ok(Json.toJson("Book submitted"));
            }
            else{
                return ok(Json.toJson("Book already submitted"));
            }

        } catch (Exception e) {

            e.printStackTrace();
            return badRequest(Json.toJson("hhh"));
        }
    }
}





