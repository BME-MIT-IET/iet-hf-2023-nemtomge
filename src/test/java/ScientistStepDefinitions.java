package test.java;

import components.agent.GeneticCode;
import components.agent.Stun;
import components.scientist.ActnLabel;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static test.java.GameStepDefinitions.*;

import static org.junit.Assert.*;

public class ScientistStepDefinitions {
    private GeneticCode touchedGenCode;
    @And("{int}(st/nd/rd/th) scientist's position is the {int}(st/nd/rd/th) {word}")
    public void scientists_position_is(int scientistIndex, int fieldIndex, String where){
        var field = fields.get(String.format("%s-%d", where, fieldIndex-1));
        var scientist = scientists.get(scientistIndex-1);
        scientist.setField(field);
    }
    @When("{int}(st/nd/rd/th) scientist moves")
    public void scientist_moves(int index){
        scientists.get(index-1).move();
    }
    @Then("{int}(st/nd/rd/th) scientist current position {string} the {int}(st/nd/rd/th) {word}")
    public void scientist_current_position_should_be(int scientistIndex, String shouldMatch, int fieldIndex, String where){
        var scientistCurrentField = scientists.get(scientistIndex-1).getField();
        var scientistOldField = fields.get(String.format("%s-%d", where, fieldIndex-1));
        if(shouldMatch.equals("should be the same as"))
            assertSame(scientistOldField, scientistCurrentField);
        else if(shouldMatch.equals("should be not the same as"))
            assertNotSame(scientistOldField, scientistCurrentField);
    }
    @Given("{int}(st/nd/rd/th) scientist has got the virus {Stun} with {int} duration")
    public void scientist_has_got_virus(int scientistIndex, Stun stun, int duration){
        stun.setDuration(duration);
        stun.actionMgmt(ActnLabel.MOVE);
        var scientist = scientists.get(scientistIndex-1);
        scientist.getInventory().addActiveAgent(stun);
    }
    @When("{int}(st/nd/rd/th) scientist moves to {int}(st/nd/rd/th) {word}")
    public void scientist_moves_to(int scientistIndex, int whereIndex, String where){
        var scientist = scientists.get(scientistIndex-1);
        var whereTo = fields.get(String.format("%s-%d", where, whereIndex-1));
        scientist.move(whereTo);
    }
    @And("{int}(st/nd/rd/th) scientist touches")
    public void scientist_starts_touching(int index){
        touchedGenCode = scientists.get(index-1).touch().getCode();
    }
    @And("{int}(st/nd/rd/th) scientist learns the genetic code")
    public void scientist_learns_the_genetic_code(int index){
        scientists.get(index-1).learn(touchedGenCode);
    }
    @Then("{int}(st/nd/rd/th) scientist should have learned the genetic code")
    public void scientist_should_have_learned_the_genetic_code(int index){
        assertTrue(scientists.get(index-1).getInventory().getKnownGeneticCodes().contains(touchedGenCode));
    }


}
