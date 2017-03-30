package shared.models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Books extends Model{

    @Id
    @GeneratedValue
    public Integer id;

    public Integer ISBN, quantity;
    public String name, author, domain;
    public boolean isIssued, availibility;

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

    public Integer getISBN() {
        return ISBN;
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
        return availibility;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setISBN(Integer ISBN) {
        this.ISBN = ISBN;
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
        this.availibility = availibility;
    }
}


