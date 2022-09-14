package dtos;

import entities.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MovieDTO {
    private int id;
    private int year;
    private String title;

    public MovieDTO(Movie movie) {
        if(movie.getId()!=0)
            this.id = movie.getId();
        this.year = movie.getYear();
        this.title = movie.getTitle();
    }

    public static List<MovieDTO> toList(List<Movie> movies) {
        return movies.stream().map(MovieDTO::new).collect(Collectors.toList());
    }


    public Movie getEntity(){
        Movie m = new Movie(this.year, this.title);
        if(id != 0)
            m.setId(this.id);

        return m;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDTO movieDTO = (MovieDTO) o;
        return id == movieDTO.id && year == movieDTO.year && title.equals(movieDTO.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, title);
    }
}
