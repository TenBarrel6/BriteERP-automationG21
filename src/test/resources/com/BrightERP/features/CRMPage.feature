Feature: I see correct information for each opportunity on the view list page and pivot table
Background:
  Given I login as a "crm57"
  When I click on the "CRM" tab
  And I click on Pivot board

  Scenario: Verify that amount is the same on pivot board and list board
    And I click on the List board
    Then "Second opportunity expected revenue value" on the pivot board is the same as the "Expected revenue column value" on the list board


  Scenario: The total revenue should match sum of all items
    And Click on "Total"
    And Click on "Total"
    And Click on "Opportunity"
    Then Compare total revenue with the sum of all the items