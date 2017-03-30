package shared.models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Fine extends Model {

    @Id
    @GeneratedValue
    public Integer id;

    public boolean isPaid;
    public Integer days, amount;

    @ManyToOne
    public Books books;

    @ManyToOne
    public Student student;

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

    public Books getBooks() {
        return books;
    }

    public Student getStudent() {
        return student;
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

    public void setBooks(Books books) {
        this.books = books;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
