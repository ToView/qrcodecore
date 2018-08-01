# QRCodeUtils使用文档
## 介绍
QRCodeUtils是基于Google提供的zxing二维码生成库而整合的一个生成或者解析二维码的工具类。QRCodeUtils采用遵循COC原则，在调用者未声明生成配置时，默认生成**编码为UTF-8，尺寸为300*300**的二维码。当然，如果调用者调用了带有Logo的二维码生成方法，其中**LOGO的尺寸为90*90**。差点忘了说，**生成的图片格式默认为PNG**。
## 使用方法
将qrcode-core-1.0.jar引入项目lib库，直接调用即可。
## API说明
#### QRCodeUtils.create(String text, String filePath)
根据输入的文本内容生成二维码图片。
#### QRCodeUtils.create(String text)
输入文本内容，返回二维码图片字节输出流。
#### QRCodeUtils.create(String text, String filePath, int width, int height)
输入文本内容，生成文件路径，二维码宽度，二维码高度，生成二维码图片。
#### QRCodeUtils.create(String text, String filePath, int width, int height, Color onColor, Color offColor)
输入文本内容，生成文件路径，二维码宽度，二维码高度，二维码颜色，背景颜色，生成二维码图片。
#### QRCodeUtils.create(String text, int width, int height)
输入文本内容，二维码宽度，二维码高度，返回二维码图片字节输出流。
#### QRCodeUtils.create(String text, String filePath, int width, int height, String format)
输入文本内容，生成文件路径，二维码宽度，二维码高度，图片格式，生成二维码图片。
#### QRCodeUtils.create(String text, int width, int height, String format,  Map<EncodeHintType, Object> hintTypes) 
输入文本内容，生成文件路径，二维码宽度，二维码高度，图片格式，生成配置，返回二维码图片字节输出流。
#### QRCodeUtils.read(String filePath)
输入二维码图片路径，解析二维码信息。
#### QRCodeUtils.read(InputStream inputStream)
输入二维码图片输入流，解析二维码信息。
#### QRCodeUtils.read(String filePath, Map<DecodeHintType, Object> decodeHints)
输入二维码图片路径，解析配置，解析二维码信息
#### QRCodeUtils.read(InputStream inputStream, Map<DecodeHintType, Object> decodeHints)
输入二维码图片输入流，解析配置，解析二维码信息。
#### QRCodeUtils.createWithLogo(String text, String filePath, String logoPath)
输入文本内容，即将存放路径，logo图片路径，生成带有logo的二维码图片。
#### QRCodeUtils.createWithLogo(String text, String filePath, String logoPath, Map<EncodeHintType, Object> hintTypes)
输入文本内容，即将存放路径，logo图片路径，生成配置，生成带有logo的二维码图片。
#### QRCodeUtils.createWithLogo(String text, String filePath, String logoPath, int qrCodeWidth, int qrCodeHeight, int logoWidth, int logoHeight)
输入文本内容，即将存放路径，logo图片路径，二维码宽度，二维码高度，logo宽度，logo高度，生成带有logo的二维码图片。
#### QRCodeUtils.createWithLogo(String text, String filePath, String logoPath, int qrCodeWidth, int qrCodeHeight, int logoWidth, int logoHeight, String format)
输入文本内容，即将存放路径，logo图片路径，二维码宽度，二维码高度，logo宽度，logo高度，生成的图片格式，生成带有logo的二维码图片。
#### QRCodeUtils. createWithLogo(String text, String logoPath)
输入文本内容，logo图片路径，返回生成带有logo的二维码图片的字节流。
#### QRCodeUtils. createWithLogo(String text, String logoPath, int qrCodeWidth, int qrCodeHeight, int logoWidth, int logoHeight)
输入文本内容，logo图片路径，二维码宽度，二维码高度，logo宽度，logo高度，返回生成带有logo的二维码图片的字节流。
#### QRCodeUtils.createWithLogo(String text, String logoPath, int qrCodeWidth, int qrCodeHeight, int logoWidth,int logoHeight, String format)
输入文本内容，logo图片路径，二维码宽度，二维码高度，logo宽度，logo高度，生成的图片格式，返回生成带有logo的二维码图片的字节流。


----------
使用过程中，有任何问题可以在我的[CSDN博客](https://blog.csdn.net/qq_24091555)或者我的[Github主页](https://github.com/ToView)上留言。
