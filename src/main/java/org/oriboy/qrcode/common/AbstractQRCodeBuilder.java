package org.oriboy.qrcode.common;

/**
 * QR code generation parsing abstract Builder
 * 二维码生成/解析抽象类
 * @author rocky
 * @date 2018-07-17 17:59
 */
public abstract class AbstractQRCodeBuilder {

    /**
     * @title 生成二维码
     * @description 根据配置信息生成二维码
     */
    abstract void create();

    /**
     * @title 解析二维码
     * @description 解析二维码，获取二维码携带信息
     */
    abstract void read();
}
