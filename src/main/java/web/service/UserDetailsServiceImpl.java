package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDAO;
import web.model.Role;
import web.model.User;

import java.sql.SQLOutput;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static Logger log = Logger.getLogger(UserDetailsServiceImpl.class.getName());

    @Autowired
    private UserDAO userDao;
    public UserDetailsServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }

    // «Пользователь» – это просто Object. В большинстве случаев он может быть
    //  приведен к классу UserDetails.
    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("запросили юзера в базе "+s);
        User user = userDao.getUserByName(s);
        System.out.println("вернули юзреа "+user.getName() + "|" +user.getPassword()+"|роль "+user.getRoles().toString());

        log.info("Load user by name: " + user.getName());
        if(user == null){
            log.info("User not found!");
            throw new UsernameNotFoundException("Username not found");
        }

        //+----------------------------
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role: user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return user;
        //return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);

    }

    /*
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByUsername(s);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role: user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

     */
}
