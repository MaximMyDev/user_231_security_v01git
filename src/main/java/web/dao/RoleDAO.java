package web.dao;

import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

public interface RoleDAO {
        //extends JpaRepository<Role, Long> {
        public Role getRoleByRoleName(String roleName);
        List<Role> allRoles();
        public Role getRoleById(int id);
}
