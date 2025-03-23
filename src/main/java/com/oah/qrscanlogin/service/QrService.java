package com.oah.qrscanlogin.service;

import com.oah.qrscanlogin.vo.UserVO;

public interface QrService {
    /**
     * 获取 二维码ID
     * @return
     */
    String getQrId(String deviceId);

    /**
     * 获取二维码状态
     * @param qrId
     * @return
     */
    Integer getQrStatus(String qrId);

    /**
     * 扫描二维码的后续操作
     * @param qrId
     * @param userVO
     * @return
     */
    String scan(String qrId, UserVO userVO);

    /**
     * 移动端确认登录
     * @param token
     */
    void confirm(String token);
}
