package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView allUsers() {
        List<User> users = userService.allUsers();
        List<Role> roleList = userService.allRoles();
        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.setViewName("adminPage");
        modelAndView.setViewName("listAdmin");
        modelAndView.addObject("userList", users);
        modelAndView.addObject("roleList", roleList);
        return modelAndView;
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addUser");
        //modelAndView.setViewName("editPage");
        /*
        System.out.println("edit GET ----------------");
        System.out.println("userName="+ user.getName());
        System.out.println("userPass="+ user.getPassword());
        System.out.println("userRoles="+ user.getRoles());
        */
        List<Role> roles = userService.allRoles();
        modelAndView.addObject("getRole", roles);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute("user") User user, Role role)
            //,@RequestParam("role") String role)
    {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("edit POST ----------------");
        System.out.println("userName="+ user.getName());
        System.out.println("userPass="+ user.getPassword());
        System.out.println("userRoles="+ user.getRoles());
        System.out.println("Roles="+ role.getRole());
        System.out.println("userRoles="+ role.toString());

        modelAndView.setViewName("redirect:/admin/list");
        //Set<Role> rolesArray = new HashSet<>();
        //System.out.println(userService.getRoleByRoleName(role).toString());
        //rolesArray.add(userService.getRoleByRoleName(role));

        //for (String roles : role) {
            //user.setRoles(userService.roles);
            //user.setRoles();
            //rolesArray.add(userService.getRoleByRoleName(roles));
        //}
        //user.setRoles(userService.getRoleByRoleName(roles));
        //user.setRoles(rolesArray);
        //user.setRoles(roleSet.add(userService.getRoleByName(roles)););
        System.out.println("edit POST) вернули юзреа "+user.getName() + "|" +user.getPassword()+"|роль "+user.getRoles());
        userService.edit(user);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        List<Role> roles = userService.allRoles();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("getRole", roles);
        //modelAndView.setViewName("editPage");
        modelAndView.setViewName("addUser");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user, Model model, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("форма add post");
        //System.out.println(user.getId());
        //System.out.println(user.getRoles());
        //model.addAttribute("getRole", userService.allRoles());
        System.out.println("Model");
        System.out.println(model.getAttribute("role"));
        System.out.println(model.getAttribute("roles"));
        System.out.println(model.getAttribute("getRole"));
        System.out.println(model.getAttribute("name"));
        System.out.println(model.getAttribute("password"));
        System.out.println("ModelMap");
        System.out.println(modelMap.getAttribute("role"));
        System.out.println(modelMap.getAttribute("roles"));
        System.out.println(modelMap.getAttribute("getRole"));
        System.out.println(modelMap.getAttribute("name"));
        System.out.println(modelMap.getAttribute("password"));
        System.out.println("------------------");
        System.out.println(modelAndView.getView());
        System.out.println(modelAndView.toString());
        System.out.println("------------------");
        //modelMap .se addAttribute("types", user.getUsername());
        modelAndView.setViewName("redirect:/admin/list");

        userService.add(user);
        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/list");
        User user = userService.getById(id);
        userService.delete(user);
        return modelAndView;
    }
}
