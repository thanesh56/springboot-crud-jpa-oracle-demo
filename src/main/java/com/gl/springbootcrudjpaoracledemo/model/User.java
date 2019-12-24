package com.gl.springbootcrudjpaoracledemo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gl.springbootcrudjpaoracledemo.request.UploadFileRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends GeneralDetails {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="upload_file_request_id")
    private  UploadFileRequest uploadFileRequest;



}
