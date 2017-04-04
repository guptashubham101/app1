package shared.models;


import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class LibraryAdmin extends Model {

    public static String LibraryAdminView = "email, name";

    @Id
    @GeneratedValue
    public Integer id;
    public String email;

    @OneToMany
    public List<BookIssued> bookIssuedList;

    public String name, password;

    Finder<Integer,LibraryAdmin> finder = new Finder<Integer, LibraryAdmin>(LibraryAdmin.class);

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<BookIssued> getBookIssuedList() {
        return bookIssuedList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBookIssuedList(List<BookIssued> bookIssuedList) {
        this.bookIssuedList = bookIssuedList;
    }

    public String getPassword() {
        return password;
    }
}

