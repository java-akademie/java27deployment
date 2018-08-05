call \utils\env.bat

md work

cls

javac -cp ./lib/mytools.jar -d ./work ./ch/jmildner/hallo/*.java

cd work

jar cmf ../manifest.mf hallo.jar ch

copy hallo.jar ..\lib\hallo.jar

cd ..

java -jar ./lib/hallo.jar

rd .\work /S

pause



