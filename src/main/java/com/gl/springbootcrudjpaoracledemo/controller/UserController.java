package com.gl.springbootcrudjpaoracledemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.springbootcrudjpaoracledemo.constant.SiteConstant;
import com.gl.springbootcrudjpaoracledemo.dao.UserDao;
import com.gl.springbootcrudjpaoracledemo.model.User;
import com.gl.springbootcrudjpaoracledemo.response.ServerResponse;
import com.gl.springbootcrudjpaoracledemo.response.UploadFileResponse;
import com.gl.springbootcrudjpaoracledemo.vo.GeneralDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@Api(value = "User Controller REST Endpoint",description="Shows the user info")
public class UserController {

    @Autowired
    UserDao userDao;



    /**
     * Getting All User from the database
     * @return User list
     */
    @ApiOperation(value = "Return All Users.")
    @GetMapping(value = SiteConstant.GET_ALL_USERS_URL)
    public ResponseEntity<ServerResponse> getAllUsers(){
        log.debug("Getting All Users.");
        ResponseEntity<ServerResponse> serverResponseEntity;
        serverResponseEntity = new ResponseEntity(new ServerResponse(HttpStatus.OK.value()+"","Getted All Users",userDao.findAll(),HttpStatus.OK.name()),HttpStatus.OK);
        return serverResponseEntity;
    }



    /**
     * Getting User By Id
     * @param take User Id as parameter
     * @return User
     */
    @ApiOperation(value = "Getting User By Id.")
    @GetMapping(value = SiteConstant.GET_USER_BY_ID_URL)
    public ResponseEntity<ServerResponse> getUserById(@PathVariable Long userId) {
        log.info("Getting User By Id.");
        ResponseEntity<ServerResponse> serverResponseEntity;
            serverResponseEntity = new ResponseEntity(new ServerResponse(HttpStatus.OK.value()+"","Gotted user of id: "+userId+"",userDao.findById(userId),HttpStatus.OK.name()),HttpStatus.OK);
         return serverResponseEntity;
    }



    /**
     * Getting User By Email
     * @param take User Email as parameter
     * @return User
     */
    @ApiOperation(value = "Getting User By Email.")
    @GetMapping(value = SiteConstant.GET_USER_BY_EMAIL_URL)
    public ResponseEntity<ServerResponse> getUserByEmail(@PathVariable String email) {
        log.debug("Getting User By Email.");
        //return ResponseEntity.status(HttpStatus.OK).body(userDao.getUserByEmail(email));
        ResponseEntity<ServerResponse> serverResponseEntity;
        serverResponseEntity = new ResponseEntity(new ServerResponse(HttpStatus.OK.value()+"","Getting User By Email. "+email+"",userDao.getUserByEmail(email),HttpStatus.OK.name()),HttpStatus.OK);
        return serverResponseEntity;
    }



    /**
     *
     * @param This method take new user as parameter to store it
     * @return return that new stored user
     */
    @ApiOperation(value = "Saving User.")
    @PostMapping(value = SiteConstant.SAVE_USER_URL)
    public ResponseEntity<ServerResponse> saveUser(@RequestParam("file") MultipartFile file,@RequestParam("jsonData") String jsonData) throws IOException {
        log.debug("Saving User.");
        System.out.println("file:"+file);


        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(jsonData,User.class);


        ResponseEntity<ServerResponse> serverResponseEntity;
        serverResponseEntity = new ResponseEntity(new ServerResponse(HttpStatus.OK.value()+"","User Successfully Saved. ",userDao.save(file,user),HttpStatus.OK.name()),HttpStatus.OK);
        return serverResponseEntity;
    }


   /* @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("jsonData") String jsonData) {
        System.out.println("jsonData:-"+jsonData);
        System.out.println("file-"+file);
        //DBFile dbFile = dbFileStorageService.storeFile(file);

//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(dbFile.getId())
//                .toUriString();

//        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
//                file.getContentType(), file.getSize());

        return "Done";
    }*/



    /**
     *
     * @param This method take updated user as parameter to store it
     * @return update user
     */
    @ApiOperation(value = "Updating User.")
    @PutMapping(value = SiteConstant.UPDATE_USER_URL)
    public ResponseEntity<ServerResponse> updateUser(@RequestBody GeneralDetailVO generalDetailVO, @PathVariable Long userId) {
        log.debug("Updating User.");
        //return ResponseEntity.status(HttpStatus.OK).body(userDao.updateUser(generalDetailVO, userId));
        ResponseEntity<ServerResponse> serverResponseEntity;
        serverResponseEntity = new ResponseEntity(new ServerResponse(HttpStatus.OK.value()+"","User Successfully Updated. ",userDao.updateUser(generalDetailVO, userId),HttpStatus.OK.name()),HttpStatus.OK);
        return serverResponseEntity;
    }



    /**
     *
     * @param This method take user as parameter for delete that user
     * @return success message as a 204 noContent status with exit code 1
     */
    @ApiOperation(value = "Deleting User.")
    @DeleteMapping(value = SiteConstant.DELETE_USER_URL)
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        log.debug("Deleting User.");
        userDao.delete(user);
       //return ResponseEntity.noContent().build();
        ResponseEntity<ServerResponse> serverResponseEntity;
        serverResponseEntity = new ResponseEntity(new ServerResponse(HttpStatus.OK.value()+"","User Successfully Deleted. ",null,HttpStatus.OK.name()),HttpStatus.OK);
        return serverResponseEntity;
    }



    /**
     *
     * @param This method take user id as parameter for delete that user by user id
     * @return success message as a 204 noContent status with exit code 1
     */
    @ApiOperation(value = "Deleting User By Id.")
    @DeleteMapping(value = SiteConstant.DELETE_USER_BY_ID_URL)
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId) {
        log.debug("Deleting User By Id.");
        userDao.deleteById(userId);
        //return ResponseEntity.noContent().build();
        ResponseEntity<ServerResponse> serverResponseEntity;
        serverResponseEntity = new ResponseEntity(new ServerResponse(HttpStatus.OK.value()+"","User Successfully Deleted by id: "+userId+" ",null,HttpStatus.OK.name()),HttpStatus.OK);
        return serverResponseEntity;
    }

    public String getValueById(int i) {
        return "done";
    }
}
