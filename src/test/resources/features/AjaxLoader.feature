Feature: Validate Ajax Loader link in WebDriverUniversity website

  Scenario Outline: Validate Ajax Loader
    Given I login to WebDriverUniversity website "<url>"
    #When I check for the <value> in step
    #Then I verify the <status> in step

    Examples: 
      | url |
      | http://webdriveruniversity.com/index.html |

