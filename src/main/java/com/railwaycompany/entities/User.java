package com.railwaycompany.entities;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

    private Long id;
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private String birth_date;

    public User() {
    }

    public User(String email, String password, String first_name, String last_name, String birth_date) {
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Email
    @NotEmpty
    @Column(name = "EMAIL", unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    @Column(name = "PASSWORD", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    @Column(name = "FIRST_NAME", unique = true, length = 50, nullable = false)
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String firstName) {
        this.first_name = firstName;
    }

    @NotNull
    @Column(name = "LAST_NAME", length = 50, nullable = false)
    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String lastName) {
        this.last_name = lastName;
    }

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
//    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @Column(name = "BIRTH_DATE", nullable = false)
    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birthday) {
        this.birth_date = birthday;
    }

    @Override
    public String toString() {
        String sb = "ID: " + this.id +
                "\nEMAIL: " + this.email +
                "\nPASSWORD: " + this.password +
                "\nFIRST_NAME: " + this.first_name +
                "\nLAST_NAME: " + this.last_name +
                "\nBIRTH_DATE: " + this.birth_date;
        return sb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(first_name, user.first_name) &&
                Objects.equals(last_name, user.last_name) &&
                Objects.equals(birth_date, user.birth_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, first_name, last_name, birth_date);
    }
}