call c:\utils\bat\env
# aus ws2
cd bin

jar cvf ../ws/ws.jar ch

cd ..
cd ws 

jarsigner ws.jar joe

jar cvf ../ws.war *.*

cd ..

copy  ws.war %CATALINA_HOME%\webapps

pause
