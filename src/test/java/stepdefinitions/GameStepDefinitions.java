package test.java.stepdefinitions;

import components.agent.GeneticCode;
import components.agent.Immunity;
import components.field.Field;
import components.field.Lab;
import components.scientist.Scientist;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import java.util.*;

public class GameStepDefinitions {

    public static Hashtable<String, Scientist> scientists;
    public static Hashtable<String, Field> fields;

    @Given("game has {Scientist} named {word}")
    public void game_has_scientists(Scientist scientist, String scientistName){
        // Arrange
        scientists = (scientists == null) ? new Hashtable<>() : scientists;
        scientists.put(scientistName, scientist);
    }
    @And("game has {Field} named {word}")
    public void game_has_fields(Field field, String fieldName){
        // Arrange
        fields = (fields == null) ? new Hashtable<>() : fields;
        fields.put(fieldName, field);
    }
    @And("game has {Lab} with {word} BearDance named {word}")
    public void game_has_laboratory(Lab Lab, String option, String fieldName){
        // Arrange
        fields = (fields == null) ? new Hashtable<>() : fields;
        Lab.setHasBear(option.equals("yes"));
        fields.put(fieldName, Lab);
    }
    @And("{word} has genetic code {Immunity} with {int} duration")
    public void lab_has_genetic_code_with_duration(String labName, Immunity immunity, int duration){
        // Arrange
        var lab = (Lab)fields.get(labName);
        immunity.setDuration(duration);
        GeneticCode genCode = new GeneticCode(immunity);
        lab.add(genCode);
    }
    @And("all of the fields are neighbours to each other")
    public void all_of_the_fields_are_neighbours_to_each_other(){
        // Arrange
        ArrayList<Field> fieldList = new ArrayList<>(fields.values());
        for(int i = 0; i<fieldList.size()-1; i++){
            for(int j = i+1; j< fieldList.size(); j++){
                Field a = fieldList.get(i);
                Field b = fieldList.get(j);
                a.setNeighbour(b.toString());
                b.setNeighbour(a.toString());
            }
        }
    }
}
