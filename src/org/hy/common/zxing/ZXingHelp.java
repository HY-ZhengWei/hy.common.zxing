package org.hy.common.zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;





/**
 * 二维码、条形码的辅助类
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-09-29
 * @version     v1.0
 *              v2.0  添加：对encodeTo...(...)的系列方法添加i_CharEncoding参数，使外部可自行设定字符集编码。
 */
public final class ZXingHelp
{
    
    public  final static String               $DefaultCharacter            = "UTF-8";
    
    /** 默认生成的图片类型 */
    public  final static String               $DefaultImageFormat          = "png";
    
    /** 默认生成的容错级别 */
    public  final static ErrorCorrectionLevel $DefaultErrorCorrectionLevel = ErrorCorrectionLevel.M;
    
    /** 二维码识别后的原始十六进制数据的分割符。如：二维码字符 + $DecodeBytesSplite + 二维码十六进制数据 */
    public  final static String               $DecodeBytesSplite           = "_@HY@_";
    
    
    
    private ZXingHelp()
    {
        // Nothing.
    }
    
    
    
    /**
     * 获取在生成二维码、条形码的提示对象。
     * 
     * 统一生成，多处使用。
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-09-29
     * @version     v1.0
     *
     * @param i_ErrorCorrectionLevel
     * @return
     */
    public final static Map<EncodeHintType ,Object> getEncodeHints(ErrorCorrectionLevel i_ErrorCorrectionLevel ,String i_Char)
    {
        Map<EncodeHintType ,Object> v_Hints = new HashMap<EncodeHintType ,Object>(2);
        
        // 编码格式
        v_Hints.put(EncodeHintType.CHARACTER_SET ,$DefaultCharacter);
        
        // 容错率
        // H = ~30%
        // Q = ~25%
        // M = ~15%
        // L = ~7%
        v_Hints.put(EncodeHintType.ERROR_CORRECTION ,i_ErrorCorrectionLevel); 
        
        return v_Hints;
    }
    
    
    
    /**
     * 生成二维码，并保存成文件
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-09-29
     * @version     v1.0
     *
     * @param i_BarcodeFormat         二维码、条形码的编码格式
     * @param i_SaveFile              文件路径
     * @param i_Text                  二维码的文本内容
     * @param i_ImageWidth            生成二维码图片的宽度
     * @param i_ImageHeight           生成二维码图片的高度
     * @return
     */
    public final static File encodeToFile(BarcodeFormat i_BarcodeFormat ,String i_SaveFile ,String i_Text ,int i_ImageWidth ,int i_ImageHeight)
    {
        return encodeToFile(i_BarcodeFormat ,new File(i_SaveFile) ,i_Text ,$DefaultErrorCorrectionLevel ,$DefaultCharacter ,i_ImageWidth ,i_ImageHeight ,$DefaultImageFormat);
    }
    
    
    
    /**
     * 生成二维码，并保存成文件
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-11-01
     * @version     v1.0
     *
     * @param i_BarcodeFormat         二维码、条形码的编码格式
     * @param i_SaveFile              文件路径
     * @param i_Text                  二维码的文本内容
     * @param i_CharEncoding          字符集编码。如，UTF-8、GBK
     * @param i_ImageWidth            生成二维码图片的宽度
     * @param i_ImageHeight           生成二维码图片的高度
     * @return
     */
    public final static File encodeToFile(BarcodeFormat i_BarcodeFormat ,String i_SaveFile ,String i_Text ,String i_CharEncoding ,int i_ImageWidth ,int i_ImageHeight)
    {
        return encodeToFile(i_BarcodeFormat ,new File(i_SaveFile) ,i_Text ,$DefaultErrorCorrectionLevel ,i_CharEncoding ,i_ImageWidth ,i_ImageHeight ,$DefaultImageFormat);
    }
    
    
    
    /**
     * 生成二维码，并保存成文件
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-09-29
     * @version     v1.0
     *
     * @param i_BarcodeFormat         二维码、条形码的编码格式
     * @param i_SaveFile              文件路径
     * @param i_Text                  二维码的文本内容
     * @param i_ErrorCorrectionLevel  容错级别
     * @param i_ImageWidth            生成二维码图片的宽度
     * @param i_ImageHeight           生成二维码图片的高度
     * @return
     */
    public final static File encodeToFile(BarcodeFormat i_BarcodeFormat ,String i_SaveFile ,String i_Text ,ErrorCorrectionLevel i_ErrorCorrectionLevel ,int i_ImageWidth ,int i_ImageHeight)
    {
        return encodeToFile(i_BarcodeFormat ,new File(i_SaveFile) ,i_Text ,i_ErrorCorrectionLevel ,$DefaultCharacter ,i_ImageWidth ,i_ImageHeight ,$DefaultImageFormat);
    }
    
    
    
    /**
     * 生成二维码，并保存成文件
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-11-01
     * @version     v1.0
     *
     * @param i_BarcodeFormat         二维码、条形码的编码格式
     * @param i_SaveFile              文件路径
     * @param i_Text                  二维码的文本内容
     * @param i_ErrorCorrectionLevel  容错级别
     * @param i_CharEncoding          字符集编码。如，UTF-8、GBK
     * @param i_ImageWidth            生成二维码图片的宽度
     * @param i_ImageHeight           生成二维码图片的高度
     * @return
     */
    public final static File encodeToFile(BarcodeFormat i_BarcodeFormat ,String i_SaveFile ,String i_Text ,ErrorCorrectionLevel i_ErrorCorrectionLevel ,String i_CharEncoding ,int i_ImageWidth ,int i_ImageHeight)
    {
        return encodeToFile(i_BarcodeFormat ,new File(i_SaveFile) ,i_Text ,i_ErrorCorrectionLevel ,i_CharEncoding ,i_ImageWidth ,i_ImageHeight ,$DefaultImageFormat);
    }
    
    
    
    /**
     * 生成二维码，并保存成文件
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-09-29
     * @version     v1.0
     *
     * @param i_BarcodeFormat         二维码、条形码的编码格式
     * @param i_SaveFile              文件路径
     * @param i_Text                  二维码的文本内容
     * @param i_ErrorCorrectionLevel  容错级别
     * @param i_ImageWidth            生成二维码图片的宽度
     * @param i_ImageHeight           生成二维码图片的高度
     * @param i_ImageFormat           生成二维码图片的格式(png、jpg)
     * @return
     */
    public final static File encodeToFile(BarcodeFormat i_BarcodeFormat ,String i_SaveFile ,String i_Text ,ErrorCorrectionLevel i_ErrorCorrectionLevel ,int i_ImageWidth ,int i_ImageHeight ,String i_ImageFormat)
    {
        return encodeToFile(i_BarcodeFormat ,new File(i_SaveFile) ,i_Text ,i_ErrorCorrectionLevel ,$DefaultCharacter ,i_ImageWidth ,i_ImageHeight ,i_ImageFormat);
    }
    
    
    
    /**
     * 生成二维码，并保存成文件
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-11-01
     * @version     v1.0
     *
     * @param i_BarcodeFormat         二维码、条形码的编码格式
     * @param i_SaveFile              文件路径
     * @param i_Text                  二维码的文本内容
     * @param i_ErrorCorrectionLevel  容错级别
     * @param i_CharEncoding          字符集编码。如，UTF-8、GBK
     * @param i_ImageWidth            生成二维码图片的宽度
     * @param i_ImageHeight           生成二维码图片的高度
     * @param i_ImageFormat           生成二维码图片的格式(png、jpg)
     * @return
     */
    public final static File encodeToFile(BarcodeFormat i_BarcodeFormat ,String i_SaveFile ,String i_Text ,ErrorCorrectionLevel i_ErrorCorrectionLevel ,String i_CharEncoding ,int i_ImageWidth ,int i_ImageHeight ,String i_ImageFormat)
    {
        return encodeToFile(i_BarcodeFormat ,new File(i_SaveFile) ,i_Text ,i_ErrorCorrectionLevel ,i_CharEncoding ,i_ImageWidth ,i_ImageHeight ,i_ImageFormat);
    }
    
    
    
    /**
     * 生成二维码，并保存成文件
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-09-29
     * @version     v1.0
     *
     * @param i_BarcodeFormat         二维码、条形码的编码格式
     * @param i_SaveFile              文件路径
     * @param i_Text                  二维码的文本内容
     * @param i_ImageWidth            生成二维码图片的宽度
     * @param i_ImageHeight           生成二维码图片的高度
     * @return
     */
    public final static File encodeToFile(BarcodeFormat i_BarcodeFormat ,File i_SaveFile ,String i_Text ,int i_ImageWidth ,int i_ImageHeight)
    {
        return encodeToFile(i_BarcodeFormat ,i_SaveFile ,i_Text ,$DefaultErrorCorrectionLevel ,$DefaultCharacter ,i_ImageWidth ,i_ImageHeight ,$DefaultImageFormat);
    }
    
    
    
    /**
     * 生成二维码，并保存成文件
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-11-01
     * @version     v1.0
     *
     * @param i_BarcodeFormat         二维码、条形码的编码格式
     * @param i_SaveFile              文件路径
     * @param i_Text                  二维码的文本内容
     * @param i_CharEncoding          字符集编码。如，UTF-8、GBK
     * @param i_ImageWidth            生成二维码图片的宽度
     * @param i_ImageHeight           生成二维码图片的高度
     * @return
     */
    public final static File encodeToFile(BarcodeFormat i_BarcodeFormat ,File i_SaveFile ,String i_Text ,String i_CharEncoding ,int i_ImageWidth ,int i_ImageHeight)
    {
        return encodeToFile(i_BarcodeFormat ,i_SaveFile ,i_Text ,$DefaultErrorCorrectionLevel ,i_CharEncoding ,i_ImageWidth ,i_ImageHeight ,$DefaultImageFormat);
    }
    
    
    
    /**
     * 生成二维码，并保存成文件
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-09-29
     * @version     v1.0
     *
     * @param i_BarcodeFormat         二维码、条形码的编码格式
     * @param i_SaveFile              文件路径
     * @param i_Text                  二维码的文本内容
     * @param i_ErrorCorrectionLevel  容错级别
     * @param i_ImageWidth            生成二维码图片的宽度
     * @param i_ImageHeight           生成二维码图片的高度
     * @return
     */
    public final static File encodeToFile(BarcodeFormat i_BarcodeFormat ,File i_SaveFile ,String i_Text ,ErrorCorrectionLevel i_ErrorCorrectionLevel ,int i_ImageWidth ,int i_ImageHeight)
    {
        return encodeToFile(i_BarcodeFormat ,i_SaveFile ,i_Text ,i_ErrorCorrectionLevel ,$DefaultCharacter ,i_ImageWidth ,i_ImageHeight ,$DefaultImageFormat);
    }
    
    
    
    /**
     * 生成二维码，并保存成文件
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-11-01
     * @version     v1.0
     *
     * @param i_BarcodeFormat         二维码、条形码的编码格式
     * @param i_SaveFile              文件路径
     * @param i_Text                  二维码的文本内容
     * @param i_ErrorCorrectionLevel  容错级别
     * @param i_CharEncoding          字符集编码。如，UTF-8、GBK
     * @param i_ImageWidth            生成二维码图片的宽度
     * @param i_ImageHeight           生成二维码图片的高度
     * @return
     */
    public final static File encodeToFile(BarcodeFormat i_BarcodeFormat ,File i_SaveFile ,String i_Text ,ErrorCorrectionLevel i_ErrorCorrectionLevel ,String i_CharEncoding ,int i_ImageWidth ,int i_ImageHeight)
    {
        return encodeToFile(i_BarcodeFormat ,i_SaveFile ,i_Text ,i_ErrorCorrectionLevel ,i_CharEncoding ,i_ImageWidth ,i_ImageHeight ,$DefaultImageFormat);
    }
    
    
    
    /**
     * 生成二维码，并保存成文件
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-09-29
     * @version     v1.0
     *
     * @param i_BarcodeFormat         二维码、条形码的编码格式
     * @param i_SaveFile              文件路径
     * @param i_Text                  二维码的文本内容
     * @param i_ErrorCorrectionLevel  容错级别
     * @param i_CharEncoding          字符集编码。如，UTF-8、GBK
     * @param i_ImageWidth            生成二维码图片的宽度
     * @param i_ImageHeight           生成二维码图片的高度
     * @param i_ImageFormat           生成二维码图片的格式(png、jpg)
     * @return
     */
    public final static File encodeToFile(BarcodeFormat i_BarcodeFormat ,File i_SaveFile ,String i_Text ,ErrorCorrectionLevel i_ErrorCorrectionLevel ,String i_CharEncoding ,int i_ImageWidth ,int i_ImageHeight ,String i_ImageFormat)
    {
        try
        {
            BitMatrix v_BitMatrix = new MultiFormatWriter().encode(i_Text ,i_BarcodeFormat ,i_ImageWidth ,i_ImageHeight ,getEncodeHints(i_ErrorCorrectionLevel ,i_CharEncoding));
            Path      v_Path      = FileSystems.getDefault().getPath(i_SaveFile.getParent() ,i_SaveFile.getName());
            
            MatrixToImageWriter.writeToPath(v_BitMatrix ,i_ImageFormat ,v_Path);
            
            return i_SaveFile;
        }
        catch (Exception exce)
        {
            exce.printStackTrace();
        }
        
        return null;
    }
    
    
    
    /**
     * 生成二维码，并写到IO流中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-09-29
     * @version     v1.0
     *
     * @param i_BarcodeFormat         二维码、条形码的编码格式
     * @param i_SaveFile              文件的IO流
     * @param i_Text                  二维码的文本内容
     * @param i_ErrorCorrectionLevel  容错级别
     * @param i_ImageWidth            生成二维码图片的宽度
     * @param i_ImageHeight           生成二维码图片的高度
     * @return
     */
    public final static void encodeToStream(BarcodeFormat i_BarcodeFormat ,OutputStream io_SaveFile ,String i_Text ,int i_ImageWidth ,int i_ImageHeight)
    {
        encodeToStream(i_BarcodeFormat ,io_SaveFile ,i_Text ,$DefaultErrorCorrectionLevel ,$DefaultCharacter ,i_ImageWidth ,i_ImageHeight ,$DefaultImageFormat);
    }
    
    
    
    /**
     * 生成二维码，并写到IO流中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-09-29
     * @version     v1.0
     *
     * @param i_BarcodeFormat         二维码、条形码的编码格式
     * @param i_SaveFile              文件的IO流
     * @param i_Text                  二维码的文本内容
     * @param i_ErrorCorrectionLevel  容错级别
     * @param i_ImageWidth            生成二维码图片的宽度
     * @param i_ImageHeight           生成二维码图片的高度
     * @return
     */
    public final static void encodeToStream(BarcodeFormat i_BarcodeFormat ,OutputStream io_SaveFile ,String i_Text ,ErrorCorrectionLevel i_ErrorCorrectionLevel ,int i_ImageWidth ,int i_ImageHeight)
    {
        encodeToStream(i_BarcodeFormat ,io_SaveFile ,i_Text ,i_ErrorCorrectionLevel ,$DefaultCharacter ,i_ImageWidth ,i_ImageHeight ,$DefaultImageFormat);
    }
    
    
    
    /**
     * 生成二维码，并写到IO流中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-11-01
     * @version     v1.0
     *
     * @param i_BarcodeFormat         二维码、条形码的编码格式
     * @param i_SaveFile              文件的IO流
     * @param i_Text                  二维码的文本内容
     * @param i_ErrorCorrectionLevel  容错级别
     * @param i_CharEncoding          字符集编码。如，UTF-8、GBK
     * @param i_ImageWidth            生成二维码图片的宽度
     * @param i_ImageHeight           生成二维码图片的高度
     * @return
     */
    public final static void encodeToStream(BarcodeFormat i_BarcodeFormat ,OutputStream io_SaveFile ,String i_Text ,ErrorCorrectionLevel i_ErrorCorrectionLevel ,String i_CharEncoding ,int i_ImageWidth ,int i_ImageHeight)
    {
        encodeToStream(i_BarcodeFormat ,io_SaveFile ,i_Text ,i_ErrorCorrectionLevel ,i_CharEncoding ,i_ImageWidth ,i_ImageHeight ,$DefaultImageFormat);
    }
    
    
    
    /**
     * 生成二维码，并写到IO流中
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-09-29
     * @version     v1.0
     *
     * @param i_BarcodeFormat         二维码、条形码的编码格式
     * @param i_SaveFile              文件的IO流
     * @param i_Text                  二维码的文本内容
     * @param i_ErrorCorrectionLevel  容错级别
     * @param i_CharEncoding          字符集编码。如，UTF-8、GBK
     * @param i_ImageWidth            生成二维码图片的宽度
     * @param i_ImageHeight           生成二维码图片的高度
     * @param i_ImageFormat           生成二维码图片的格式(png、jpg)
     * @return
     */
    public final static void encodeToStream(BarcodeFormat i_BarcodeFormat ,OutputStream io_SaveFile ,String i_Text ,ErrorCorrectionLevel i_ErrorCorrectionLevel ,String i_CharEncoding ,int i_ImageWidth ,int i_ImageHeight ,String i_ImageFormat)
    {
        try
        {
            BitMatrix v_BitMatrix = new MultiFormatWriter().encode(i_Text ,i_BarcodeFormat ,i_ImageWidth ,i_ImageHeight ,getEncodeHints(i_ErrorCorrectionLevel ,i_CharEncoding));
            
            MatrixToImageWriter.writeToStream(v_BitMatrix ,i_ImageFormat ,io_SaveFile);
        }
        catch (Exception exce)
        {
            exce.printStackTrace();
        }
    }
    
    
    
    /**
     * 解释识别二维码、条形码
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-09-29
     * @version     v1.0
     *
     * @param i_ImageFile      二维码、条形码的文件
     * @return
     */
    public final static String decode(String i_ImageFile)
    {
        return decode(new File(i_ImageFile) ,$DefaultCharacter);
    }
    
    
    
    /**
     * 解释识别二维码、条形码
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-09-29
     * @version     v1.0
     *
     * @param i_ImageFile      二维码、条形码的文件
     * @param i_CharEncoding   字符集编码
     * @return
     */
    public final static String decode(String i_ImageFile ,String i_CharEncoding)
    {
        return decode(new File(i_ImageFile) ,i_CharEncoding);
    }
    
    
    
    /**
     * 解释识别二维码、条形码
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-09-29
     * @version     v1.0
     *
     * @param i_ImageFile      二维码、条形码的文件
     * @return
     */
    public final static String decode(File i_ImageFile)
    {
        return decode(i_ImageFile ,$DefaultCharacter);
    }
    
    
    
    /**
     * 解释识别二维码、条形码
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-09-29
     * @version     v1.0
     *
     * @param i_ImageFile      二维码、条形码的文件
     * @param i_CharEncoding   字符集编码
     * @return
     */
    public final static String decode(File i_ImageFile ,String i_CharEncoding)
    {
        try
        {
            return decode(ImageIO.read(i_ImageFile) ,$DefaultCharacter);
        }
        catch (Exception exce)
        {
            exce.printStackTrace();
        }
        
        return null;
    }
    
    
    
    /**
     * 解释识别二维码、条形码
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-09-29
     * @version     v1.0
     *
     * @param i_ImageFile      二维码、条形码的文件
     * @return
     */
    public final static String decode(InputStream i_ImageFile)
    {
        return decode(i_ImageFile ,$DefaultCharacter);
    }
    
    
    
    /**
     * 解释识别二维码、条形码
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-09-29
     * @version     v1.0
     *
     * @param i_ImageFile      二维码、条形码的文件
     * @param i_CharEncoding   字符集编码
     * @return
     */
    public final static String decode(InputStream i_ImageFile ,String i_CharEncoding)
    {
        try
        {
            return decode(ImageIO.read(i_ImageFile) ,$DefaultCharacter);
        }
        catch (Exception exce)
        {
            exce.printStackTrace();
        }
        
        return null;
    }
    
    
    
    /**
     * 解释识别二维码、条形码
     * 
     * @author      ZhengWei(HY)
     * @createDate  2017-09-29
     * @version     v1.0
     *
     * @param i_ImageFile      二维码、条形码的文件
     * @param i_CharEncoding   字符集编码
     * @return
     */
    public final static String decode(BufferedImage i_ImageFile ,String i_CharEncoding)
    {
        try
        {
            LuminanceSource             v_Source       = new BufferedImageLuminanceSource(i_ImageFile);
            Binarizer                   v_Binarizer    = new HybridBinarizer(v_Source);
            BinaryBitmap                v_BinaryBitmap = new BinaryBitmap(v_Binarizer);
            Map<DecodeHintType ,Object> v_Hints        = new HashMap<DecodeHintType ,Object>();
            
            v_Hints.put(DecodeHintType.CHARACTER_SET ,i_CharEncoding);
            
            Result v_Result = new MultiFormatReader().decode(v_BinaryBitmap ,v_Hints);
            
            return v_Result.getText();
        }
        catch (Exception exce)
        {
            exce.printStackTrace();
        }
        
        return null;
    }
    
}
