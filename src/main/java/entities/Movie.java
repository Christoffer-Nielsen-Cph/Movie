package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Movie.deleteAllRows", query = "DELETE from Movie"),
        @NamedQuery(name = "Movie.getAll", query = "SELECT m FROM Movie m"),
        @NamedQuery(name = "Movie.getByTitle", query = "SELECT m FROM Movie m WHERE m.title LIKE :title")
})

public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "year")
    private int year;

    @Column(name = "title")
    private String title;

   /* @ElementCollection
    @CollectionTable(name = "actor_list",joinColumns = @JoinColumn(name="id"))
    @Column(name = "actors")
    private List<String> actors; */

    private String[] actors;

    public Movie() {
    }

    public Movie(int year, String title, List<String> actors) {
        this.year = year;
        this.title = title;
        this.actors = actors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", year=" + year +
                ", title='" + title + '\'' +
                ", actors=" + actors +
                '}';
    }
}