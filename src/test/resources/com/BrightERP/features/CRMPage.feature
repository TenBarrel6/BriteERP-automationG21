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