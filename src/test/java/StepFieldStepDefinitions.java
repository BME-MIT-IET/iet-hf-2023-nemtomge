package test.java;

import components.field.Field;
import components.scientist.Scientist;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertNotSame;

public class StepFieldStepDefinitions {
    private Scientist scientist;
    private Field givenField;
    private Field endField;

    @Given("scientist current position is a given field")
    public void scientist_current_position_is(){
        scientist = new Scientist();
        givenField = new Field();
        endField = new Field();
        givenField.setNeighbour(endField.toString());
        scientist.setField(givenField);
    }
    @When("the User moves its scientist")
    public void the_user_move_its_scientist(){
        scientist.move();
        endField = scientist.getField();
    }
    @Then("scientist current position should be changed")
    public void scientist_current_position_should_be_not(){
        assertNotSame(givenField, endField);
    }
}
