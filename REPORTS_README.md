# UI API Automation Framework

## Allure Reports

The Allure HTML reports are generated in the `reports/allure-report/` folder.

### How to Generate Reports

#### Option 1: Using Batch Script (Windows)
Double-click `generate-report.bat` or run:
```cmd
generate-report.bat
```

#### Option 2: Using Maven Commands
```bash
# Run tests and generate report
mvn clean test allure:report

# Or run tests first, then generate report
mvn clean test
mvn allure:report
```

### Viewing Reports

**Important**: Due to browser security restrictions, Allure HTML reports must be served from a web server. You cannot just double-click the `index.html` file.

#### Option 1: Easy Batch Script (Recommended)
Double-click `view-report.bat` or run:
```cmd
view-report.bat
```
Then open: `http://localhost:8000`

#### Option 2: Manual Python Server
```bash
python -m http.server 8000 -d reports/allure-report
```
Then open: `http://localhost:8000`

#### Option 3: Allure Serve (Alternative)
```bash
mvn allure:serve
```
This starts Allure's built-in server (usually on a different port).

### Report Contents

- **API Request/Response Attachments**: View detailed API payloads
- **Test Execution Details**: Step-by-step test execution
- **Screenshots**: UI screenshots (when UI steps are enabled)
- **Test Results**: Pass/fail status with detailed logs

### Report Contents

- **API Request/Response Attachments**: View detailed API payloads
- **Test Execution Details**: Step-by-step test execution
- **Screenshots**: UI screenshots (when UI steps are enabled)
- **Test Results**: Pass/fail status with detailed logs

### Report Structure

```
reports/
└── allure-report/
    ├── index.html          # Main report file
    ├── data/              # Report data
    ├── widgets/           # Report widgets
    ├── plugins/           # Allure plugins
    └── ...
```

### Notes

- Reports are self-contained HTML files - no server required
- Each test run overwrites the previous report
- Keep important reports by copying the folder before new runs