package com.oah.qrscanlogin.controller;

import com.oah.qrscanlogin.service.QrService;
import com.oah.qrscanlogin.vo.UserVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qr")
public class QrController {

    @Resource
    private QrService qrService;

    /**
     * 获取二维码ID
     * @param deviceId
     * @return
     */
    @GetMapping("/get/qr-id/{deviceId}")
    public String getQrId(@PathVariable String deviceId) {
        return qrService.getQrId(deviceId);
    }

    /**
     * 获取二维码扫描状态
     * @param qrId
     * @return
     */
    @GetMapping("/get/qr/status/{qrId}")
    public Integer getQrStatus(@PathVariable String qrId) {
        return qrService.getQrStatus(qrId);
    }

    /**
     * 扫描二维码的后续操作
     * @param qrId
     * @param userVO
     * @return
     */
    @PostMapping("/scan/{qrId}")
    public String scan(@PathVariable String qrId, @RequestBody UserVO userVO) {
        return qrService.scan(qrId, userVO);
    }

    @PostMapping("/confirm/{token}")
    public void confirm(@PathVariable String token) {
        qrService.confirm(token);
    }
}
