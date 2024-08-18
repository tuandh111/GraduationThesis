package com.DuAn.DuAnTotNghiep.service.service.utils;

import com.DuAn.DuAnTotNghiep.model.request.URLRequest;

public interface QrCodeService {
    String generateQrCode(URLRequest sdi);
}
