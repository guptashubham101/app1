package shared.models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Books extends Model{

    public static String BookView = "isbn, quantity, name, author, domain, isIssued, availability";

    @Id
    @GeneratedValue
    public Integer id;

    private Integer quantity;
    private String name, author, domain, isbn;
    private boolean isIssued,  availability;

    Finder<Integer,Books> finder = new Finder<Integer, Books>(Books.class);

    @OneToMany
    public List<BookIssued> bookIssuedList;

    @OneToMany
    public List<Fine> fineList;

    public List<Fine> getFineList() {
        return fineList;
    }

    public void setFineList(List<Fine> fineList) {
        this.fineList = fineList;
    }

    public List<BookIssued> getBookIssuedList() {
        return bookIssuedList;
    }

    public Integer getId() {

        return id;
    }

    public String getisbn() {
        return isbn;
    }

    public void setBookIssuedList(List<BookIssued> bookIssuedList) {
        this.bookIssuedList = bookIssuedList;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getDomain() {
        return domain;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public boolean isAvailibility() {
        return  availability;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setisbn(String isbn) {
        this.isbn = isbn;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    public void setAvailibility(boolean availibility) {
        this. availability = availibility;
    }
}


