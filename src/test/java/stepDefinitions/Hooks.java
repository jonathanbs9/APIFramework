package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        placeStepDefinitions sd = new placeStepDefinitions();

        if (placeStepDefinitions.placeID ==null) {
            sd.addPlacePayload("TestName", "AR-Spanish", "123 Test st");
            sd.userCallsWithPOSTHTTPRequest("AddPlaceAPI", "POST");
            sd.verifyPlace_IdCreatedMapsToUsing("TestName", "GetPlaceAPI");
        }

    }

}
