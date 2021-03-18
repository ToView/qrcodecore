package demo;

import com.google.zxing.WriterException;
import org.oriboy.qrcode.BarcodeUtils;

import java.io.IOException;

/**
 * 生成条形码
 * @author rocky
 * @date 2018-07-18 11:00
 */
public class Demo4 {

    public static void main(String[] args) {
        String filePath = "E:\\projects\\qrcodecore\\img\\demo4-1.png";
        try {
            BarcodeUtils.create("GH200611102906822309", filePath);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
