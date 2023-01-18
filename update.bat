cd ECAutomat-Frontend\src\main\resources\rawContent\javascript
node mensaScraper.js
node newsScraper.js

cd ..\..\..\..\..\..
start server.bat

timeout 3
start firefox http://192.168.0.108:8080/ECAutomat-Frontend/src/main/resources/rawContent/html/startseite.html --kiosk
start chrome http://192.168.0.108:8080/ECAutomat-Frontend/src/main/resources/rawContent/html/mensa.html --kiosk