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
import java.time.LocalDate;
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
//            case "bank" :
//                jdbcTemplate.update(connection -> insertBankStatement(userDetails, connection),keyHolder);
//                break;
            default :
                System.out.println("Rol bestaat niet");
        }
        int newKey = keyHolder.getKey().intValue();
        userDetails.setUserID(newKey);
        return userDetails;
    }

    @Override
    public UserDetails updatePassword(UserDetails userDetails) {
        logger.debug("UserDao called for updatePassword");
        jdbcTemplate.update(connection -> updateUserStatement(userDetails, connection));
        return userDetails;
    }

    @Override
    public UserDetails findByUsername(String username) {
        logger.debug("userDao called for findByUsername");
        List<UserDetails> users = jdbcTemplate.query(
                "select * from user where username = ?",  new JdbcUserDao.UserRowMapper(), username);
        if(users.size() == 1) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public UserDetails findUserById(int userId) {
        logger.debug("UserDao called for findByUserId");
        List<UserDetails> userDetails = jdbcTemplate.query(
                "select * from user where UserID = ?", new JdbcUserDao.UserRowMapper(), userId);
        if(userDetails.size() == 1) {
            return userDetails.get(0);
        }
        return null;
    }

    @Override
    public UserDetails findUserByEmail(String emailAddress) {
        logger.debug("userDao called for findByEmailaddress");
        List<UserDetails> userDetails = jdbcTemplate.query(
                "select * from user where emailaddress = ?", new JdbcUserDao.UserRowMapper(), emailAddress);
        if (userDetails.size() == 1) {
            return userDetails.get(0);
        }
        return null;
    }

    private static class UserRowMapper implements RowMapper<UserDetails> {
        @Override

        public UserDetails mapRow(ResultSet resultSet, int i) throws SQLException {
            UserDetails result = null;
            switch(resultSet.getString(5)) {
                case "client":
                    int UserId = resultSet.getInt(1);
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
                    String country = resultSet.getString(15);
                    String dateOfBirth = resultSet.getDate(16).toString();
                    int bsn = resultSet.getInt(17);
                    ClientDetails clientDetails = new ClientDetails(firstname, prefix, lastname,
                            LocalDate.parse(dateOfBirth), bsn, emailaddress);
                    Address address1 = new Address(address, housenumber, affix, postalCode, city, country);
                    UserDetails userDetails = new UserDetails(username, password, salt, clientDetails, address1);
                    userDetails.setUserID(UserId);
                    result = userDetails;
                    break;
                case "administrator":
                    int UserIdAdmin = resultSet.getInt(1);
                    String usernameAdmin = resultSet.getString(2);
                    String passwordAdmin = resultSet.getString(3);
                    String saltAdmin = resultSet.getString(4);
                    UserDetails userDetailsAdmin = new UserDetails(usernameAdmin, passwordAdmin, saltAdmin);
                    userDetailsAdmin.setUserID(UserIdAdmin);
                    result = userDetailsAdmin;
                    break;
//                case "bank":
//                    int UserIdBank = resultSet.getInt(1);
//                    String usernameBank = resultSet.getString(2);
//                    String passwordBank = resultSet.getString(3);
//                    String saltBank = resultSet.getString(4);
//                    String addressBank = resultSet.getString(10);
//                    int housenumberBank = resultSet.getInt(11);
//                    String affixBank = resultSet.getString(12);
//                    String postalCodeBank = resultSet.getString(13);
//                    String cityBank = resultSet.getString(14);
//                    String countryBank = resultSet.getString(15);
//                    Address address1Bank = new Address(addressBank, housenumberBank, affixBank, postalCodeBank, cityBank, countryBank);
//                    UserDetails userDetailsBank = new UserDetails(usernameBank, passwordBank, saltBank, address1Bank);
//                    userDetailsBank.setUserID(UserIdBank);
//                    result = userDetailsBank;
//                    break;
                default:
                    System.out.println("rol bestaat niet");

            }
            return result;
        }
    }
}