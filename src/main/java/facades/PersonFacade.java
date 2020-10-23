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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    public PersonFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

       // Det skal være en List<PersonDTO>. Det giver ikke mening at oprettee en ny Person(s)DTO entitet.
    public PersonsDTO getAllPersons() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
            List<Person> list = query.getResultList();
            PersonsDTO result = new PersonsDTO(list);
            return result;
            //Query query = em.createQuery("SELECT new dto.PersonDTO(p.firstName, p.lastname) FROM Person p");
            //return new PersonsDTO(query.getResultList());
        } finally {
            em.close();
        }
    }

    public PersonDTO getPersonByPhone(int phone) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.id = (SELECT h.person.id FROM Phone h WHERE h.number = :phone)", Person.class);
            query.setParameter("phone", phone);
            Person person = query.getSingleResult();
            return new PersonDTO(person.getFirstName(), person.getLastname(), person.getAddress().getStreet(), person.getAddress().getCityinfo().getZipCode());
        } finally {
            em.close();
        }
    }

    // Det skal være en List<PersonDTO>. Det giver ikke mening at oprette en ny Person(s)DTO entitet.
    public PersonsDTO getAllPersonsByHobby(String hobby) {
        EntityManager em = getEntityManager();
        try {

            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p INNER JOIN p.hobbies Hobby WHERE Hobby.name = :hobby", Person.class);
            query.setParameter("hobby", hobby);
            List<Person> resultList = query.getResultList();
            PersonsDTO result = new PersonsDTO(resultList);
            return result;
        } finally {
            em.close();
        }
    }
    
    
    public CityInfo checkCityinfo(String zip) throws InputError {
        if (4 != String.valueOf(zip).length()) {
            throw new InputError("ZIP must be 4 letters");
        }
        EntityManager em = emf.createEntityManager();
        TypedQuery<CityInfo> query1 = em.createQuery("Select c from CityInfo c where c.zipCode = :zip", CityInfo.class);
        query1.setParameter("zip", zip);
        CityInfo cityinfo = query1.getSingleResult();
        return cityinfo;
        
        
    }


    public PersonDTO addPerson(PersonDTO personDTO)throws MissingInputException, InputError {
        if (personDTO.getfName().length() == 0 || (personDTO.getlName().length() == 0)) {
            throw new MissingInputException("One or both names are missing");
        }     
        if (String.valueOf(personDTO.getZip()).length() != 4) {
            throw new InputError("ZIP must be 4 letters");
        }
        EntityManager em = emf.createEntityManager();
        try {
            
            Person p = new Person(personDTO.getEmail(), personDTO.getfName(), personDTO.getlName());
            
            Address address = new Address(personDTO.getStreet(),personDTO.getAdditInfo());
            
            TypedQuery<CityInfo> query1 = em.createQuery("Select c from CityInfo c where c.zipCode = :zip", CityInfo.class);
            query1.setParameter("zip", personDTO.getZip());
            CityInfo cityinfo = query1.getSingleResult();
            
            address.setCityinfo(cityinfo);
            
            address.addPerson(p);
            
            p.setAddress(address);

            TypedQuery<Hobby> query2 = em.createQuery("Select h from Hobby h where h.name = :name", Hobby.class);
            query2.setParameter("name", personDTO.getHobbyName());
            Hobby hobby = query2.getSingleResult();

            p.addHobby(hobby);
            
            p.addTelNo(new Phone(personDTO.getPhNumber(), personDTO.getDescrip()));

            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            
            return new PersonDTO(p.getEmail(),p.getFirstName(),p.getLastname());
        } finally {
            em.close();
        }
    }

    public PersonDTO editPerson(int id, PersonDTO personDTO) {
        EntityManager em = getEntityManager();
        try {
            Person personFromDb = em.find(Person.class, id);
            PersonDTO pdto = null;
            if (personFromDb == null) {
                return pdto;
            }
         
            personFromDb.setFirstName(personDTO.getfName());
            personFromDb.setLastname(personDTO.getlName());
            personFromDb.setEmail(personDTO.getEmail());
            if (personDTO.getStreet() != null) {    
                // TODO: Bør refaktoreres ud i en ny metode, da koden vil blive genbrugt flere steder
                Address address = new Address(personDTO.getStreet(),personDTO.getAdditInfo());
                TypedQuery<CityInfo> query1 = em.createQuery("Select c from CityInfo c where c.zipCode = :zip", CityInfo.class);
                query1.setParameter("zip", personDTO.getZip());
                CityInfo cityinfo = query1.getSingleResult();
                address.setCityinfo(cityinfo);
                personFromDb.setAddress(address);  
                //
            }
            if (personDTO.getHobbies() != null) {
                // TODO: sørg for at der kun tilføjes nye hobbier.
                for (int i = 0; i < personDTO.getHobbies().size(); i++) {
                    personFromDb.addHobby( personDTO.getHobbies().get(i).MapToHobby());
                }
            }

            em.getTransaction().begin();
            em.merge(personFromDb);
            em.getTransaction().commit();
            pdto = new PersonDTO(personFromDb);
            return pdto;
        } finally {
            em.close();
        }

    }

}
