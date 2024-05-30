package com.DuAn.DuAnTotNghiep.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Abnormality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer abnormalityId;
    
    private String name;

    private String description;
    @OneToMany(mappedBy = "cTResultAbnormalityId")
    @JsonIgnore
    private List<CTResultAbnormality> ctResultAbnormalities;
}
