package shared.models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Fine extends Model {

    public static String FineView = "isPaid, days, amount";


    @Id
    @GeneratedValue
    public Integer id;

    public boolean isPaid;
    public Integer days, amount;

    @ManyToOne
    public BookIssued bookIssued;

    public Integer getId() {
        return id;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public Integer getDays() {
        return days;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BookIssued getBook_issue() {
        return bookIssued;
    }

    public void setBook_issue(BookIssued book_issue) {
        this.bookIssued = book_issue;
    }
}
