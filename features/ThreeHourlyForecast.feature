@ThreeHourlyForecast
Feature: Daily forecast
  As a user of the BuildIT website application
  I want to see a 3 hourly forecast details for my city
  So that I can get a specific idea of the daily weather at different times of the day

  @smoke
  Scenario Outline: I can see a 3 hourly forecast for a selected day
    Given I am viewing the application
    When I select day <dayNumber> from the 5 day forecast
    Then I am shown a 3 hourly forecast for the selected day
    Examples:
      | dayNumber |
      | 1         |
      | 2         |
      | 3         |
      | 4         |
      | 5         |

  Scenario: I can hide the 3 hourly weather forecast
    Given I am viewing the application
    And I select day 1 from the 5 day forecast
    When I select day 1 from the 5 day forecast again
    Then the 3 hourly forecast is hidden for this day

  Scenario: The 3 hourly forecast shows the correct conditions
    Given I am viewing the application
    When I select day 1 from the 5 day forecast
    Then all conditions are correct in the 3 hourly forecast

  Scenario: The 3 hourly forecast shows the correct min temperatures
    Given I am viewing the application
    When I select day 1 from the 5 day forecast
    Then all min temperatures are correct in the 3 hourly forecast

  Scenario: The 3 hourly forecast shows the correct max temperatures
    Given I am viewing the application
    When I select day 1 from the 5 day forecast
    Then all max temperatures are correct in the 3 hourly forecast

  Scenario: The 3 hourly forecast shows the correct wind speeds
    Given I am viewing the application
    When I select day 1 from the 5 day forecast
    Then all wind speeds are correct in the 3 hourly forecast

  Scenario: The 3 hourly forecast shows the correct wind directions
    Given I am viewing the application
    When I select day 1 from the 5 day forecast
    Then all wind directions are correct in the 3 hourly forecast

  Scenario: The 3 hourly forecast shows the correct rainfalls
    Given I am viewing the application
    When I select day 1 from the 5 day forecast
    Then all rainfalls are correct in the 3 hourly forecast

  Scenario: The 3 hourly forecast shows the correct pressures
    Given I am viewing the application
    When I select day 1 from the 5 day forecast
    Then all pressures are correct in the 3 hourly forecast
