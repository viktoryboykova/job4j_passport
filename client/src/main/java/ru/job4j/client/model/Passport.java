package ru.job4j.client.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import ru.job4j.client.validation.Operation;

@Entity
@Table(name = "passport")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1, message = "id must be more than 0", groups = {
            Operation.OnUpdate.class, Operation.OnDelete.class
    })
    private int id;

    @NotNull(message = "seria must be non null")
    private String seria;

    @NotNull(message = "number must be non null")
    private String number;

    @Column(name = "expiry_date")
    private Date expiryDate;

    public Passport() {
    }

    public Passport(String seria, String number, Date expiryDate) {
        this.seria = seria;
        this.number = number;
        this.expiryDate = expiryDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeria() {
        return seria;
    }

    public void setSeria(String seria) {
        this.seria = seria;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passport passport = (Passport) o;
        return Objects.equals(seria, passport.seria) && Objects.equals(number, passport.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seria, number);
    }
}
