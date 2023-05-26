package test.java.stepdefinitions;

import components.agent.Agent;
import components.agent.GeneticCode;
import components.agent.Stun;
import components.scientist.ActnLabel;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static test.java.stepdefinitions.GameStepDefinitions.*;

import static org.junit.Assert.*;

public class ScientistStepDefinitions {
    private GeneticCode touchedGenCode;
    @And("{int}st/nd/rd/th scientist's position is the {int}st/nd/rd/th {word}")
    public void scientists_position_is(int scientistIndex, int whereIndex, String where){
        var field = fields.get(String.format("%s-%d", where, whereIndex));
        var scientist = scientists.get(scientistIndex);
        scientist.setField(field);
    }
    @When("{int}st/nd/rd/th scientist moves")
    public void scientist_moves(int index){
        scientists.get(index).move();
    }
    @Then("{int}st/nd/rd/th scientist current position {string} the {int}st/nd/rd/th {word}")
    public void scientist_current_position_should_be(int scientistIndex, String shouldMatch, int whereIndex, String where){
        var scientistCurrentField = scientists.get(scientistIndex).getField();
        var scientistOldField = fields.get(String.format("%s-%d", where, whereIndex));
        if(shouldMatch.equals("should be the same as"))
            assertSame(scientistOldField, scientistCurrentField);
        else if(shouldMatch.equals("should be not the same as"))
            assertNotSame(scientistOldField, scientistCurrentField);
    }
    @Given("{int}st/nd/rd/th scientist has got the virus {Stun} with {int} duration")
    public void scientist_has_got_virus(int scientistIndex, Stun stun, int duration){
        stun.setDuration(duration);
        stun.actionMgmt(ActnLabel.MOVE);
        var scientist = scientists.get(scientistIndex);
        scientist.getInventory().addActiveAgent(stun);
    }
    @When("{int}st/nd/rd/th scientist moves to {int}st/nd/rd/th {word}")
    public void scientist_moves_to(int scientistIndex, int whereIndex, String where){
        var scientist = scientists.get(scientistIndex);
        var whereTo = fields.get(String.format("%s-%d", where, whereIndex-1));
        scientist.move(whereTo);
    }
    @And("{int}st/nd/rd/th scientist touches")
    public void scientist_starts_touching(int scientistIndex){
        touchedGenCode = scientists.get(scientistIndex).touch().getCode();
    }
    @And("{int}st/nd/rd/th scientist learns the genetic code")
    public void scientist_learns_the_genetic_code(int scientistIndex){
        scientists.get(scientistIndex).learn(touchedGenCode);
    }
    @Then("{int}st/nd/rd/th scientist should have learned the genetic code")
    public void scientist_should_have_learned_the_genetic_code(int scientistIndex){
        assertTrue(scientists.get(scientistIndex).getInventory().getKnownGeneticCodes().contains(touchedGenCode));
    }
    @And("{int}st/nd/rd/th scientist learns genetic code")
    public void scientist_learns_genetic_code(int scientistIndex){
        scientist_starts_touching(scientistIndex);
        scientist_learns_the_genetic_code(scientistIndex);
    }
    @Given("{int}st/nd/rd/th scientist has active {agent} with {int}")
    public void scientist_has_active_agent(int scientistIndex, Agent agent, int duration){
        agent.setDuration(duration);
        scientists.get(scientistIndex).getInventory().addActiveAgent(agent);
    }
    @When("{int}st/nd/rd/th scientist crafts")
    public void scientist_crafts(int scientistIndex){
        scientists.get(scientistIndex).craft(touchedGenCode);
    }
    @Then("{int}st/nd/rd/th scientist inventory crafted should be empty")
    public void scientist_inventory_should_be(int scientistIndex){
        var inventoryEmpty = scientists.get(scientistIndex).getInventory().getCrafted().isEmpty();
        assertTrue(inventoryEmpty);
    }

}
