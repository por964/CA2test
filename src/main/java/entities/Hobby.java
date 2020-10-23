package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "Hobby.deleteAllRows", query = "DELETE from Hobby"),
    @NamedQuery(name = "Hobby.getAllRows", query = "SELECT h from Hobby h")})
public class Hobby implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(length = 50)
    private String name;

    private String linkWiki;

    private String underCathegory;

    private String type;

    @ManyToMany
    private List<Person> persons;

    public Hobby() {
    }

    public Hobby(String wikiLink, String underCathegory, String type) {
        this.linkWiki = linkWiki;
        this.underCathegory = underCathegory;
        this.type = type;
        this.persons = new ArrayList<>();
    }

    public Hobby(String name, String linkWiki, String underCathegory, String type) {
        this.name = name;
        this.linkWiki = linkWiki;
        this.underCathegory = underCathegory;
        this.type = type;
        this.persons = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getLinkWiki() {
        return linkWiki;
    }

    public String getUnderCathegory() {
        return underCathegory;
    }

    public String getType() {
        return type;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

}
