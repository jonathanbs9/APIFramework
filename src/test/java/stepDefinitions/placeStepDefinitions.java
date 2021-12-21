package stepDefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class placeStepDefinitions extends Utils {
    RequestSpecification res;
    ResponseSpecification respSpec;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String placeID;
    JsonPath js;

    @Given("Add Place Payload with {string}, {string}, {string}")
    public void addPlacePayload(String name, String language, String address) throws IOException {
        //respSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        /** I call utils.requestSpecification() ***/
        res = given().spec(requestSpecification())
                .body(data.addPlacePayLoad(name, language, address));
    }

    @When("User calls {string} with {string} HTTP request")
    public void userCallsWithPOSTHTTPRequest(String resource, String httpMethod) {
        ApiResources resourceAPI = ApiResources.valueOf(resource);
        System.out.println(resourceAPI.GetResource());

        respSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if (httpMethod.equalsIgnoreCase("POST")){
            response = res
                    .when()
                    .post(resourceAPI.GetResource());
        } else if (httpMethod.equalsIgnoreCase("GET")){
            response = res
                    .when()
                    .get(resourceAPI.GetResource());
        }

    }


    @Then("The response API call is success with status code {int}")
    public void theResponseAPICallIsSuccessWithStatusCode(int statusCode) {
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }



    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String keyValue, String expectedValue) {
        //String resp = response.asString();
        //js = new JsonPath(resp);

        //placeID = js.get("place_id");
        //System.out.println("PLACE_ID: "+ placeID);

        //String actualStatus =  js.get(status);
        //System.out.println(resp);

        Assert.assertEquals(getJSONPath(response, keyValue), expectedValue);
    }

    @And("Verify place_Id created maps to {string} using {string}")
    public void verifyPlace_IdCreatedMapsToUsing(String expectedName, String resource) throws IOException {
        /*** requestSpec ***/
        //placeID = js.get("place_id");
        placeID = getJSONPath(response, "place_id");
        res = given().spec(requestSpecification())
                .queryParam("place_id", placeID);

        userCallsWithPOSTHTTPRequest(resource, "GET");

        String actualName = getJSONPath(response, "name");
        Assert.assertEquals(expectedName, actualName);
    }

    @Given("DeletePlace Payload")
    public void deleteplacePayload() throws IOException {

        res = given()
                .spec(requestSpecification())
                .body(data.deletePlacePayload(placeID));
    }
}
