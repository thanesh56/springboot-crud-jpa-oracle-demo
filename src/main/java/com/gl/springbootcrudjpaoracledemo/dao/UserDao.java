package com.gl.springbootcrudjpaoracledemo.dao;

import com.gl.springbootcrudjpaoracledemo.model.User;
import com.gl.springbootcrudjpaoracledemo.vo.GeneralDetailVO;
import org.springframework.web.multipart.MultipartFile;

public interface UserDao extends CrudDao<User,Long> {

    User getUserByEmail(String email);

    User updateUser(GeneralDetailVO generalDetailVO,Long userId);
    User save(MultipartFile multipartFile,User user );
}
