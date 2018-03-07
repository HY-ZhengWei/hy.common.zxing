

cd .\bin


rd /s/q .\org\hy\common\zxing\junit


jar cvfm hy.common.zxing.jar MANIFEST.MF META-INF com org

copy hy.common.zxing.jar ..
del /q hy.common.zxing.jar
cd ..





cd .\src
jar cvfm hy.common.zxing-sources.jar MANIFEST.MF META-INF com org 
copy hy.common.zxing-sources.jar ..
del /q hy.common.zxing-sources.jar
cd ..
