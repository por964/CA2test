package facades;

import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import exceptions.InputError;
import exceptions.MissingInputException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

@Disabled
public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    
    Person p1;
    Person p2;
    Phone ph1, ph2, ph3;
    CityInfo c1;
    Address a1, a2;
    Hobby h1, h2;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        EntityManager em = emf.createEntityManager();
        facade = PersonFacade.getPersonFacade(emf);

        Person p1 = new Person("yrsalarsen@gmail.com", "Yrsa", "Larsen");
        Person p2 = new Person("kajhansen@mail.dk", "Kaj", "Hansen");

        Phone ph1 = new Phone(77449982, "privat");
        Phone ph2 = new Phone(77449980, "kontor");
        Phone ph3 = new Phone(99887766, "hjem");

        CityInfo c1 = new CityInfo("2820", "Gentofte");
        //CityInfo c2 = new CityInfo("2900", "Hellerup");

        Address a1 = new Address("Store Gade 7", "hjem");
        Address a2 = new Address("Torvet 6", "hjem");

        Hobby h1 = new Hobby("Tennis", "Wiki here", "Cat here", "Type here");
        Hobby h2 = new Hobby("Skiing", "Wiki here", "Cat here", "Type here");

        p1.addHobby(h1);
        p1.addHobby(h2);
        p2.addHobby(h2);
        a1.setCityinfo(c1);
        a2.setCityinfo(c1);
        a1.addPerson(p1);
        a2.addPerson(p2);
        p1.addTelNo(ph1);
        p1.addTelNo(ph2);
        p2.addTelNo(ph3);

        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.getTransaction().commit();
    }

    @AfterAll
    public static void tearDownClass() {
        //Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    @Test
    public void testGetPersonByPhone() {
        PersonDTO actual = facade.getPersonByPhone(77449980);
        PersonDTO expected = new PersonDTO("Yrsa", "Larsen", "Store Gade 7", "2820");
        assertEquals(expected.getfName(), actual.getfName());
        assertEquals(expected.getlName(), actual.getlName());
        assertEquals(expected.getStreet(), actual.getStreet());

    }

    @Test
    public void testGetAllPersonsByHobby() {
        PersonsDTO actual = facade.getAllPersonsByHobby("Skiing");
        int expQty = 2;
        assertEquals(expQty, actual.getAll().size());

    }
    
    @Test
    public void testAddPerson() throws MissingInputException, InputError {

        String email = "bg@ms.com";
        String fName = "Bill";
        String lName = "Gates";
        String street = "Strandvejen 1";
        String info = "5.th.";
        String zip = "2820";
        String hobby = "Tennis";
        int telno = 1234;
        String descrip = "mobil";

        //EntityManagerFactory _emf = null;
        //PersonFacade instance = PersonFacade.getPersonFacade(_emf);

        PersonDTO pdto1 = new PersonDTO(email, fName, lName, street, info, zip, hobby, telno, descrip);
        PersonDTO result = facade.addPerson(pdto1);

        PersonDTO expResult = new PersonDTO("bg@ms.com","Bill", "Gates", "Strandvejen 1","5.th.", "2820","Tennis",1234,"mobil");

        assertEquals(expResult.getfName(), result.getfName());
        assertEquals(expResult.getlName(), result.getlName());
        assertEquals(expResult.getStreet(), result.getStreet());
        assertEquals(expResult.getZip(), result.getZip());
    }

    /*
    @Test
    public void testEditPerson() {
        PersonDTO per1 = new PersonDTO("fjhotmail.com", "Frank", "Jensen", "fgh");
        per1.setlName("Hansen");
        PersonDTO p1New = facade.editPerson(per1);
        assertEquals(p1New.getlName(), per1.getlName());
        assertNotEquals(per1.getlName(), "Jensen");
    }
    */

}
