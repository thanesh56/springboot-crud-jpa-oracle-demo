package com.gl.springbootcrudjpaoracledemo.controller;

import com.gl.springbootcrudjpaoracledemo.dao.UserDao;
import com.gl.springbootcrudjpaoracledemo.model.User;
import com.gl.springbootcrudjpaoracledemo.repository.UserRepository;
import com.gl.springbootcrudjpaoracledemo.request.UploadFileRequest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserController.class)
@ExtendWith(value = SpringExtension.class)
class UserControllerTest {
    /*@Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDao userDao;

    @Test
    @Disabled
    void getAllUsers() throws Exception {
        when(userDao.findAll()).thenReturn(
                Arrays.asList(
                    new User(1L,"Thanesh","7566189283","thanesh@gmail",new UploadFileRequest("abc.jpg","image/jpg",new byte[]{12,12})),
                        new User(2L,"Ravi","7566189283","ravi@gmail",new UploadFileRequest("abc2.png","image/png",new byte[]{12,12}))
                )
        );

        //call Get "/users" application/json
        RequestBuilder request = MockMvcRequestBuilders
                .get("/users")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{id: 1,name:Thanesh,phoneNo:7566189283,email:thanesh@gmail,{fileName:abc.jpg,fileType:image/jpg,data:1212}},{id:2,name:Ravi,phoneNo:7566189283,email:ravi@gmail,{fileName:abc2.png,fileType:image/png,data:1212}]"))
                .andReturn();

    }*/

}

