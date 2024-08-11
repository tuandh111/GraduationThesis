package com.DuAn.DuAnTotNghiep.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFacebookDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

}
