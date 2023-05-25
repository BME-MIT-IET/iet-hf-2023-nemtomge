package test.java;

import components.agent.GeneticCode;
import components.agent.Immunity;
import components.agent.Stun;
import components.field.Field;
import components.field.ItemPackage;
import components.field.Lab;
import components.scientist.ActnLabel;
import components.scientist.Scientist;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class StepFieldStepDefinitions {
    private Scientist scientist;
    private Field startField;
    private Field endField;
    private Lab lab;
    private GeneticCode touchedGenCode;

    // ----- Background -----
    @Given("the game has one scientist")
    public void the_game_has_one_scientist(){
        scientist = new Scientist();
    }
    @And("the game has two field")
    public void the_game_has_two_field(){
        startField = new Field();
        endField = new Field();
        startField.setNeighbour(endField.toString());
        endField.setNeighbour(startField.toString());
    }
    @And("the game has one laboratory with Immunity genetic code")
    public void the_game_has_one_laboratory_with_immunity_genetic_code(){
        lab = new Lab(false);
        Immunity agentImmunity = new Immunity(10);
        GeneticCode genCodeImmunity = new GeneticCode(agentImmunity);
        lab.add(genCodeImmunity);
    }
    @And("all of the fields are neighbours to each other")
    public void all_of_the_fields_are_neighbours_to_each_other(){
        startField.setNeighbour(endField.toString());
        startField.setNeighbour(lab.toString());
        endField.setNeighbour(startField.toString());
        endField.setNeighbour(lab.toString());
        lab.setNeighbour(startField.toString());
        lab.setNeighbour(endField.toString());
    }
    @And("the one scientist's position is one of the above field")
    public void the_one_scientists_position_is_one_of_the_above_field(){
        scientist.setField(startField);
    }

    // ----- Scenario: Scientist Steps On Field -----
    @When("the scientist moves")
    public void the_user_move_its_scientist(){
        scientist.move();
    }
    @Then("scientist current position should be changed")
    public void scientist_current_position_should_be_not(){
        assertNotSame(startField, scientist.getField());
    }

    // ----- Scenario: Scientist Unable to Move -----
    @Given("the scientist has got the Stun Virus")
    public void the_scientist_has_got_the_stun_virus(){
        Stun stunAgent = new Stun(10);
        stunAgent.actionMgmt(ActnLabel.MOVE);
        scientist.getInventory().addActiveAgent(stunAgent);
    }
    @Then("scientist current position should not be changed")
    public void scientist_current_position_should_not_be_changed(){
        assertSame(startField, scientist.getField());
    }

    // ----- Scenario: Scientist Learns Genetic Code -----
    @When("the scientist moves to a laboratory")
    public void the_scientist_moves_to_a_laboratory(){
        scientist.move(lab);
    }
    @And("the scientist touches")
    public void the_scientist_starts_to_touching(){
        touchedGenCode = scientist.touch().getCode();
    }
    @And("the scientist learns the genetic code")
    public void the_scientist_learns_the_genetic_code(){
        scientist.learn(touchedGenCode);
    }
    @Then("scientist should have learned the genetic code")
    public void scientist_should_have_learned_the_genetic_code(){
        assertTrue(scientist.getInventory().getKnownGeneticCodes().contains(touchedGenCode));
    }
}
