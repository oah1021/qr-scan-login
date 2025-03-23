package com.oah.qrscanlogin.enums;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum QrStatusEnum {
    INIT(0),
    SCANNED(1),
    SUCCESS(2);
    private Integer status;
}
