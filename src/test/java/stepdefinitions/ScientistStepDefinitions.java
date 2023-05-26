package test.java.stepdefinitions;

import components.agent.Agent;
import components.agent.GeneticCode;
import components.agent.Stun;
import components.scientist.ActnLabel;
import components.scientist.Inventory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static test.java.stepdefinitions.GameStepDefinitions.*;

import static org.junit.Assert.*;

public class ScientistStepDefinitions {

    private GeneticCode touchedGenCode;

    @Given("{word} has got the virus {Stun} with {int} duration")
    public void scientist_has_got_virus(String scientistName, Stun stun, int duration){
        // Arrange
        stun.setDuration(duration);
        stun.actionMgmt(ActnLabel.MOVE);
        scientists.get(scientistName).getInventory().addActiveAgent(stun);
    }
    @Given("{word} has active {agent} with {int}")
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
    @And("{word} touches the lab")
    public void scientist_starts_touching(String scientistName){
        // Act
        touchedGenCode = scientists.get(scientistName).touch().getCode();
    }
    @And("{word} learns the genetic code on lab")
    public void scientist_learns_the_genetic_code(String scientistName){
        // Act
        scientists.get(scientistName).learn(touchedGenCode);
    }
    @Then("{word} current position {string} {word}")
    public void scientist_current_position_should_be(String scientistName, String shouldMatch, String fieldName){
        // Assert
        var scientistCurrentField = scientists.get(scientistName).getField();
        var scientistOldField = fields.get(fieldName);
        if(shouldMatch.equals("should be"))
            assertSame(scientistOldField, scientistCurrentField);
        else if(shouldMatch.equals("should not be"))
            assertNotSame(scientistOldField, scientistCurrentField);
    }
    @Then("{word} should have learned the genetic code")
    public void scientist_should_have_learned_the_genetic_code(String scientistName){
        // Assert
        assertTrue(scientists.get(scientistName).getInventory().getKnownGeneticCodes().contains(touchedGenCode));
    }
    @Then("{word} inventory called {word} should be empty")
    public void scientist_inventory_should_be(String scientistName, String inventoryType){
        // Assert
        Inventory inventory = scientists.get(scientistName).getInventory();
        switch(inventoryType){
            case "crafted": assertTrue(inventory.getCrafted().isEmpty()); break;
            case "learned": assertTrue(inventory.getKnownGeneticCodes().isEmpty()); break;
            case "gears": assertTrue(inventory.getGears().isEmpty());
            case "agents": assertTrue(inventory.getActiveAgents().isEmpty());
            default: break;
        }
    }
}
