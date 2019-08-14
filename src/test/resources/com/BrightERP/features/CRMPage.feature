Feature: I see correct information for each opportunity on the view list page and pivot table

  Scenario: Verify that amount is the same on pivot board and list board
    Given I login as a "user"
    When I am on the "CRM" tab
    And I click on Pivot board
    And I click on the List board
    Then "Second opportunity expected revenue value" on the pivot board is the same as the "Expected revenue column value" on the list board

Feature: CRM Page Tables
  Agile story: Verify that on the pivot table,
  the total expected revenue
  should be the sum of all opportunities
  expected revenue.
  Scenario: The total revenue should match sum of all items
    Given I login as a "crm57"
    When I navigate to "CRM"
    And Click on "Pivot"
    And Click on "Total"
    And Click on "Total"
    And Click on "Opportunity"
    Then Compare total revenue with the sum of all the items