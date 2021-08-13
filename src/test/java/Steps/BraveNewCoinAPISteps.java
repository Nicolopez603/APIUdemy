package Steps;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;

public class BraveNewCoinAPISteps {
    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;

    @Given("^I have a valid API Key for the (.+) URI$")
    public void iSetTheRequestParams(String URI) {
        request = given().relaxedHTTPSValidation().header("x-rapidapi-key" , "76f509e6f1msh987ac2cSeb1b70fp1bb995jsnfe856cc6572a")
                .header("x-rapidapi-host", "bravenewcoin.p.rapidapi.com")
                .contentType(ContentType.JSON)
                .baseUri(URI)
                .log().all();
    }

    @When("^I send a POST request with a valid (.+) payload to the (.+) endpoint$")
    public void sendPOSTRequest(String endpoint, String payload){
        File requestBody = new File("src/test/resources/Payloads/"+payload+".json");

        response = request.when().body(requestBody).post(endpoint).prettyPeek();
    }

    @Then("^I can validate I receive a valid token in the response$")
    public void validateTheToken(){
        //response.then().log().all();
    }



}
