Feature: Testing scenarios about moving a scientist to given fields
  Checking if a scientist can change its position (field)
  Checking if a scientist can change its position if it got Stun Virus
  Checking if a scientist can learn a genetic code on a lab

  Background:
    Given game has 1 Scientist
    And game has 2 Fields
    And game has 1 Lab with no BearDance
    And 1st lab has genetic code Immunity with 10 duration
    And all of the fields are neighbours to each other
    And 1st scientist's position is the 1st field

  Scenario: Scientist Steps On Field
    When 1st scientist moves
    Then 1st scientist current position "should be not the same as" the 1st field

  Scenario: Scientist Unable to Move
    Given 1st scientist has got the virus Stun with 10 duration
    When 1st scientist moves
    Then 1st scientist current position "should be the same as" the 1st field

  Scenario: Scientist Learns Genetic Code
    When 1st scientist moves to 1st lab
    And 1st scientist touches
    And 1st scientist learns the genetic code
    Then 1st scientist should have learned the genetic code