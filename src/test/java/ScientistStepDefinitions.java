package test.java;

import components.agent.GeneticCode;
import components.agent.Stun;
import components.scientist.ActnLabel;
import components.scientist.Scientist;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static test.java.GameStepDefinitions.*;

import static org.junit.Assert.*;

public class ScientistStepDefinitions {

    public static Scientist scientist;
    public static GeneticCode touchedGenCode;

    @Given("game has one scientist")
    public void game_has_one_scientist(){
        scientist = new Scientist();
    }
    @And("scientist's position is one of the above field")
    public void scientists_position_is_one_of_the_above_field(){scientist.setField(startField);}
    @When("scientist moves")
    public void scientist_moves(){
        scientist.move();
    }
    @Given("scientist has got the Stun Virus")
    public void scientist_has_got_the_stun_virus(){
        Stun stunAgent = new Stun(10);
        stunAgent.actionMgmt(ActnLabel.MOVE);
        scientist.getInventory().addActiveAgent(stunAgent);
    }
    @When("scientist moves to a laboratory")
    public void scientist_moves_to_a_laboratory(){scientist.move(lab);}
    @And("scientist touches")
    public void scientist_starts_to_touching(){
        touchedGenCode = scientist.touch().getCode();
    }
    @And("scientist learns the genetic code")
    public void scientist_learns_the_genetic_code(){
        scientist.learn(touchedGenCode);
    }
    @Then("scientist should have learned the genetic code")
    public void scientist_should_have_learned_the_genetic_code(){
        assertTrue(scientist.getInventory().getKnownGeneticCodes().contains(touchedGenCode));
    }
    @Then("scientist current position should be changed")
    public void scientist_current_position_should_be_changed(){
        assertNotSame(startField, scientist.getField());
    }
    @Then("scientist current position should not be changed")
    public void scientist_current_position_should_not_be_changed(){
        assertSame(startField, scientist.getField());
    }
}
