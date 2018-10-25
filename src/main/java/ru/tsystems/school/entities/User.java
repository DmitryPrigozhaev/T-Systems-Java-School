package ru.tsystems.school.entities;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birth_date;

    public User() {
    }

    public User(String email, String password, String firstName, String lastName, LocalDate birth_date) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Column(name = "LAST_NAME", length = 50, nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
//    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @Column(name = "BIRTH_DATE", nullable = false)
    public LocalDate getBirthday() {
        return birth_date;
    }

    public void setBirthday(LocalDate birthday) {
        this.birth_date = birthday;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("ID: ").append(this.id)
                .append("\nEMAIL: ").append(this.email)
                .append("\nPASSWORD: ").append(this.password)
                .append("\nFIRST_NAME: ").append(this.firstName)
                .append("\nLAST_NAME: ").append(this.lastName)
                .append("\nBIRTH_DATE: ").append(this.birth_date);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(birth_date, user.birth_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, firstName, lastName, birth_date);
    }
}