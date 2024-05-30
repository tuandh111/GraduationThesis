package com.DuAn.DuAnTotNghiep.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class CTResultAbnormality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cTResultAbnormalityId;

    private String Description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointmentCTResultId")
    private AppointmentCTResult appointmentCTResult;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "abnormalityId")
    private Abnormality abnormality;

}
