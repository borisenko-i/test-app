package com.test_app.database;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DbConnection {
    public static String url;
    public static String user;
    public static String password;
}
