@echo off
echo Generating Allure Report...
mvn clean test allure:report
echo.
echo Report generated successfully!
echo Open: reports\allure-report\index.html
pause