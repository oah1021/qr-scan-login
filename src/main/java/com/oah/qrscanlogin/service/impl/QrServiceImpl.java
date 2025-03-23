package com.oah.qrscanlogin.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.oah.qrscanlogin.constant.QrConstent;
import com.oah.qrscanlogin.enums.QrStatusEnum;
import com.oah.qrscanlogin.service.QrService;
import com.oah.qrscanlogin.vo.UserVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Objects;

import static com.oah.qrscanlogin.constant.QrConstent.QR_ID;
import static com.oah.qrscanlogin.constant.QrConstent.QR_STATUS_PREFIX;

@Service
public class QrServiceImpl implements QrService {

    @Resource
    private Cache<String, Object> cache;

    @Override
    public String getQrId(String deviceId) {
        // 生成二维码ID
        String qrId = QR_ID;
        // 将二维码ID与设备信息绑定
//        cache.put(qrId, deviceId);
        // 设置 二维码扫描状态
        cache.put(QR_STATUS_PREFIX + qrId, QrStatusEnum.INIT.getStatus());
        // 返回二维码ID
        return qrId;
    }

    @Override
    public Integer getQrStatus(String qrId) {
        return (Integer) cache.asMap().get(QR_STATUS_PREFIX + qrId);
    }

    @Override
    public String scan(String qrId, UserVO userVO) {
        // 将 二维码id 与 用户信息 绑定
        cache.put(qrId, userVO);
        // 返回临时 token 返回给 手机端。
        String token = qrId + userVO;
        cache.put("token:" + token, qrId);
        // 更新二维码状态
        cache.asMap().put(QR_STATUS_PREFIX + qrId, QrStatusEnum.SCANNED.getStatus());
        return token;
    }

    @Override
    public void confirm(String token) {
        String qrId = (String) cache.asMap().get("token:" + token);
        // 更新二维码状态
        if (cache.asMap().containsKey("token:" + token)) {
            cache.asMap().put(QR_STATUS_PREFIX + qrId, QrStatusEnum.SUCCESS.getStatus());
        }
    }
}
