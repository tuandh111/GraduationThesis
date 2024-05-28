package com.DuAn.DuAnTotNghiep.service.impl.utils;


import com.DuAn.DuAnTotNghiep.service.service.utils.QrCodeService;
import org.springframework.stereotype.Service;


@Service
public class QrCodeServiceImpl implements QrCodeService {

    private String qrCodeDirectory;
    private static final int ORDER_QR_CODE_SIZE_WIDTH = 300;
    private static final int ORDER_QR_CODE_SIZE_HEIGHT = 300;

}
