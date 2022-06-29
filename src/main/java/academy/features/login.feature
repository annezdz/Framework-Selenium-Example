Feature: Login into Application

  Scenario Outline: Positive test validating login
    Given initialize the browser with chrome
    And navigate to "http://qaclickacademy.com" site
    And click on Login link in home page to land on Secure sign in page
    When user enters <username> and <password> and logs in
    Then verify that user is succesfully logged in
    And close browser

    Examples:
      | username          | password |
      | test99@gmail.com  | 123456   |
      | test123@gmail.com | 12345    |

