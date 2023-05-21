Feature: Did the scientist step?
  Checking if the scientist changed its position (field)

  Scenario: User's scientist stepped or not
    Given scientist current position is a given field
    When the User moves its scientist
    Then scientist current position should be changed