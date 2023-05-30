Feature: Testing scenarios about scientist crafting during pacific agents
  Checking scientist crafting during dementia
  Checking scientist crafting during stun

  Background:
    Given game has Scientist named:
    | M.Elek |
    And game has Lab with no BearDance named:
    | Laboratory |
    And Laboratory has genetic code Immunity with 10 duration
    And M.Elek's position is Laboratory
    And M.Elek touches the lab
    And M.Elek learns the genetic code on lab

  Scenario Outline: Crafting
    Given M.Elek has active <agent> with <duration> duration
    When M.Elek crafts from genetic code
    Then M.Elek inventory called crafted "should be" empty

    Examples:
      |    agent | duration |
      | Dementia |       10 |
      |     Stun |       10 |