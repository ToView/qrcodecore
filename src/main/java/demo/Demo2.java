package demo;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import org.oriboy.qrcode.common.QRCodeUtils;

import java.io.IOException;

/**
 * @author rocky
 * @date 2018-07-18 10:34
 */
public class Demo2 {

    public static void main(String[] args) {
        String filePath = "E:\\ideaProject\\qrcodecore\\img\\demo1.png";
        try {
            String text = QRCodeUtils.read(filePath);
            System.out.println("========================");
            System.out.println("解析文本：" + text);
            System.out.println("========================");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        } catch (ChecksumException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
