package com.blockbank.database.repository;
/**
 * @author Harold Stevens
 */
import com.blockbank.database.domain.User;
import com.blockbank.database.domain.UserDetails;


public interface UserDao {
    UserDetails save(UserDetails userDetails);
    User udatePassword(User user);
    //User findByUsername(String username);
    User findUserById (int userId);




}