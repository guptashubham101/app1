package shared.models;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Student extends Model {

    @Id
    @GeneratedValue
    public Integer id;

    public Integer semester, year;

    public String studentName, rollNumber, studentEmail, password, branch;

    @CreatedTimestamp
    public Date createdOn;

    @OneToMany
    public List<BookIssued> bookIssuedList;

    @OneToMany
    public List<Fine> fineList;

    public List<Fine> getFineList() {
        return fineList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentRollNumber(String studentRollNumber) {
        this.rollNumber = studentRollNumber;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setBookIssuedList(List<BookIssued> bookIssuedList) {
        this.bookIssuedList = bookIssuedList;
    }

    public void setFineList(List<Fine> fineList) {
        this.fineList = fineList;
    }

    public static void setFind(Finder<Integer, Student> find) {
        Student.find = find;
    }

    public static Finder<Integer, Student> find = new Finder<>(Student.class);

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setEmail(String email) {
        this.studentEmail = email;
    }

    public String getName() {
        return studentName;
    }

    public void setName(String firstName) {
        this.studentName = firstName;
    }

    public void setCurrentSemester(Integer currentSemester) {
        this.semester = currentSemester;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return studentEmail;
    }

    public String getPassword() {
        return password;
    }

    public Integer getSemester() {
        return semester;
    }

    public Integer getYear() {
        return year;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentRollNumber() {
        return rollNumber;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getBranch() {
        return branch;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public List<BookIssued> getBookIssuedList() {
        return bookIssuedList;
    }

    public static Finder<Integer, Student> getFind() {
        return find;
    }


}
