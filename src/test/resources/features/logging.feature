Feature: Logging

  Scenario: User should not be able to login with invalid credentials
    Given home page is opened
    When The user changes language to "UA"
    Then Localization should be switched to "UA"

    When The user opens Log In modal by clicking on header user icon
    Then Log In modal should be opened
    And Log In button should be displayed
    And Registration button should be displayed
    And Remind password button should be displayed

    When The user tries to log in by clicking on the log in button
    Then Error message "Введено невірну адресу ел. пошти або номер телефону" should be displayed when submitting empty fields
    And Email border color should be "248, 65, 71" after submitting empty fields
    And Password border color should be "248, 65, 71" after submitting empty fields

    When The user tries to remind password by clicking on the remind password button
    Then Get temporary password button should be displayed
    And Remember password button should be displayed

    When The user tries to get temporary password by clicking on the get temporary password button
    Then Error message "Введено невірну адресу ел. пошти або номер телефону" should be displayed when submitting empty field
    And Email border color should be "248, 65, 71" after submitting empty email field

    When The user remembers password by clicking on the remember password button
    And The user starts registration by clicking on the register button
    Then Registration modal should be opened