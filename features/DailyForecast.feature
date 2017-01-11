@DailyForecast
Feature: Daily forecast
  As a user of the BuildIT website application
  I want to see a daily forecast summaries for my city
  So that I can get a general idea of the daily weather for each day

  Scenario: The daily forecast shows the current condition
    Given I am viewing the application
    When I examine the daily weather summary for day 1
    Then the daily forecast shows the current condition

  Scenario: The daily forecast shows the current wind speed
    Given I am viewing the application
    When I examine the daily weather summary for day 1
    Then the daily forecast shows the current wind speed

  Scenario: The daily forecast shows the current wind direction
    Given I am viewing the application
    When I examine the daily weather summary for day 1
    Then the daily forecast shows the current wind direction

  Scenario: The daily forecast shows the aggregate rainfall
    Given I am viewing the application
    When I examine the daily weather summary for day 1
    Then the daily forecast shows the aggregate rainfall

  Scenario: The daily forecast shows the minimum temperature
    Given I am viewing the application
    When I examine the daily weather summary for day 1
    Then the daily forecast shows the daily minimum temperature

  Scenario: The daily forecast shows the maximum temperature
    Given I am viewing the application
    When I examine the daily weather summary for day 1
    Then the daily forecast shows the daily maximum temperature
