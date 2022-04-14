package ru.job4j.mail.model;

import java.util.Date;
import java.util.Objects;

public class Passport {
    private int id;

    private String seria;

    private String number;

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
