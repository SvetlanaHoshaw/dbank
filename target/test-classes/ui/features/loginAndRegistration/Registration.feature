@Registration
  #@Registration is a tag to use this in some test suite
Feature: Digital Bank Registration Page
Background:
  Given User navigates to Digital Bank signup page

  Scenario: Positive Case. As a user,  I want to successfully create Digital Bank account
    When User creates account with following fields with mocked email and ssn
      | title | firstName | lastName | gender | dob               | ssn       | email     |  password | address    | locality | region | postalCode | country | homePhone  | mobilePhone | workingPhone | termsCheckMark |
      | Mr.   | Jack        | Test         | M         | 12/12/1990  | random | random |Test1234    | 12 Main st | City      | CA       | 99921         | US       | 2146591008   | 21365912      | 1126593008   | true                     |

    Then User should be displayed with the message "Success Registration Successful. Please Login."

    #by adding the next hook, we do not need to open browser for each of examples
    @NegativeRegistrationCases
  Scenario Outline: Negative Test Cases. As a Digital Bank Admin I want to make sure users can not register without providing all valid data
      When User creates account with following fields with mocked email and ssn
      | title   | firstName   | lastName         | gender     | dob   | ssn              | email     | password    | address     | locality     | region    | postalCode     | country     | homePhone    | mobilePhone     | workingPhone   | termsCheckMark |
      | <title> | <firstName> | <lastName> | <gender> | <dob> | <ssn>         | <email> |<password> | <address> | <locality> | <region> | <postalCode> | <country> | <homePhone> | <mobilePhone> | <workingPhone> | <termsCheckMark>|
    Then the User should see the "<fieldWithError>" required field error message  "<errorMessage>"

    Examples:
      | title | firstName | lastName | gender | dob | ssn | email | password | address | locality | region | postalCode | country | homePhone | mobilePhone | workingPhone | termsCheckMark | fieldWithError | errorMessage |
      |        |                   |                |             |         |       |            |                |               |              |            |                    |               |                     |                       |                         |                              |  title                         | Please select an item in the list.    |
      |  Mr. |                   |                |             |         |       |            |                |               |              |            |                    |               |                     |                       |                         |                              |  firstName                | Please fill out this field.                 |
      |  Mr. |   Jack         |                |             |         |       |            |                |               |              |            |                    |               |                     |                       |                         |                              |  lastName                | Please fill out this field.                 |
      |  Mr. |   Jack         |    Test      |             |         |       |            |                |               |              |            |                    |               |                     |                       |                         |                              |  gender                | Please select one of these options.  |


