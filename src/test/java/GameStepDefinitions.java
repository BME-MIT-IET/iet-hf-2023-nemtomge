package test.java;

import components.agent.GeneticCode;
import components.agent.Immunity;
import components.field.Field;
import components.field.Lab;
import io.cucumber.java.en.And;

public class GameStepDefinitions {

    public static Field startField;
    public static Field endField;
    public static Lab lab;

    @And("game has two field")
    public void game_has_two_field(){
        startField = new Field();
        endField = new Field();
        startField.setNeighbour(endField.toString());
        endField.setNeighbour(startField.toString());
    }
    @And("game has one laboratory with Immunity genetic code")
    public void game_has_one_laboratory_with_immunity_genetic_code(){
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

}
