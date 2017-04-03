package controllers;

import com.avaje.ebean.Ebean;

import com.fasterxml.jackson.databind.ObjectMapper;
import play.libs.Json;
import play.mvc.Result;

import shared.models.*;

import java.util.*;

import static com.avaje.ebean.Ebean.json;
import play.mvc.Http;

import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;


public class BookController {

    public Result studentList(Integer no) {
        try {
            System.out.println(no);
            List<Books> bookList = Ebean.find(Books.class)
                    .where().conjunction().eq("quantity", no).findList();

            return ok(json().toJson(bookList));
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





