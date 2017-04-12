package shared.models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class BookIssued extends Model {

    public static String BookIssuedView = "dateOfSubmission, dateOfIssue";

    @Id
    @GeneratedValue
    public Integer id;

    public Timestamp dateOfSubmission, dateOfIssue;

    private boolean isSubmitted;

    @ManyToOne
    public Books books;

    @ManyToOne
    public Student student;

    @ManyToOne
    public LibraryAdmin libraryAdmin;

    @OneToMany
    public List<Fine> fineList;

    public boolean isSubmitted() {
        return isSubmitted;
    }

    public void setSubmitted(boolean submitted) {
        isSubmitted = submitted;
    }

    public Integer getId() {
        return id;
    }

    public Timestamp getDateOfSubmission() {
        return dateOfSubmission;
    }

    public Timestamp getDateOfIssue() {
        return dateOfIssue;
    }

    public Books getBooks() {
        return books;
    }

    public Student getStudent() {
        return student;
    }

    public LibraryAdmin getLibraryAdmin() {
        return libraryAdmin;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDateOfSubmission(Timestamp dateOfSubmission) {
        this.dateOfSubmission = dateOfSubmission;
    }

    public void setDateOfIssue(Timestamp dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Fine> getFineList() {
        return fineList;
    }

    public void setFineList(List<Fine> fineList) {
        this.fineList = fineList;
    }

    public void setLibraryAdmin(LibraryAdmin libraryAdmin) {
        this.libraryAdmin = libraryAdmin;
    }
}
