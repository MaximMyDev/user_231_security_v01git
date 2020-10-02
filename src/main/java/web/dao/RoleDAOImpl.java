package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDAOImpl implements RoleDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public Role getRoleByRoleName(String roleName) {
        return (Role) entityManager.createQuery("FROM Role WHERE role = :roleName")
                .setParameter("roleName", roleName).getSingleResult();
    }

    public Role getRoleById(int id) {
        return (Role) entityManager.createQuery("FROM Role WHERE id = :roleId")
                .setParameter("roleId", id).getSingleResult();
    }

    @Override
    public List<Role> allRoles() {
        return entityManager.createQuery("select r from Role r").getResultList();
    }
}
