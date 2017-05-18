Feature: Sums

  Background:
    Given I launch the app

  Scenario: Sum 1 and 1
    When I add 1 and 1
    Then I should see 2.0

    Scenario Outline: Sums
      When I add <first> and <second>
      Then I should see <result>
      Examples:
        | first | second | result |
       | 1     | 1      | 1.0    |
