#!/bin/sh

cd ./bin

rm -R ./org/hy/common/zxing/junit


jar cvfm zxing.jar MANIFEST.MF com org

cp zxing.jar ..
rm zxing.jar
cd ..

