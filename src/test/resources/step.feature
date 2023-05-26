Feature: Testing scenarios about moving a scientist to given fields
  Checking if a scientist can change its position (field)
  Checking if a scientist can change its position if it got Stun Virus
  Checking if a scientist can learn a genetic code on a lab

  Background:
    Given game has Scientist named Martin
    And game has Field named Start
    And game has Field named End
    And game has Lab with no BearDance named Laboratory
    And Laboratory has genetic code Immunity with 10 duration
    And all of the fields are neighbours to each other
    And Martin's position is Start

  Scenario: Scientist Steps On Field
    When Martin moves
    Then Martin current position "should not be" Start

  Scenario: Scientist Unable to Move
    Given Martin has got the virus Stun with 10 duration
    When Martin moves
    Then Martin current position "should be" Start

  Scenario: Scientist Learns Genetic Code
    When Martin moves to Laboratory
    And Martin touches the lab
    And Martin learns the genetic code on lab
    Then Martin should have learned the genetic code