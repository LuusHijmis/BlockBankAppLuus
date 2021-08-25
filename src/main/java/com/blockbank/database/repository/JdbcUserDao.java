package com.blockbank.database.repository;
/**
 * @author Harold Stevens
 */

import com.blockbank.database.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

@Repository
public class JdbcUserDao implements UserDao {

    private final Logger logger = LoggerFactory.getLogger(JdbcUserDao.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserDao(JdbcTemplate jdbcTemplate){
        super();
        this.jdbcTemplate = jdbcTemplate;
        logger.info("New JdbcUserDao");
    }
    //TODO: SQLException afvangen
    private PreparedStatement insertUserStatement (User user, Connection connection) throws SQLException{
        PreparedStatement ps = connection.prepareStatement(
                "insert into user_table (username, role, password, salt) values (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getRole());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getSalt());
        return ps;
    }

    private PreparedStatement updateUserStatement (User user, Connection connection) throws SQLException{
        PreparedStatement ps = connection.prepareStatement(
                "update user set password = ?, salt = ? where username = ?;"
        );
        ps.setString(1, user.getPassword());
        ps.setString(2, user.getSalt());
        return ps;
    }


    @Override
    public User save(User user) {
        logger.debug("UserDao called for save");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> insertUserStatement(user, connection),keyHolder);
        int newKey = keyHolder.getKey().intValue();
        user.setUserID(newKey);
        return user;
    }

    @Override
    public User udatePassword(User user) {
        logger.debug("UserDao called for updatePassword");
        jdbcTemplate.update(connection -> updateUserStatement(user, connection));
        return user;
    }

    /*@Override
    public User findByUsername(String username) {
        logger.debug("userDao called for findByUsername");
        List<User> users = jdbcTemplate.query(
                "select * from user where username = ?", new JdbcUserDao.UserRowMapper(), username);
        if(username.size() == 1) {
            return users.get(0);
        }
        return null;
    }*/

    @Override
    public User findUserById(int userId) {
        return null;
    }


   /* private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            int UserId =resultSet.getInt(1);
            String username = resultSet.getString(2);
            String password = resultSet.getString(3);
            String salt = resultSet.getString(4);
            String role = resultSet.getString(5);
            String emailaddress = resultSet.getString(6);
            String firstname = resultSet.getString(7);
            String prefix = resultSet.getString(8);
            String lastname = resultSet.getString(9);
            String address = resultSet.getString(10);
            int housenumber = resultSet.getInt(11);
            String affix = resultSet.getString(12);
            String postalCode = resultSet.getString(13);
            String city = resultSet.getString(14);
            Date dateOfBirth = resultSet.getDate(15);
            int bsn = resultSet.getInt(16);
            ClientDetails clientDetails =  new ClientDetails(firstname,prefix,lastname,dateOfBirth, bsn,emailaddress);
            Address address1 = new Address(address, housenumber, affix, postalCode, city,country)
            UserDetails userDetails = new UserDetails(username,password,)
            Client client = new Client()

            Account account = new Account(clientId);
            account.setIban(iban);
            account.setBalance(balance);
            return account;
        }
    }*/
}