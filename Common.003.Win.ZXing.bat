

cd .\bin

rd /s/q .\org\hy\common\zxing\junit


jar cvfm hy.common.base.jar MANIFEST.MF com org

copy hy.common.zxing.jar ..
del /q hy.common.zxing.jar
cd ..

