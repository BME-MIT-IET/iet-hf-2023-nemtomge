package test.java.stepdefinitions;

import components.agent.Agent;
import components.agent.GeneticCode;
import components.field.Field;
import components.gear.Gear;
import components.scientist.Inventory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static test.java.stepdefinitions.GameStepDefinitions.*;

import static org.junit.Assert.*;

public class ScientistStepDefinitions {

    private GeneticCode touchedGenCode;
    private boolean killed;
    @Given("{word} has active {agent} with {int} duration")
    public void scientist_has_active_agent(String scientistName, Agent agent, int duration){
        // Arrange
        agent.setDuration(duration);
        scientists.get(scientistName).getInventory().addActiveAgent(agent);
    }
    @And("{word}'s position is {word}")
    public void scientists_position_is(String scientistName, String fieldName){
        // Arrange
        scientists.get(scientistName).setField(fields.get(fieldName));
    }
    @And("{word} touches the lab")
    public void scientist_starts_touching(String scientistName){
        // Arrange
        touchedGenCode = scientists.get(scientistName).touch().getCode();
    }
    @And("{word} has gear {gear}")
    public void scientist_has_gear(String scientistName, Gear gear){
        // Arrange
        scientists.get(scientistName).getInventory().add(gear);
    }
    @When("{word} moves")
    public void scientist_moves(String scientistName){
        // Act
        scientists.get(scientistName).move();
    }
    @When("{word} moves to {word}")
    public void scientist_moves_to(String scientistName, String fieldName){
        // Act
        scientists.get(scientistName).move(fields.get(fieldName));
    }
    @When("{word} crafts from genetic code")
    public void scientist_crafts(String scientistName){
        // Act
        scientists.get(scientistName).craft(touchedGenCode);
    }
    @When("{word} kills {word}")
    public void scientist_kills(String scientistAName, String scientistBName){
        // Act
        killed = scientists.get(scientistAName).kill(scientists.get(scientistBName));
    }
    @And("{word} learns the genetic code on lab")
    public void scientist_learns_the_genetic_code(String scientistName){
        // Act
        scientists.get(scientistName).learn(touchedGenCode);
    }
    private void fieldsAreTheSameSuccess(Field fA, Field fB){
        // Assert
        assertSame(fA, fB);
    }
    private void fieldsAreNotTheSameSuccess(Field fA, Field fB){
        // Assert
        assertNotSame(fA, fB);
    }
    @Then("{word} current position {string} {word}")
    public void scientist_current_position_should_be(String scientistName, String shouldMatch, String fieldName){
        var scientistCurrentField = scientists.get(scientistName).getField();
        var scientistOldField = fields.get(fieldName);
        if(shouldMatch.equals("should be"))
            fieldsAreTheSameSuccess(scientistOldField, scientistCurrentField);
        else if(shouldMatch.equals("should not be"))
            fieldsAreNotTheSameSuccess(scientistOldField, scientistCurrentField);
    }

    private void scientistInventoryEmptySuccess(boolean empty){
        // Assert
        assertTrue(empty);
    }
    private void scientistInventoryNotEmptySuccess(boolean empty){
        // Assert
        assertFalse(empty);
    }
    @Then("{word} inventory called {word} {string} empty")
    public void scientist_inventory_should_be(String scientistName, String inventoryType, String matchText){
        Inventory inventory = scientists.get(scientistName).getInventory();
        boolean isEmpty = false;
        switch (inventoryType){
            case "crafted": isEmpty = inventory.getCrafted().isEmpty(); break;
            case "learned": isEmpty = inventory.getKnownGeneticCodes().isEmpty(); break;
            case "gears": isEmpty = inventory.getGears().isEmpty(); break;
            case "agents": isEmpty = inventory.getActiveAgents().isEmpty(); break;
            default: break;
        }
        if (matchText.equals("should be"))
            scientistInventoryEmptySuccess(isEmpty);
        else if (matchText.equals("should not be"))
            scientistInventoryNotEmptySuccess(isEmpty);
    }

    private void scientistKilledSuccess(){
        // Assert
        assertTrue(killed);
    }
    private void scientistAliveSuccess(){
        // Assert
        assertFalse(killed);
    }
    @Then("He/She should be {word}")
    public void scientist_alive_or_killed(String matchText){
        // Assert
        if(matchText.equals("killed"))
            scientistKilledSuccess();
        else if(matchText.equals("alive"))
            scientistAliveSuccess();
    }
}
