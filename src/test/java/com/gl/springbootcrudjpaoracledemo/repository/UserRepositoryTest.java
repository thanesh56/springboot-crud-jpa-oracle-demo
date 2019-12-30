package com.gl.springbootcrudjpaoracledemo.repository;

import com.gl.springbootcrudjpaoracledemo.model.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

//    @MockBean
//    UploadFileRepository uploadFileRepository;

    @Test
   @Disabled
   public void findUserByEmail() {
//        User user = userRepository.findUserByEmail("thaneshwar@gmail.com");
//        assertEquals("thaneshwar@gmail.com",user.getEmail());


    }
}