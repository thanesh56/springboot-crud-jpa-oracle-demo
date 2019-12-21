package com.gl.springbootcrudjpaoracledemo.controller;

import com.gl.springbootcrudjpaoracledemo.dao.UserDao;
import com.gl.springbootcrudjpaoracledemo.model.User;
import com.gl.springbootcrudjpaoracledemo.vo.GeneralDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Autowired
    UserDao userDao;

    /**
     * Getting All User from the database
     * @return User list
     */
    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers(){
        log.debug("Getting All Users.");
        return ResponseEntity.status(HttpStatus.OK).body(userDao.findAll());
    }

    /**
     * Getting User By Id
     * @param take User Id as parameter
     * @return User
     */
    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        log.debug("Getting User By Id.");
        return ResponseEntity.status(HttpStatus.OK).body(userDao.findById(userId));
    }

    /**
     * Getting User By Email
     * @param take User Email as parameter
     * @return User
     */
    @GetMapping(value = "/users/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        log.debug("Getting User By Email.");
        return ResponseEntity.status(HttpStatus.OK).body(userDao.getUserByEmail(email));
    }

    /**
     *
     * @param This method take new user as parameter to store it
     * @return return that new stored user
     */
    @PostMapping(value = "/users")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        log.debug("Saving User.");
        userDao.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDao.save(user));
    }

    /**
     *
     * @param This method take updated user as parameter to store it
     * @return update user
     */
    @PutMapping(value = "/users/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody GeneralDetailVO generalDetailVO, @PathVariable Long userId) {
        log.debug("Updating User.");
        return ResponseEntity.status(HttpStatus.OK).body(userDao.updateUser(generalDetailVO, userId));
    }

    /**
     *
     * @param This method take user as parameter for delete that user
     * @return success message as a 204 noContent status with exit code 1
     */
    @DeleteMapping(value = "/users")
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        log.debug("Deleting User.");
        userDao.delete(user);
        return ResponseEntity.noContent().build();
    }

    /**
     *
     * @param This method take user id as parameter for delete that user by user id
     * @return success message as a 204 noContent status with exit code 1
     */
    @DeleteMapping(value = "/users/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId) {
        log.debug("Deleting User By Id.");
        userDao.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

}
