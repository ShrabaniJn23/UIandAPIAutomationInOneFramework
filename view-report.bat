@echo off
echo Starting Allure Report Server...
echo.
echo Report will be available at: http://localhost:8000
echo.
echo Press Ctrl+C to stop the server
echo.
python -m http.server 8000 -d reports/allure-report