Feature: Testing scenarios about moving a scientist to given fields
  Checking if a scientist can change its position (field)
  Checking if a scientist can change its position if it got Stun Virus
  Checking if a scientist can learn a genetic code on a laboratory

  Background:
    Given game has one scientist
    And game has two field
    And game has one laboratory with Immunity genetic code
    And all of the fields are neighbours to each other
    And scientist's position is one of the above field

  Scenario: Scientist Steps On Field
    When scientist moves
    Then scientist current position should be changed

  Scenario: Scientist Unable to Move
    Given scientist has got the Stun Virus
    When scientist moves
    Then scientist current position should not be changed

  Scenario: Scientist Learns Genetic Code
    When scientist moves to a laboratory
    And scientist touches
    And scientist learns the genetic code
    Then scientist should have learned the genetic code