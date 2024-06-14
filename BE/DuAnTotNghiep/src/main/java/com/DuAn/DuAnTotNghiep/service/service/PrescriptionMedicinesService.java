package com.DuAn.DuAnTotNghiep.service.service;

import com.DuAn.DuAnTotNghiep.entities.PrescriptionMedicines;
import com.DuAn.DuAnTotNghiep.model.request.PrescriptionMedicinesRequest;
import com.DuAn.DuAnTotNghiep.model.response.MessageResponse;

import java.util.List;

public interface PrescriptionMedicinesService {
    PrescriptionMedicines findPrescriptionMedicinesById(int prescriptionMedicinesId) ;

    List<PrescriptionMedicines> findAllPrescriptionMedicines() ;

    PrescriptionMedicines savePrescriptionMedicines(PrescriptionMedicinesRequest prescriptionMedicinesRequest) ;

    PrescriptionMedicines updatePrescriptionMedicines(int prescriptionMedicinesId, PrescriptionMedicinesRequest prescriptionMedicinesRequest) ;

    MessageResponse deletePrescriptionMedicines(int prescriptionMedicinesId) ;

    MessageResponse softDeletePrescriptionMedicines(int prescriptionMedicinesId) ;
}

