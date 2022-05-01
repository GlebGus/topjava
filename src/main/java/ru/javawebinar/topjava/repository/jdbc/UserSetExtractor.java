package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.javawebinar.topjava.model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class UserSetExtractor implements ResultSetExtractor<Map<Integer, Set<Role>>>{

    @Override
    public Map<Integer, Set<Role>> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, Set<Role>> roles = new LinkedHashMap<>();
        while (rs.next()){
            Integer id = rs.getInt("user_id");
            roles.putIfAbsent(id, new LinkedHashSet<>());
            Role role = Role.valueOf(rs.getString("role"));
            roles.get(id).add(role);
        }
        return roles;
    }
}
