import Models.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String args[]) {


        //Insert a new persona
        insertPersona("12345678A", "Juan");
        //Update the name of the persona with dni 12345678A
        updatePersona("12345678A", "Juan Perez");
        //Find the persona with dni 12345678A
        Persona p = findPersona("12345678A");
        if (p != null) {
            System.out.println(p.getNombre());
        }
    }

   //Create method to update persona object with dni and name parameters
    public static void updatePersona(String dni, String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Persona persona = em.find(Persona.class, dni);
        persona.setNombre(name);
        tx.commit();
        em.close();
        emf.close();
    }
    //Create method to insert a new persona object
    private static boolean insertPersona(final String dni, final String nombre) {
        //Create an EntityManagerFactory object
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        //Create an EntityManager and a Transaction
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            //Create a new object
            Persona p = new Persona();
            p.setDni(dni);
            p.setNombre(nombre);
            em.persist(p);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (tx.isActive()) {
                //Rollback the transaction
                tx.rollback();
            }

            em.close();
            emf.close();
        }
        return false;
    }
    //Create method to find a persona object
    private static Persona findPersona(final String dni) {
        //Create an EntityManagerFactory object
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        //Create an EntityManager and a Transaction
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            //Create a new object
            Persona p = em.find(Persona.class, dni);
            tx.commit();
            return p;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (tx.isActive()) {
                //Rollback the transaction
                tx.rollback();
            }

            em.close();
            emf.close();
        }
        return null;
    }
    //Create method to delete a persona object
    private static boolean deletePersona(final String dni) {
        //Create an EntityManagerFactory object
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        //Create an EntityManager and a Transaction
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            //Create a new object
            Persona p = em.find(Persona.class, dni);
            em.remove(p);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (tx.isActive()) {
                //Rollback the transaction
                tx.rollback();
            }

            em.close();
            emf.close();
        }
        return false;
    }

}

