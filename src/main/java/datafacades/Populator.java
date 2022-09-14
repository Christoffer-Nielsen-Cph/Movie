/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datafacades;

import javax.persistence.EntityManagerFactory;

import entities.Movie;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        IDataFacade pf = MovieFacade.getMovieFacade(emf);
        Movie m1 = new Movie(2022,"Top Gun");
        Movie m2 = new Movie(2005,"Terminator");


        pf.create(m1);
        pf.create(m2);

    }
    
    public static void main(String[] args) {
        populate();
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        IDataFacade mf = MovieFacade.getMovieFacade(emf);
        mf.getAll().forEach(System.out::println);
    }
}
