

cd .\bin

rd /s/q .\org\hy\common\zxing\junit


jar cvfm zxing.jar MANIFEST.MF com org

copy zxing.jar ..
del /q zxing.jar
cd ..

