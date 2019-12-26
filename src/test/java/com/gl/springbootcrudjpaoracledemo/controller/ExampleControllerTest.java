package com.gl.springbootcrudjpaoracledemo.controller;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;



import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

//TestInstance create instance based on requirement like PER_CLASS or PER_METHOD
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExampleControllerTest {
    UserController userController;
    TestReporter testReporter;
    TestInfo testInfo;

    @BeforeAll
    static void beforeAllCall(){
        System.out.println("This method called at class loading before the instantiation");
    }


    @BeforeEach
    void init(TestInfo testInfo,TestReporter testReporter){
        this.testInfo = testInfo;
        this.testReporter = testReporter;
        userController = new UserController();
    }

    @AfterEach
    void afterEach(){
        System.out.println("Code Cleanup");
    }

    @Nested
    @DisplayName("Test getUserById() method")
    @Tag("Tagging for getUserById() method ")
    class  TestGetUserById {
        @RepeatedTest(5)
        @DisplayName("Test testGetUserByPositiveId() method")  //to show customized name of the getUserById() method
        void testGetUserByPositiveId(RepetitionInfo repetitionInfo) {
            System.out.println("Running "+testInfo.getDisplayName()+" with tag "+testInfo.getTags());
           long id = repetitionInfo.getCurrentRepetition();
            assertThrows(NullPointerException.class,()->userController.getUserById(id),()->"SomeThing wrong in getUserById()");//here using second lembda ()-> only executed when test is fail withot lembda it execute even fail or pass

            testReporter.publishEntry("ReportEntry[ Running "+testInfo.getDisplayName()+" with tag "+testInfo.getTags());
        }



        @Test
        @DisplayName("Test testGetUserByNegativeId method")  //to show customized name of the getUserById() method
        void testGetUserByNegativeId() {
            assertThrows(NullPointerException.class,()->userController.getUserById(-2L),()->"SomeThing wrong in getUserById()");
            testReporter.publishEntry("ReportEntry[Running "+testInfo.getDisplayName()+" with tag "+testInfo.getTags());
        }

        @Test
        @DisplayName("Test testGetUserByZeroId method")  //to show customized name of the getUserById() method
        void testGetUserByZeroId() {
            assertThrows(NullPointerException.class,()->userController.getUserById(0L),()->"SomeThing wrong in getUserById()");

            testReporter.publishEntry("ReportEntry[Running "+testInfo.getDisplayName()+" with tag "+testInfo.getTags());
        }

        @Test
        @DisplayName("Test testGetUserByStringId() method")  //to show customized name of the getUserById() method
        void testGetUserByStringId() {
            boolean isServerUp = true;
            assumeTrue(isServerUp); //It is a conditional expression which enabled based on assumtion  else skip the test case
            assertThrows(NullPointerException.class,()->userController.getUserById(5l),()->"SomeThing wrong in getUserById()");

            testReporter.publishEntry("ReportEntry[Running "+testInfo.getDisplayName()+" with tag "+testInfo.getTags());
        }


    }



    @Test
    @Tag("For getValue by id")
    @DisplayName("Test getValueById() method")  //to show customized name of the test() method
    @EnabledOnOs(OS.LINUX)  //It is a conditional expression which enabled based on OS else skip the test case
    void test(){

        System.out.println(userController.getValueById(1));
        assertEquals("done",userController.getValueById(1));
    }

    @Test
    @Tag("For Testing @Disable annotation")
    @DisplayName("TDD method, should not run")  //to show customized name of the test() method
    @Disabled   //this annotation made for to skip this test case
    void testDisabled(){
        fail("This method should be failed");
    }


}