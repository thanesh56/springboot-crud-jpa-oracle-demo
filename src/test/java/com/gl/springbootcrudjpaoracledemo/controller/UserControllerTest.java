package com.gl.springbootcrudjpaoracledemo.controller;

import org.junit.jupiter.api.*;
import org.springframework.test.context.event.annotation.AfterTestClass;

import static org.junit.jupiter.api.Assertions.*;

//TestInstance create instance based on requirement like PER_CLASS or PER_METHOD
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {
    UserController userController;

    @BeforeAll
    static void beforeAllCall(){
        System.out.println("This method called at class loading before the instantiation");
    }


    @BeforeEach
    void init(){
        userController = new UserController();
    }

    @AfterEach
    void afterEach(){
        System.out.println("Code Cleanup");
    }



    @Test
    void getUserById() {
        assertThrows(NullPointerException.class,()->userController.getUserById(2L),"SomeThing worg in getUserById()");


    }

    @Test
    void test(){

        System.out.println(userController.getValueById(1));
        assertEquals("done",userController.getValueById(1));
    }
}