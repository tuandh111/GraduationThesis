package com.DuAn.DuAnTotNghiep.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class PostRequest {
    private String image ;

    private String title ;

    private String body ;

    private Date createDate ;

    private Integer createById ;
}
