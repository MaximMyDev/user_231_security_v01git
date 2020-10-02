package web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import web.dao.UserDAO;
import web.model.User;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
/*
    файл для примера
 */
@Component
public class AuthProviderImp implements AuthenticationProvider {

    @Autowired
    private UserDAO userDAO;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        System.out.println("ввели пользователя "+name);
        User user = userDAO.getUserByName(name);
        if (user == null) {
            System.out.println("Нет пользователь");
        }
        String password = authentication.getCredentials().toString();
        if (!password.equals(user.getPassword())) {
            System.out.println("пользователь с паролем не найден");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
