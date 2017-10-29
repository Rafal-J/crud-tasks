call gradlew build
if "%ERRORLEVEL%" == "0" goto rename
echo.
echo GRADLEW BUILD has errors - breaking work
goto fail

:rename
del build\libs\crud.war
ren build\libs\tasks-0.0.1-SNAPSHOT.war crud.war
if "%ERRORLEVEL%" == "0" goto stoptomcat
echo Cannot rename file
goto fail

:stoptomcat
cd \
cd apache-tomcat-9.0.1\bin\
call shutdown.bat

:copyfile
cd \
cd Users\rjanus\Documents\Java\Projects\tasks
copy build\libs\crud.war C:\apache-tomcat-9.0.1\webapps
if "%ERRORLEVEL%" == "0" goto runtomcat
echo Cannot copy file
goto fail

:runtomcat
cd \
cd apache-tomcat-9.0.1\bin\
call startup.bat
sleep 20
goto end

:fail
echo.
echo There were errors

:end
cd \
cd Users\rjanus\Documents\Java\Projects\tasks
echo.
echo Work is finished.