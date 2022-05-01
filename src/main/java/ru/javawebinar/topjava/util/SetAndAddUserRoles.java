package ru.javawebinar.topjava.util;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.jdbc.UserSetExtractor;

import java.util.*;

public class SetAndAddUserRoles {


    public static void setRolesById(List<User> users, int id, JdbcTemplate jdbcTemplate){
        Map<Integer, Set<Role>> roles = jdbcTemplate.query("SELECT * FROM user_roles" + " WHERE user_id=?",
                new UserSetExtractor(), id);
        users.stream()
                .filter(user -> user.getId() ==id )
                .forEach(user -> user.setRoles(Objects.requireNonNull(roles).get(user.getId())));
    }
    public static void setRolesByEmail(List<User> users,String email, JdbcTemplate jdbcTemplate){
        Map<Integer, Set<Role>> roles = jdbcTemplate.query("SELECT * FROM user_roles"
                + " JOIN users u on u.id = user_roles.user_id WHERE email = ?", new UserSetExtractor(),email);
        users.stream()
                .filter(user -> user.getEmail().equals(email))
                .forEach(user -> user.setRoles(Objects.requireNonNull(roles).get(user.getId())));
    }


    public static void setAllRoles(List<User> users, JdbcTemplate jdbcTemplate){
        Map<Integer, Set<Role>> roles = jdbcTemplate.query("SELECT * FROM user_roles", new UserSetExtractor());
       users.forEach(user -> user.setRoles(Objects.requireNonNull(roles).get(user.getId())));
    }
    public static void addRole(User user, JdbcTemplate jdbcTemplate){
        Set<Role> roles = user.getRoles();
        jdbcTemplate.batchUpdate("INSERT INTO user_roles(user_id, role) VALUES (?, ?)", roles, roles.size(),
                (ps, argument) -> {
                    ps.setInt(1, user.getId());
                    ps.setString(2, argument.name());
                });
    }
}
