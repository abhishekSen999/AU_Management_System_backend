package com.au.cucumber;

import com.au.domain.Onboard;
import com.au.service.OnboardService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

@CucumberContextConfiguration
public class UpdateWithIncompleteOnboard {

    @Autowired
    OnboardService onboardService;
    Onboard onboard,updatedOnboard;

    @Given("incomplete onboard")
    public void incompleteOnboard() {
        onboard = new Onboard();
        onboard.setOnb_id(1)
                .setEmp_id(1)
                .setDem_id(1)
                .setBgc_status("Completed");

    }

    @When("I ask it to update")
    public void iAskItToUpdate() {
        System.out.println(onboardService);
        updatedOnboard = onboardService.update(onboard,"abhishek.sen999@gmail.com");

    }

    @Then("i should get updated onboard with all values")
    public void iShouldGetUpdatedOnboardWithAllValues() {
        Onboard actualOnboard = onboardService.getById(onboard.getOnb_id());
        Assertions.assertEquals(actualOnboard,updatedOnboard);
    }
}
