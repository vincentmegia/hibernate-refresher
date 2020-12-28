package com.vincentmegia.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Family {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "surname")
    private String surname;

    public Family(String id, String surname) {
        this.id = id;
        this.surname = surname;
    }

    public Family() { }

    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }
    public String getSurname() { return this.surname; }
    public void setSurname(String surname) { this.surname = surname; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return Objects.equals(id, family.id) &&
                Objects.equals(surname, family.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname);
    }
}
