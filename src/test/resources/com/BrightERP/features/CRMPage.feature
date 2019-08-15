#@CRM
Feature: Revenue matches the price of corresponding items and "Total revenue expected" matches sum of all items.

  Background:
    Given I login as "CRM manager"
    When I click on the crm tab
    Then Page title should be "Pipeline - Odoo"
#  @wip
  Scenario: Revenue On The List View Matches The Price Of Corresponding Items On The Pivot View
    When I click on the List board
    And I click on Pivot board sign
    Then "Books" price on the Pivot board matches corresponding the price on list board

  @wip
  Scenario: Total Revenue Expected Should Match Sum Of All Items
    When I click on Pivot board sign
    And I double click on "Total"
    And I click on "Opportunity" option from dropdown
    Then "Total revenue expected" matches sum of all revenues