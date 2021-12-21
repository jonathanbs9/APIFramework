Feature: Validation of Place API

  @AddPlace
  Scenario Outline: Verify Place is successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>", "<language>", "<address>"
    When User calls "AddPlaceAPI" with "POST" HTTP request
    Then The response API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify place_Id created maps to "<name>" using "GetPlaceAPI"

    Examples:
      | name                   | language | address             |
      | House Test             | Spanish  | 123 Fake St         |
      | Avalith Office Mdq     | English  | 4756                |
      | GlobalLogic Cowork Mdq | Spanish  | 5600 Consticion Ave |

@DeletePlace
Scenario: Verify if Delete Place Functionality is working
  Given DeletePlace Payload
  When User calls "DeletePlaceAPI" with "POST" HTTP request
  Then The response API call is success with status code 200
  And "status" in response body is "OK"


