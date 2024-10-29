package de.pdbm;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;


@ApplicationScoped
public class PersonRepository {

    @PersistenceContext(unitName = "myPU")
    private EntityManager entityManager;

    public List<Person> findAll() {
        return entityManager.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

    public Person find(Long id) {
        return entityManager.find(Person.class, id);
    }

    @Transactional
    public void save(Person person) {
        entityManager.persist(person);
    }

    @Transactional
    public Person update(Person person) {
        return entityManager.merge(person);
    }

    @Transactional
    public void delete(Long id) {
        Person person = find(id);
        if (person != null) {
            entityManager.remove(person);
        }
    }
}
