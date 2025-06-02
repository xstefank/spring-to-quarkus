package org.acme;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
@Table(name = "avengers")
public class Avenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Name cannot be blank")
    private String name;

    private String civilname;

    private boolean snapped;

    public Avenger() {}

    public Avenger(String name, String civilname, boolean snapped) {
        this.name = name;
        this.civilname = civilname;
        this.snapped = snapped;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCivilname() {
        return civilname;
    }

    public void setCivilname(String civilname) {
        this.civilname = civilname;
    }

    public boolean isSnapped() {
        return snapped;
    }

    public void setSnapped(boolean snapped) {
        this.snapped = snapped;
    }

    @Override
    public String toString() {
        return "Avenger{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", civilname='" + civilname + '\'' +
            ", snapped=" + snapped +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Avenger avenger)) return false;
        return isSnapped() == avenger.isSnapped() && Objects.equals(getId(), avenger.getId()) && Objects.equals(getName(), avenger.getName()) && Objects.equals(getCivilname(), avenger.getCivilname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCivilname(), isSnapped());
    }
}
