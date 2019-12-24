package com.gl.springbootcrudjpaoracledemo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class GeneralDetails extends BaseEntity {
    private String name;
    private String phoneNo;
    private String email;


}
