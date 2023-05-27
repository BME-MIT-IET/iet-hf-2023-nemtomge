Feature: Testing scenarios about two scientist interactions
  Checking if one scientist can infect other scientist
  Checking if one scientist can kill the other scientist

  Background:
    Given game has Scientist named:
    | Austin  |
    | Bella   |
    And game has Field named:
    | Austin-Field  |
    | Bella-Field   |
    And Austin's position is Austin-Field
    And Bella's position is Bella-Field
    And all of the fields are neighbours to each other

  Scenario: Scientist infects Scientist
    Given Austin has active Bear with 10 duration
    When Bella moves to Austin-Field
    Then Bella inventory called agents "should not be" empty

  Scenario: Scientist kills Scientist
    Given Austin has active Bear with 10 duration
    And Bella moves to Austin-Field
    And Bella has gear Axe
    When Bella kills Austin
    Then He should be killed

