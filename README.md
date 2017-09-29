# hy.common.base



原始工程在 https://github.com/zxing/zxing 下，本工程是否对其再次简单化的封装。

本工程只包含 zxing 的两个子工程（core、javase）



__代码举例__
```java
// 生成二维码
ZXingHelp.encodeToFile(BarcodeFormat.QR_CODE  ,"/Users/hy/Downloads/zxing2D.png" ,"http://www.baidu.com" ,300 ,300);

// 生成条形码
ZXingHelp.encodeToFile(BarcodeFormat.CODE_128 ,"/Users/hy/Downloads/zxing1D.png" ,"Abc1234567890"        ,200 ,50);



// 识别二维码
System.out.println(ZXingHelp.decode(BarcodeFormat.QR_CODE  ,"/Users/hy/Downloads/zxing2D.png"));

// 识别条形码
System.out.println(ZXingHelp.decode(BarcodeFormat.CODE_128 ,"/Users/hy/Downloads/zxing1D.png"));
```