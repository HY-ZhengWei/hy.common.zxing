# hy.common.base



本工程是对 https://github.com/zxing/zxing 的再次简单化的封装。

本工程只包含 zxing 的两个子工程（core、javase）。

本工程的版本，即是封装zxing的版本。



------
__代码举例__
```java
// 生成二维码(默认容错级别：中)
ZXingHelp.encodeToFile(BarcodeFormat.QR_CODE  ,"/Users/hy/Downloads/zxing2D.png" ,"http://www.baidu.com" ,300 ,300);
ZXingHelp.encodeToFile(BarcodeFormat.QR_CODE  ,"/Users/hy/Downloads/zxing2D.png" ,"http://www.baidu.com" ,"容错级别" ,300 ,300 ,"图片格式，如png");

// 生成条形码
ZXingHelp.encodeToFile(BarcodeFormat.CODE_128 ,"/Users/hy/Downloads/zxing1D.png" ,"Abc1234567890"        ,200 ,50);



// 识别二维码
// 注：识别出的二维码字符串中用 ZXingHelp.$DecodeBytesSplite = "_@HY@_" ，
//     分割了二维码字符串与二维码原始数据的十六进制字符
System.out.println(ZXingHelp.decode("/Users/hy/Downloads/zxing2D.png"));
System.out.println(ZXingHelp.decode("/Users/hy/Downloads/zxing2D.png" ,"UTF-8"));

// 识别条形码
System.out.println(ZXingHelp.decode("/Users/hy/Downloads/zxing1D.png"));
```


---
#### 本项目引用Jar包，其源码链接如下
引用 https://github.com/HY-ZhengWei/hy.common.base 类库
