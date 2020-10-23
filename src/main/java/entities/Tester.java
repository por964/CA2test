package entities;

import dto.PersonDTO;
import dto.PersonsDTO;
import exceptions.InputError;
import exceptions.MissingInputException;
import facades.PersonFacade;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.New;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * @author claes
 */
public class Tester {

    private static final EntityManagerFactory EMFC = EMF_Creator.createEntityManagerFactory();
    private static final PersonFacade FACADE = PersonFacade.getPersonFacade(EMFC);

    public static void main(String[] args) throws MissingInputException, InputError {
        
        
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

        EntityManager em = emf.createEntityManager();
        
        PersonDTO pdto1 = new PersonDTO("bg@ms.com", "Thrill", "Gates", "Strandvejen 1", "stuen", "2900", "Skak", 1234, "privat");
        PersonDTO pdto4 = new PersonDTO("bg@ms.com", "Torben", "Chris", "Munkedal 1", "B","2850", "Surfing", 1234, "kontor");
        
        PersonDTO pdto2 = new PersonDTO("hans@ms.com", "Hans", "Smith", "Strandvejen 48", "5.sal th", "2100", "Skak", 1122, "bil");
        PersonDTO pdto3 = new PersonDTO("bente@ms.com", "Bente", "Jensen", "Byvej 3", "kælderen", "2900", "Astrologi", 3344, "hjem");

        FACADE.addPerson(pdto1);
        FACADE.addPerson(pdto2);
        FACADE.addPerson(pdto3);
        FACADE.addPerson(pdto4);
        
        PersonsDTO psdto = new PersonsDTO();
        psdto = FACADE.getAllPersonsByHobby("skak");
        
              for(PersonDTO pd : psdto.getAll()) {
            System.out.println(pd);
        }
        
        
        
        
        //DATABASE SET-UP:
        /*
        PersonDTO p1 = new PersonDTO("bg@ms.com", "Bill", "Gates", "Strandvejen 1", "2900", "Skak", 22556633);
        PersonDTO p2 = new PersonDTO("hans@ms.com", "Hans", "Smith", "Strandvejen 48", "2100", "Skak", 44558899);
        PersonDTO p3 = new PersonDTO("bente@ms.com", "Bente", "Jensen", "Byvej 3", "2800", "Astrologi", 55336699);
        PersonDTO p4 = new PersonDTO("mariam1982@hotmail.com", "Maria", "Madsen", "Søvej 2", "4800", "Motorsport", 44778866);
        PersonDTO p5 = new PersonDTO("dave2004@hotmail.com", "David", "Eskelund", "Troelsgaardsvej 124B", "2820", "Motorsport", 22336655);
        PersonDTO p6 = new PersonDTO("kiarra1983@hotmail.com", "Kia", "Rasted", "Lysbygade 24", "4684", "Astrologi", 11223344);
        PersonDTO p7 = new PersonDTO("cornell1979@hotmail.com", "Chris", "Nelli", "Bygaden 1", "4700", "Bodybuilding", 22334455);
        PersonDTO p8 = new PersonDTO("fae1990@yahoo.com", "Frank", "Andersen", "Røvervænget 40", "2800", "Astrologi", 33445566);
        PersonDTO p9 = new PersonDTO("emmie19731988@yahoo.com", "Emmie", "Petersen", "Saksegade 3", "2100", "Skak", 44556677);
        PersonDTO p10 = new PersonDTO("mohammad_greenho@gmail.com", "Mohammad", "Jensen", "Øverupvej 583", "2880", "Bodybuilding", 55667788);
        
        FACADE.addPerson(p1);
        FACADE.addPerson(p2);
        FACADE.addPerson(p3);
        FACADE.addPerson(p4);
        FACADE.addPerson(p5);
        FACADE.addPerson(p6);
        FACADE.addPerson(p7);
        FACADE.addPerson(p8);
        FACADE.addPerson(p9);
        FACADE.addPerson(p10);
        */
        
        
        
//                PersonsDTO pdto = FACADE.getAllPersons();
//        pdto.getAll().forEach(p -> {
//            System.out.println("Person first name: " + p.getFname());
//        });
        
        /*
        
        */
        /*
        Person person = new Person();
        person.setFirstName(pdto1NewChanges.getFname());
        person.setLastname(pdto1NewChanges.getlName());
        person.setEmail(pdto1NewChanges.getEmail());
        Address address = new Address(pdto1NewChanges.getStreet(),pdto1NewChanges.getZip());
        
        person.setAddress(address);
        PersonDTO result = FACADE.editPerson(1,pdto1NewChanges);
        
    }
        
        
         
        PersonsDTO persons = FACADE.getAllPersonsByHobby("Skak");
        persons.getAll().forEach(p -> {
            System.out.println("Persons with hobby SKAK: " + p.getfName());
        });
        
        PersonsDTO persons2 = FACADE.getAllPersonsByHobby("Astrologi");
        persons2.getAll().forEach(p -> {
            System.out.println("Persons with hobby ASTROLOGI: " + p.getfName());
        });
        
        
        
        
        Long antal = FACADE2.getCityCount();
        System.out.println(antal);
        CityInfosDTO cities = FACADE2.getAllCities();
        
        cities.getAll().stream().forEach(System.out::println);

        
        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.getTransaction().commit();
         
        TypedQuery<Phone> q1 = em.createQuery("SELECT p FROM Phone p", Phone.class);

        List<Phone> phones = q1.getResultList();

        phones.forEach(p -> {
            System.out.println(p.getPerson().getFirstName() + ", " + p.getPerson().getLastname() + ", Numbers: " + p.getNumber());
        });

        PersonsDTO pdto = FACADE.getAllPersons();

        pdto.getAll().forEach(p -> {
            System.out.println("First name: " + p.getfName() + ". Last name: " + p.getlName());
        });

        PersonDTO pdto2 = FACADE.getPersonByPhone(7777);

        System.out.println("First name by phone: " + pdto2.getfName() + ". Address: " + pdto2.getStreet());
        */
    }
}

