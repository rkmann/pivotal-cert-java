package org.apache.struts.crud.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import org.apache.struts.crud.model.Country;
import org.apache.struts.crud.model.Person;
import org.apache.struts.crud.service.DefaultPersonService;
import org.apache.struts.crud.service.PersonService;

/**
 * Acts as a controller to handle actions related to editing a Person.
 * 
 * @author bruce phillips
 * @author antonio sánchez
 */
public class PersonAction extends ActionSupport implements Preparable {
    
    //private static final Logger LOG = LogManager.getLogger(PersonAction.class.getName());
    private PersonService personService = new DefaultPersonService();
    private Person person;
    private Person[] persons;
    private String[] sports;
    private String[] genders;
    private Country[] countries;
    private String[] carModelsAvailable;

    @Override
    public void prepare() throws Exception {
        carModelsAvailable = personService.getCarModels();
        sports = personService.getSports();
        countries = personService.getCountries();
        genders = personService.getGenders();
        System.out.println("Prepared support data for Person entity.");
        
        if (person != null && person.getPersonId() != null) {
            person = personService.getPerson(person.getPersonId());
            System.out.println("Preparing actual data for Person: " + person);
        }
    }

    /**
     * Get all persons for display in the view.
     */
    public String list() {
        persons = personService.getAllPersons();
        System.out.println("Listing persons");
        return SUCCESS;
    }
    
    /**
     * Save the state of the Person object instance field.
     */
    public String save() {
        if (person.getPersonId() == null) {
            personService.insertPerson(person);
            System.out.println("Created new Person: " + person);
        } else {
            personService.updatePerson(person);
            System.out.println("Updated Person: " + person);
        }
        return SUCCESS;
    }

    /**
     * Delete from Person identified by the person
     * instance field's personId value.
     */
    public String delete() {
        personService.deletePerson(person.getPersonId());
        System.out.println("Deleted Person: " + person);
        return SUCCESS;
    }
    
    public Person[] getPersons() {
        return persons;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String[] getSports() {
        return sports;
    }

    public String[] getGenders() {
        return genders;
    }

    public Country[] getCountries() {
        return countries;
    }

    public String[] getCarModelsAvailable() {
        return carModelsAvailable;
    }
}

