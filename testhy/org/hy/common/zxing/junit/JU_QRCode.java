package org.hy.common.zxing.junit;


import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;  
import java.nio.file.FileSystems;  
import java.nio.file.Path;  
import java.util.HashMap;  
import java.util.Map;  
  


import javax.imageio.ImageIO;  
  


import org.junit.FixMethodOrder;
import org.junit.Test;  
  
import org.junit.runners.MethodSorters;

import com.google.zxing.BarcodeFormat;  
import com.google.zxing.Binarizer;  
import com.google.zxing.BinaryBitmap;  
import com.google.zxing.DecodeHintType;  
import com.google.zxing.EncodeHintType;  
import com.google.zxing.LuminanceSource;  
import com.google.zxing.MultiFormatReader;  
import com.google.zxing.MultiFormatWriter;  
import com.google.zxing.NotFoundException;  
import com.google.zxing.Result;  
import com.google.zxing.WriterException;  
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;  
import com.google.zxing.client.j2se.MatrixToImageWriter;  
import com.google.zxing.common.BitMatrix;  
import com.google.zxing.common.HybridBinarizer;  
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;




/**
 * 生成二维码图片
 * 解释二维码图片
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-09-28
 * @version     v1.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class JU_QRCode 
{  
  
    /** 
     * 生成图像 
     *  
     * @throws WriterException 
     * @throws IOException 
     */  
    @Test  
    public void test001_Encode() throws WriterException ,IOException
    {
        String filePath = "/Users/hy/Downloads";
        String fileName = "zxing.png";
        String content = "http://www.baidu.com";// 内容
        int width = 300; // 图像宽度
        int height = 300; // 图像高度
        String format = "png";// 图像类型
        
        Map<EncodeHintType ,Object> hints = new HashMap<EncodeHintType ,Object>();
        
        // 编码格式
        hints.put(EncodeHintType.CHARACTER_SET ,"UTF-8");
        
        // 容错率
        // H = ~30%
        // Q = ~25%
        // M = ~15%
        // L = ~7%
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
        
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content ,BarcodeFormat.QR_CODE ,width ,height ,hints);// 生成矩阵
        Path path = FileSystems.getDefault().getPath(filePath ,fileName);
        MatrixToImageWriter.writeToPath(bitMatrix ,format ,path);// 输出图像
        System.out.println("输出成功.");
    }
    
    
  
    /** 
     * 解析图像 
     */  
    @Test  
    public void test002_Decode()
    {
        String filePath = "/Users/hy/Downloads/zxing.png";
        BufferedImage image;
        try
        {
            image = ImageIO.read(new File(filePath));
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType ,Object> hints = new HashMap<DecodeHintType ,Object>();
            hints.put(DecodeHintType.CHARACTER_SET ,"UTF-8");
            Result result = new MultiFormatReader().decode(binaryBitmap ,hints);// 对图像进行解码
            System.out.println("encode： " + result.getText());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (NotFoundException e)
        {
            e.printStackTrace();
        }
    }
}  
