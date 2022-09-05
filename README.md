# Test automation framework for Rozetka
This is Java automation framework build by maven used for UI tests on 'Rozetka’ Web application.

## Description
-|-|-|-

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

### Executing tests
To execute all tests run suite 'Rozetka tests' functional_tests.xml
File is located by the path:
```
selenide_tasks_704/functional_tests.xml
```
By default, number of running threads is 3. In order to change the number open functional_tests.xml and set new value to `thread-count`.