Feature: Verify page interactions and elements on the website

  Scenario: Verify the title of the home page
    Given I launch the URL "http://the-internet.herokuapp.com/"
   	Then I should see the title of the page as "The Internet"
    When I click on the "A/B Testing" link
    Then I should see the text "A/B Test Variation 1" on the page
    When Navigate back to the home page and click on the "Dropdown" link
    Then I should be on the "Dropdown" page
    When I select "Option 1" from the dropdown
    Then Navigate back to Home Page and Click on Frames