Feature: Personal data

  Scenario: Verify that user can edit personal data and this functionality works correctly
    Given home page is opened
    When The user changes language to "UA"
    Then Localization should be switched to "UA"

    When The user logs in
    And The user opens personal data section via main sidebar
    Then Personal data section should be opened

    When The user starts editing by clicking on the edit button
    And The user fills in all personal data fields random data
    Then Save button should be enabled

    When The user saves changes
    Then Personal data should be updated after editing
    And Personal data should be the same as entered data during editing"