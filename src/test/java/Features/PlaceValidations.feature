Feature: Validating Place APIS
@ADDPlace
Scenario Outline: Verify if place is being suceesfully added using AddPlaceAPI
Given Add Place payload with "<name>" "<language>""<address>"
When User calls "addPlaceAPI" with "Post" http request
Then The response status code is 200 
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify "place_id" created and maps to "<name>" using "getPlaceAPI"

Examples:
|name     | language  |address|
#|JSHouse  | english   | 9300, coit rd|
|BBHouse  | Spanish   | 4500,Plano   |
#|CCHouse  |French     | 7500,Kenya   |

