package shared.models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
public class BookIssued extends Model {

    @Id
    @GeneratedValue
    public Integer id;

    public Timestamp dateOfSubmission, dateOfIssue;

    @ManyToOne
    public Books books;

    @ManyToOne
    public Student student;

    @ManyToOne
    public LibraryAdmin libraryAdmin;

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

    public void setLibraryAdmin(LibraryAdmin libraryAdmin) {
        this.libraryAdmin = libraryAdmin;
    }
}
