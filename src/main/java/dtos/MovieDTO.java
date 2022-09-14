package dtos;

import entities.Movie;
import entities.Parent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MovieDTO {
    private Long id;
    private int year;
    private String title;
    private List<String> actors = new ArrayList<>();

    public MovieDTO(Movie movie) {
        if(movie.getId()!=0)
            this.id = movie.getId();
        this.year = movie.getYear();
        this.title = movie.getTitle();
        this.actors = movie.getActors();
    }

    public static List<MovieDTO> toList(List<Movie> movies) {
        return movies.stream().map(MovieDTO::new).collect(Collectors.toList());
    }


    public Movie getEntity(){
        Movie m = new Movie(this.year, this.title,this.actors);
        if(id != 0)
            m.setId(this.id);

        return m;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
