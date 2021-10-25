Feature: Validations of pages on the w3 site

  Scenario Outline: W3Page console error verifications
    Given A user navigates to page "<url>"
    Then user checks for console errors
    Examples:
      | url                                                  |
      | https://www.w3.org/standards/badpage                 |
      | https://www.w3.org/standards/webofdevices/multimodal |
      | https://www.w3.org/standards/webdesign/htmlcss       |

  Scenario Outline: W3Page response codes verifications
    Given A user navigates to page "<url>"
    Then the user checks response is okay
    Examples:
      | url                                                  |
      | https://www.w3.org/standards/badpage                 |
      | https://www.w3.org/standards/webofdevices/multimodal |
      | https://www.w3.org/standards/webdesign/htmlcss       |

  Scenario Outline: W3Page valid links verifications
    Given A user navigates to page "<url>"
    Then user checks all contained links are working
    Examples:
      | url                                                  |
      | https://www.w3.org/standards/badpage                 |
      | https://www.w3.org/standards/webofdevices/multimodal |
      | https://www.w3.org/standards/webdesign/htmlcss       |