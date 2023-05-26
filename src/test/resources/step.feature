Feature: Testing scenarios about moving a scientist to given fields
  Checking if a scientist can change its position (field)
  Checking if a scientist can change its position if it got Stun Virus
  Checking if a scientist can learn a genetic code on a lab

  Background:
    Given game has 1 Scientist
    And game has 2 Fields
    And game has 1 Lab with no BearDance
    And 0st lab has genetic code Immunity with 10 duration
    And all of the fields are neighbours to each other
    And 0st scientist's position is the 0st field

  Scenario: Scientist Steps On Field
    When 0st scientist moves
    Then 0st scientist current position "should be not the same as" the 0st field

  Scenario: Scientist Unable to Move
    Given 0st scientist has got the virus Stun with 10 duration
    When 0st scientist moves
    Then 0st scientist current position "should be the same as" the 0st field

  Scenario: Scientist Learns Genetic Code
    When 0st scientist moves to 0st lab
    And 0st scientist touches
    And 0st scientist learns the genetic code
    Then 0st scientist should have learned the genetic code