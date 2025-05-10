package br.com.api.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class ExampleSteps {

    @Given("the application is running")
    public void theApplicationIsRunning() {
        System.out.println("Application is running");
    }

    @When("I send a request")
    public void iSendARequest() {
        System.out.println("Request sent");
    }

    @Then("I receive a successful response")
    public void iReceiveASuccessfulResponse() {
        System.out.println("Response received");
    }
}