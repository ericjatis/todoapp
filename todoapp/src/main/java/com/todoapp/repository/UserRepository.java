package com.todoapp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.todoapp.dao.UserDAO;
import com.todoapp.model.User;

@Repository
public class UserRepository implements UserDAO
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
 
    @Transactional(readOnly=true)
    @Override
    public List<User> findAllUser() {

        String sql = "SELECT * FROM mst_user";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
        return users;
    }
 
    @Transactional(readOnly=true)
    @Override
    public User findUserByUsername(String username) {
    	
        String sql = "SELECT * FROM mst_user WHERE Username = ?";
        Object[] param = new Object[] {username};

        User user = (User) jdbcTemplate.queryForObject(sql, param,
                new BeanPropertyRowMapper(User.class));

        return user;
    }
 
    @Override
    public void createUser(final User user) 
    {
        final String sql = "insert into mst_user(Username,Password,FirstName,LastName,Email) values(?,?,?,?,?)";
        
        Object[] params = new Object[] {user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail()};

        jdbcTemplate.update(sql, params);
    }
}
