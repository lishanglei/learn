import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/25
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/25              lishanglei      v1.0.0           Created
 */
@Slf4j
public class Sign {

    /**
     * @param name      签名
     * @param size      字体大小
     * @param fontStyle 字体风格
     * @return
     * @throws Exception
     */
    public static String createImage(String name, int size, int fontStyle) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            Integer width = 400;
            Integer height = 100;
            //创建图片
            BufferedImage bufferedImage = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_BGR);
            Graphics2D graphics2D = bufferedImage.createGraphics();
            //设置透明 start
            bufferedImage = graphics2D.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
            graphics2D = bufferedImage.createGraphics();
            //设置透明 end

            Font font = new Font("华文行楷", fontStyle, size);
            graphics2D.setFont(font);//设置字体
            graphics2D.setColor(Color.BLACK);//设置颜色
            graphics2D.drawString(name, fontStyle, font.getSize());

            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        } catch (Exception e) {
            log.error(" 生成签名图片失败", e);
            e.printStackTrace();
        }
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String signature = base64Encoder.encodeBuffer(byteArrayOutputStream.toByteArray()).replaceAll("\r|\n", "");
        log.info("signature: " + signature);
        return signature;
    }

    public static void main(String[] args) throws Exception {

        String image = createImage("李尚磊", 80, 1);
//        StudioService studioService =new StudioService();
//        studioService.stringToImage(image,"C:/Users/10025/Desktop");
        GenerateImage(image, "C:\\Users\\10025\\Desktop\\1.png");
    }

    /**
     * 图片转化成base64字符串
     *
     * @param imgPath
     * @return
     */
    public static String GetImageStr(String imgPath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = imgPath;// 待处理的图片
        InputStream in = null;
        byte[] data = null;
        String encode = null; // 返回Base64编码过的字节数组字符串
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            // 读取图片字节数组
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            encode = encoder.encode(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return encode;
    }

    /**
     * base64字符串转化成图片
     *
     * @param imgData     图片编码
     * @param imgFilePath 存放到本地路径
     * @return
     * @throws IOException
     */
    @SuppressWarnings("finally")
    public static boolean GenerateImage(String imgData, String imgFilePath) throws IOException { // 对字节数组字符串进行Base64解码并生成图片
        if (imgData == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        OutputStream out = null;
        try {
            out = new FileOutputStream(imgFilePath);
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgData);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            out.write(b);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
            return true;
        }
    }


}
