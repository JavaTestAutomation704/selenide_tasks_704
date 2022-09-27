# Rozetka project
This is Java automation framework build by maven used for UI tests on 'Rozetka’ Web application (https://rozetka.com.ua/ua/).

## About the project
The main aim of this project is test automation framework creation which covers general Rozetka functionality:
- General settings (language selection, location setup, etc.).
- Navigation through the site.
- Products search and their details review.
- Filters functionality.
- Shopping cart manipulations.
- Order price and discounts calculation, etc.

## Getting Started

### Dependencies
- TestNg: https://testng.org/doc/
- Lombok: https://projectlombok.org/
- Selenide: https://selenide.org/
- AssertJ: http://joel-costigliola.github.io/assertj/
- Allure: https://docs.qameta.io/allure

### Requirements
Install and make sure following requirements work as expected:
- Google Chrome 104.0.5112.101
- Java JDK v11: https://www.oracle.com/java/technologies/downloads/archive/
- Maven v3.3 or greater https://maven.apache.org/install
- Intellij idea Standard Edition or Community Edition https://www.jetbrains.com/idea/download
- Lombok plugin for Intellij idea https://plugins.jetbrains.com/plugin/6317-lombok/

### Cloning a repository
- Open Terminal.
- Change the current working directory to the location where you want the cloned directory.
- To clone the repository using HTTPS type:
```
$ git clone https://github.com/JavaTestAutomation704/selenide_tasks_704.git
```
- To clone the repository using an SSH key type:
```
$ git clone git@github.com:JavaTestAutomation704/selenide_tasks_704.git
```

### How to run tests
To execute all tests run suite 'Rozetka tests' in functional_tests.xml file.
File is located by the path:
```
selenide_tasks_704/src/test/resources/functional_tests.xml
```
By default, number of running threads is 3. In order to change the number open functional_tests.xml and change `thread-count` value.

Also, you may run tests with Selenoid. You may find quick start guide by the link:
https://aerokube.com/selenoid/latest/

Create file `config.properties` and locate by the path:
```
selenide_tasks_704/src/main/resources/config.properties
```
Then add to the file next data: `RUN_WITH_SELENOID=true`

Chrome browser is set by default, in order to change the browser add to the file `config.properties` next data `BROWSER=Firefox'.