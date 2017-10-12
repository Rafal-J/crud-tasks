call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runbrowser
echo.
echo Problem running runcrud.bat
goto problems

:runbrowser
start chrome "http://localhost/crud/ver1/tasks/getTasks"
goto end

:problems
echo Some errors occured

:end
echo.
echo Finished!