package demo;

import com.google.zxing.WriterException;
import org.oriboy.qrcode.common.QRCodeUtils;

import java.io.IOException;

/**
 * @author rocky
 * @date 2018-07-18 11:00
 */
public class Demo3 {

    public static void main(String[] args) {
        String filePath = "E:\\ideaProject\\qrcodecore\\img\\demo3-2.png";
        String logoPath = "E:\\ideaProject\\qrcodecore\\img\\logo2.jpg";
        try {
            QRCodeUtils.createWithLogo("http://www.baidu.com", filePath, logoPath);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
