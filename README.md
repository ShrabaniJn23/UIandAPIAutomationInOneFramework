# UI API Automation Framework

A Java-based automation testing framework using Maven, Selenium WebDriver, BDD with Cucumber, and TestNG.

## Features

- **Selenium WebDriver**: For web UI automation
- **Cucumber**: BDD framework for writing human-readable tests
- **TestNG**: Test framework for running tests
- **WebDriverManager**: Automatic driver management
- **Docker**: Containerized execution
- **GitHub Actions**: CI/CD pipeline

## Prerequisites

- Java 11 or higher
- Maven 3.6+
- Docker (optional)

## Project Structure

```
src/
├── main/java/
│   └── com/example/base/
│       └── BaseTest.java          # Base test class with WebDriver setup
└── test/
    ├── java/
    │   └── com/example/
    │       ├── runner/
    │       │   └── TestRunner.java    # Cucumber TestNG runner
    │       ├── steps/
    │       │   ├── SearchSteps.java   # Step definitions for search
    │       │   └── UserManagementSteps.java # Step definitions for user management
    │       └── pages/
    │           ├── HomePage.java      # Page object for home page
    │           ├── LoginPage.java     # Page object for login
    │           └── CartPage.java      # Page object for cart
    └── resources/
        └── features/
            ├── search.feature         # Basic search feature
            └── createUserCheckLoginDeleteUser.feature # Comprehensive user flow feature
```

## Running Tests

### Local Execution

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=TestRunner

# Run in headless mode
mvn test -Dheadless=true
```

### Docker Execution

```bash
# Build and run with Docker
docker-compose up --build

# Or using Docker directly
docker build -t automation-tests .
docker run -e headless=true automation-tests
```

## Writing Tests

### Feature Files

Create `.feature` files in `src/test/resources/features/`:

```gherkin
Feature: Search functionality

  Scenario: User searches for a term
    Given I am on the home page
    When I search for "automation"
    Then I should see search results
```

### Step Definitions

Implement steps in `src/test/java/com/example/steps/`:

```java
@Given("I am on the home page")
public void iAmOnTheHomePage() {
    driver.get("https://example.com");
}

@When("I search for {string}")
public void iSearchFor(String query) {
    homePage.searchFor(query);
}
```

### Page Objects

Use Page Object Model in `src/test/java/com/example/pages/`:

```java
public class HomePage {
    @FindBy(id = "search")
    private WebElement searchBox;

    public void searchFor(String query) {
        searchBox.sendKeys(query);
        // submit
    }
}
```

## CI/CD

The project includes GitHub Actions workflow that:
- Runs on push/PR to main/develop branches
- Sets up JDK 11
- Executes all tests
- Uploads test reports as artifacts

## Configuration

- `pom.xml`: Maven dependencies and plugins
- `testng.xml`: TestNG suite configuration
- `Dockerfile`: Docker image definition
- `docker-compose.yml`: Docker Compose configuration
- `.github/workflows/ci.yml`: GitHub Actions CI pipeline

## Contributing

1. Fork the repository
2. Create a feature branch
3. Write tests for new functionality
4. Ensure all tests pass
5. Submit a pull request