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
    public String adminId;

    @OneToMany
    public List<BookIssued> bookIssuedList;

    public String studentName, password;

    public String getName() {
        return studentName;
    }

    public void setName(String firstName) {
        this.studentName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminId() {
        return adminId;
    }

    public List<BookIssued> getBookIssuedList() {
        return bookIssuedList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void setBookIssuedList(List<BookIssued> bookIssuedList) {
        this.bookIssuedList = bookIssuedList;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getPassword() {
        return password;
    }
}

