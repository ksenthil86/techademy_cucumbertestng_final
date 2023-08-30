Feature: Validate Ajax Loader link in WebDriverUniversity website

  Scenario Outline: Validate Ajax Loader
    Given I login to WebDriverUniversity website "<url>"
    When I Validate Ajax Loader link
    And I click Ajax Loader link
    And I click Click Me link
    Then I validate pop up message

    Examples: 
      | url |
      | http://webdriveruniversity.com/index.html |

