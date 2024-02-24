Feature:  Creating a new checking account

  Scenario:  Create a standard individual checking account

    Given the user logged in as "wedevxSH@gmail.com" "Wedevx2!"
    When the user creates a new checking account with the following data
      | checkingAccountType | accountOwnership | accountName                     | initialDepositAmount |
      | Standard Checking   | Individual       | Checking John Kim Third account | 100000.00            |
    Then the user should see the green "Successfully created new Standard Checking account named Checking John Kim Third account" message
    And the user should see newly added account card
      | accountName                     | accountType       | ownership  | accountNumber | interestRate | balance   |
      | Checking John Kim Third account | Standard Checking | Individual | 486133876     | 0.0%         | 100000.00 |
    And the user should see the following transaction
      | date             | category | description               | amount    | balance   |
      | 2023-12-29 23:32 | Income   | 845325096 (DPT) - Deposit | 100000.00 | 100000.00 |
