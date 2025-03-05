# API Testing Framework

This repository has 1 Selenium Java UI test for the Amazon store, and 1 test suite for the Reqres API using TestNG and RestAssured.
Dependency management is done with Maven.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Project Setup](#project-setup)
- [Running Tests](#running-tests)
- [Test Structure](#test-structure)
- [Tests Not Running](#tests-not-running)

## Prerequisites

Ensure you have the following installed:

- **Java JDK 23 or higher**
- **Java 17 or higher**
- **Maven** (for dependency management and running tests)
- **IDE** (Visual Studio Code was used)

## Project Setup

### 1. Clone the Repository

You can do this via git commands or by using GitHub's UI

### 2. Install Dependencies

Open the repo and make sure you are in the "assignment" folder.

This can be done with this command in terminal:

cd assignment

In your IDE and via terminal install the required dependencies by running:

mvn clean install

This will install all necessary libraries specified in the `pom.xml`.

## Running Tests

To run all tests, use the following Maven command:

mvn clean test

This will clean the previous build and execute the tests defined in the `testng.xml` file.

## Test Structure

The test classes are located under the `src/test/java/com/omar/tests/` directory. They contain 1 API test suite, and 1 UI test case.

Each test class contains a test method marked with `@Test`.

### Tests Not Running

There is an issue I'm encountering with running via mvn test and the tests are not being recognized.
Despite the following:
- Test classes are correctly annotated with `@Test`.
- The `testng.xml` is pointing to the test classes.
- Maven dependencies are installed by running `mvn clean install`.
