Feature: Testing scenarios about scientist crafting during pacific agents
  Checking scientist crafting during dementia
  Checking scientist crafting during stun

  Background:
    Given game has 1 Scientist
    And game has 1 Lab with no BearDance
    And 0st lab has genetic code Immunity with 10 duration
    And 0st scientist's position is the 0st lab
    And 0st scientist learns genetic code

  Scenario Outline: Crafting
    Given 0st scientist has active <agent> with <duration>
    When 0st scientist crafts
    Then 0st scientist inventory crafted should be empty

    Examples:
      |    agent | duration |
      | Dementia |       10 |
      |     Stun |       10 |