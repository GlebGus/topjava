package ru.javawebinar.topjava.util;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.javawebinar.topjava.model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserSetExtractorForJdbc implements ResultSetExtractor<Map<Integer, Set<Role>>> {

    @Override
    public Map<Integer, Set<Role>> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, Set<Role>> roles = new HashMap<>();
        while (rs.next()){
            Integer id = rs.getInt("user_id");
            if(roles.get(id) == null){
                roles.putIfAbsent(id, new HashSet<>());
            }
            Role role = Role.valueOf(rs.getString("role"));
            roles.get(id).add(role);
        }
        return roles;
    }
}
