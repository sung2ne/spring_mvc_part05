package com.example.spring.posts;

import lombok.Data;

@Data
public class PostsVo {
    private int id;
    private String title;
    private String content;
    private String username;
    private String password;
    private String createdAt;
    private String updatedAt;
}
