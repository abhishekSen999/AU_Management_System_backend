Feature: Update Onboard can handle incomplete information
  Scenario: Updating with incomplete or default values
    Given incomplete onboard
    When I ask it to update
    Then i should get updated onboard with all values
