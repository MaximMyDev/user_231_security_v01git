package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @Override
    public void add(User user) {
        if (user.getId() == null) {
            //user.setRoles(new HashSet<>(roleRepository.findAll()));
            //user.setRoles();
            //user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
            //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            //user.setRoles(Arrays.asList(new Role("ROLE_USER")));
            System.out.println("persist)name="+user.getName()+", pass="+user.getPassword()+", role="+user.getRoles());
            entityManager.persist(user);
        } else {
            System.out.println("merge)name="+user.getName()+", pass="+user.getPassword()+", role="+user.getRoles());
            entityManager.merge(user);
        }
    }

    @Override
    public void delete(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    @Override
    public void edit(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByName(String name) {
        /*
        User user = entityManager.find(User.class, name);
        System.out.println(user.getName());
        return user;

         */
        return (User) entityManager.createQuery("SELECT u FROM User u WHERE u.name LIKE :username")
                .setParameter("username", name)
                .getSingleResult();
    }
}
