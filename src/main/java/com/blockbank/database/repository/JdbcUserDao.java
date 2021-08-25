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


    private PreparedStatement insertAdminStatement (UserDetails userDetails, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO `user` (`username`, `password`, `salt`, `role`) VALUES (?,?,?,?);",
                Statement.RETURN_GENERATED_KEYS
        );
        ps.setString(1, userDetails.getUsername());
        ps.setString(2, userDetails.getPassword());
        ps.setString(3, userDetails.getSalt());
        ps.setString(4, userDetails.getRole());
        return ps;
    }

    private PreparedStatement insertClientStatement (UserDetails userDetails, Connection connection) throws SQLException{
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO `user` (`username`, `password`, `salt`, `role`, `emailaddress`, `firstname`, `prefix`," +
                        "`lastname`, `address`, `houseNumber`, `postalcode`, `city`, `country`,`dateOfBirth`, `bsn`)" +
                        " VALUES (?, ?,?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS
        );
        ps.setString(1, userDetails.getUsername());
        ps.setString(2, userDetails.getPassword());
        ps.setString(3, userDetails.getSalt());
        ps.setString(4, userDetails.getRole());
        ps.setString(5, userDetails.getClientDetails().getEmailAddress());
        ps.setString(6, userDetails.getClientDetails().getFirstname());
        ps.setString(7, userDetails.getClientDetails().getPrefix());
        ps.setString(8, userDetails.getClientDetails().getLastname());
        ps.setString(9, userDetails.getAddress().getAddress());
        ps.setInt(10, userDetails.getAddress().getHouseNumber());
        ps.setString(11, userDetails.getAddress().getPostalCode());
        ps.setString(12, userDetails.getAddress().getCity());
        ps.setString(13, userDetails.getAddress().getCountry());
        ps.setString(14, userDetails.getClientDetails().getDateOfBirth().toString());
        ps.setInt(15, userDetails.getClientDetails().getBsn());
        return ps;
    }

    private PreparedStatement insertBankStatement (UserDetails userDetails, Connection connection) throws SQLException{
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO `user` (`username`, `password`, `salt`, `role`, `address`, `houseNumber`, " +
                        "`postalcode`, `city`, `country`) VALUES (?,?,?,?,?,?,?,?,?);",
                Statement.RETURN_GENERATED_KEYS
        );
        ps.setString(1, userDetails.getUsername());
        ps.setString(2, userDetails.getPassword());
        ps.setString(3, userDetails.getSalt());
        ps.setString(4, userDetails.getRole());
        ps.setString(5, userDetails.getAddress().getAddress());
        ps.setInt(6, userDetails.getAddress().getHouseNumber());
        ps.setString(7, userDetails.getAddress().getPostalCode());
        ps.setString(8, userDetails.getAddress().getCity());
        ps.setString(9, userDetails.getAddress().getCountry());

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
    public UserDetails save(UserDetails userDetails) {
        logger.debug("UserDao called for save");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        switch(userDetails.getRole()){
            case "client" :
                jdbcTemplate.update(connection -> insertClientStatement(userDetails, connection),keyHolder);
                break;
            case "administrator" :
                jdbcTemplate.update(connection -> insertAdminStatement(userDetails, connection),keyHolder);
                break;
            case "bank" :
                jdbcTemplate.update(connection -> insertBankStatement(userDetails, connection),keyHolder);
                break;
            default :
                System.out.println("Rol bestaat niet");
        }
        int newKey = keyHolder.getKey().intValue();
        userDetails.setUserID(newKey);
        return userDetails;
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