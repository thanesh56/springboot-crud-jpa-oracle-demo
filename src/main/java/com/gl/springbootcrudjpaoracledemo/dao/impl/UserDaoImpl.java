package com.gl.springbootcrudjpaoracledemo.dao.impl;

import com.gl.springbootcrudjpaoracledemo.constant.SiteConstant;
import com.gl.springbootcrudjpaoracledemo.dao.UserDao;
import com.gl.springbootcrudjpaoracledemo.exception.FileStorageException;
import com.gl.springbootcrudjpaoracledemo.exception.UserNotFoundException;
import com.gl.springbootcrudjpaoracledemo.exception.UserServerException;
import com.gl.springbootcrudjpaoracledemo.model.User;
import com.gl.springbootcrudjpaoracledemo.repository.UploadFileRepository;
import com.gl.springbootcrudjpaoracledemo.repository.UserRepository;
import com.gl.springbootcrudjpaoracledemo.request.UploadFileRequest;
import com.gl.springbootcrudjpaoracledemo.vo.GeneralDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class UserDaoImpl implements UserDao {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UploadFileRepository uploadFileRepository;


    @Override
    public User getUserByEmail(String email) {
        log.debug("Returning User  by email from DAO ");
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<User> findAll() {
        log.debug("Returning User(s) from DAO");
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        log.debug("Returning User  by id from DAO ");


        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User of this Id: "+id+" Not Found"));
    }

    @Override
    public User save(MultipartFile file, User newUser) {
        log.debug("Saving new User  to DAO ");
        UploadFileRequest uploadFileRequest;
//        if (UploadFileRequest.getFiles().getSize() > Long.parseLong(SiteConstant.UPLOAD_FILE_SIZE_LIMIT)) {
//            log.debug("Throwing Exception because uploaded file is exceed limit for site id : "
//                    + user.getId() + " and file name is : "
//                    + user.getFiles().getOriginalFilename() + " and size is : "
//                    + user.getFiles().getSize());
//
//
//            throw new UserServerException(SiteConstant.INVALID_REQUEST, SiteConstant.EXCEED_LIMIT,
//                    HttpStatus.BAD_REQUEST);
//        }

        //        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            uploadFileRequest = new UploadFileRequest(fileName, file.getContentType(), file.getBytes());
           // uploadFileRequest.setUser(userRepository.findById());
            System.out.println(uploadFileRequest);
            uploadFileRepository.save(uploadFileRequest);
            newUser.setUploadFileRequest(uploadFileRequest);
            System.out.println(newUser);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }

        return userRepository.save(newUser);


    }

    @Override
    public User updateUser(GeneralDetailVO generalDetailVO, Long userId) {
        log.debug("Updating User from DAO");
        User user = findById(userId);
        user.setName(generalDetailVO.getName());
        user.setPhoneNo(generalDetailVO.getPhoneNo());
        user.setEmail(generalDetailVO.getEmail());
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(User user) {
        log.debug("Deleting User from DAO");
        userRepository.delete(user);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Removing User by id from DAO ");
        userRepository.deleteById(id);

    }
}
