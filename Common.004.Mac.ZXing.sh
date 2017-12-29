#!/bin/sh

cd ./bin

rm -R ./org/hy/common/zxing/junit


jar cvfm hy.common.zxing.jar MANIFEST.MF META-INF com org

cp hy.common.zxing.jar ..
rm hy.common.zxing.jar
cd ..

