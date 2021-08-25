package com.blockbank.database.repository;
/**
 * @author Harold Stevens
 */
import com.blockbank.database.domain.User;



public interface UserDao {
    User save (User user);
    User udatePassword(User user);
    //User findByUsername(String username);
    User findUserById (int userId);




}