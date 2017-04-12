package controllers;

import com.avaje.ebean.Ebean;
import play.libs.Json;
import play.mvc.Result;
import shared.models.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import static com.avaje.ebean.Ebean.json;
import static play.mvc.Results.ok;
import static shared.models.Books.BookView;
import static shared.models.Fine.FineView;

public class StudentController {


    public Result listBook(String roll_number)  {
        try {
            System.out.println("2");

            Student student = Ebean.find(Student.class).where().conjunction().eq("rollNumber",roll_number).findUnique();

            List<BookIssued> bookList = Ebean.find(BookIssued.class).where().conjunction().eq("student",student).eq("isSubmitted",false).findList();

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

            List<BookIssued> booksList = Ebean.find(BookIssued.class).select(BookIssued.BookIssuedView).fetch("books",BookView).fetch("fineList",FineView).where().conjunction().eq("student", student).findList();

            objMap.put("student", student);
            objMap.put("list", booksList);

            return ok(json().toJson(objMap));
        } catch (Exception e) {

            e.printStackTrace();
            return ok(Json.toJson("hh"));
        }
    }
}





