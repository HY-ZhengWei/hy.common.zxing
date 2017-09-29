package org.hy.common.zxing.junit;

import java.io.IOException;

import org.hy.common.zxing.ZXingHelp;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;





/**
 * 生成二维码图片
 * 解释二维码图片
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-09-28
 * @version     v1.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class JU_ZXingHelp
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
        ZXingHelp.encodeToFile(BarcodeFormat.QR_CODE  ,"/Users/hy/Downloads/zxing2D.png" ,"http://www.baidu.com" ,300 ,300);
        ZXingHelp.encodeToFile(BarcodeFormat.CODE_128 ,"/Users/hy/Downloads/zxing1D.png" ,"Abc1234567890"        ,200 ,50);
        
        System.out.println("输出成功.");
    }
    
    
  
    /** 
     * 解析图像 
     */  
    @Test  
    public void test002_Decode()
    {
        System.out.println("识别二维码：" + ZXingHelp.decode("/Users/hy/Downloads/zxing2D.png"));
        System.out.println("识别条形码：" + ZXingHelp.decode("/Users/hy/Downloads/zxing1D.png"));
    }
    
}  
