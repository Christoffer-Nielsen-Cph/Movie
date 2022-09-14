package datafacades;

import entities.Movie;
import errorhandling.EntityNotFoundException;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * created by THA
 * Purpose of this facade example is to show a facade used as a DB facade (only operating on entity classes - no DTOs
 * And to show case some different scenarios
 */
public class MovieFacade implements IDataFacade<Movie> {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MovieFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static IDataFacade<Movie> getMovieFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Movie create(Movie m){
        EntityManager em = getEntityManager();
        Movie movie = new Movie(m.getYear(),m.getTitle());
        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return movie;
    }

    @Override
    public Movie getById(int id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        Movie m = em.find(Movie.class, id);
        if (m == null)
            throw new EntityNotFoundException("The Movie entity with ID: "+id+" Was not found");
        return m;
    }

    @Override
    public List<Movie> getAll(){
        EntityManager em = getEntityManager();
        TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m", Movie.class);
        List<Movie> movies = query.getResultList();
        return movies;
    }

    @Override
    public Movie update(Movie movie) throws EntityNotFoundException {
        if (movie.getId() == 0)
            throw new IllegalArgumentException("No Movie can be updated when id is missing");
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Movie m = em.merge(movie);
        em.getTransaction().commit();
        return m;
    }

    @Override
    public Movie delete(int id) throws EntityNotFoundException{
        EntityManager em = getEntityManager();
        Movie m = em.find(Movie.class, id);
        if (m == null)
            throw new EntityNotFoundException("Could not remove Movie with id: "+id);
        em.getTransaction().begin();
        em.remove(m);
        em.getTransaction().commit();
        return m;
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        IDataFacade fe = getMovieFacade(emf);
        fe.getAll().forEach(dto->System.out.println(dto));
    }
}
