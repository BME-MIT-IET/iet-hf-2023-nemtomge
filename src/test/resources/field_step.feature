Feature: Testing scenarios about moving a scientist to given fields
  Checking if a scientist can change its position (field)
  Checking if a scientist can change its position if it got Stun Virus

  Background:
    Given the game has one scientist
    And the game has two field
    And the game has one laboratory with Immunity genetic code
    And all of the fields are neighbours to each other
    And the one scientist's position is one of the above field

  Scenario: Scientist Steps On Field
    When the scientist moves
    Then scientist current position should be changed

  Scenario: Scientist Unable to Move
    Given the scientist has got the Stun Virus
    When the scientist moves
    Then scientist current position should not be changed

  Scenario: Scientist Learns Genetic Code
    When the scientist moves to a laboratory
    And the scientist touches
    And the scientist learns the genetic code
    Then scientist should have learned the genetic code