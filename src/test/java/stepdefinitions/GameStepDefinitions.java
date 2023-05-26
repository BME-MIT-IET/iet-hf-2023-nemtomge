package test.java.stepdefinitions;

import components.agent.GeneticCode;
import components.agent.Immunity;
import components.field.Field;
import components.field.Lab;
import components.scientist.Scientist;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import java.util.*;

public class GameStepDefinitions {

    public static ArrayList<Scientist> scientists;
    public static Hashtable<String, Field> fields;

    @Given("game has {int} {Scientist}(s)")
    public void game_has_scientists(int count, Scientist scientist){
        scientists = new ArrayList<>();
        for(int i = 0; i<count; ++i)
            scientists.add(scientist);
    }
    @And("game has {int} {Field}(s)")
    public void game_has_fields(int count, Field field){
        fields = new Hashtable<>();
        for(int i = 0; i<count; ++i)
            fields.put(String.format("field-%d",i), field);
    }
    @And("game has {int} {Lab} with {word} BearDance")
    public void game_has_laboratory(int count, Lab Lab, String option){
        fields = (fields == null) ? new Hashtable<>() : fields;
        boolean hasBearDance = option.equals("yes");
        for(int i = 0; i<count; ++i){
            Lab.setHasBear(hasBearDance);
            fields.put(String.format("lab-%d", i), Lab);
        }
    }
    @And("{int}st/nd/rd/th lab has genetic code {Immunity} with {int} duration")
    public void lab_has_genetic_code_with_duration(int fieldIndex, Immunity immunity, int duration){
        var lab = (Lab)fields.get(String.format("lab-%d", fieldIndex));
        immunity.setDuration(duration);
        GeneticCode genCode = new GeneticCode(immunity);
        lab.add(genCode);
    }
    @And("all of the fields are neighbours to each other")
    public void all_of_the_fields_are_neighbours_to_each_other(){
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
