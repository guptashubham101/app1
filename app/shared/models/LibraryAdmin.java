package shared.models;


import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class LibraryAdmin extends Model {

    @Id
    @GeneratedValue
    public Integer id;
    public String email;

    @OneToMany
    public List<BookIssued> bookIssuedList;

    public String name, password;

    public Integer getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminId() {
        return email;
    }

    public List<BookIssued> getBookIssuedList() {
        return bookIssuedList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAdminId(String adminId) {
        this.email = adminId;
    }

    public void setBookIssuedList(List<BookIssued> bookIssuedList) {
        this.bookIssuedList = bookIssuedList;
    }

    public String getPassword() {
        return password;
    }
}

