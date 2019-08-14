Feature: Revenue matches the price of corresponding items and "Total revenue expected" matches sum of all items.

  Background:
    Given I login as "crm57"
    When I click on the crm tab
    Then Page title should be "Pipeline - Odoo"

  Scenario: Revenue on the list view matches the price of corresponding items on the pivot view
    When I click on the List board
    And I click on Pivot board sign
    Then "Books" price on the Pivot board matches corresponding the price on list board


  Scenario: "Total revenue expected" should match sum of all items
    When I click on Pivot board sign
    And I double click on "Total"
    And I click on "Opportunity" option from dropdown
    Then "Total revenue expected" matches sum of all revenues