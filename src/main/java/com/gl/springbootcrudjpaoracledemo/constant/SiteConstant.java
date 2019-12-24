package com.gl.springbootcrudjpaoracledemo.constant;

public class SiteConstant {

    public static final String GET_ALL_USERS_URL = "/users";
    public static final String GET_USER_BY_ID_URL = "/users/{userId}";
    public static final String GET_USER_BY_EMAIL_URL = "/users/email/{email}";
    public static final String SAVE_USER_URL = "/users";
    public static final String UPDATE_USER_URL = "/users/{userId}";
    public static final String DELETE_USER_URL = "/users";
    public static final String DELETE_USER_BY_ID_URL = "/users/{userId}";

    public static final String UPLOAD_FILE_PATH= "/opt/springboot_crud_jpa_oracle_demo/images";
    public static final String EXCEED_LIMIT = "Attachment size exceeds the allowable limit! (5MB)";
    public static final String UPLOAD_FILE_SIZE_LIMIT = "5242880";

    public static final String INVALID_REQUEST = "1400";

    public static final String FAILED ="Internal Server Error";
    public static final String FORBIDDEN_ERROR = "1403";






}
