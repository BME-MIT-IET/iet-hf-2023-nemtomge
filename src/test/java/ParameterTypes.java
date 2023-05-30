package test.java;

import components.agent.*;
import components.field.Field;
import components.field.Lab;
import components.gear.Axe;
import components.gear.Bag;
import components.gear.Gear;
import components.scientist.Scientist;
import io.cucumber.java.ParameterType;

public class ParameterTypes {
    private final int INIT_DURATION = 10;

    @ParameterType("Scientist")
    public Scientist Scientist(String text) {
        return new Scientist();
    }
    @ParameterType("Field")
    public Field Field(String text){ return new Field(); }
    @ParameterType("Lab")
    public Lab Lab(String text){
        boolean INIT_BEAR_DANCE = false;
        return new Lab(INIT_BEAR_DANCE); }
    @ParameterType("Immunity")
    public Immunity Immunity(String text) { return new Immunity(INIT_DURATION); }
    @ParameterType("Dementia|Stun|Bear")
    public Agent agent(String agentText){
        Agent agent;
        switch (agentText){
            case "Dementia": agent = new Dementia(INIT_DURATION); break;
            case "Stun": agent = new Stun(INIT_DURATION); break;
            case "Bear": agent = new Bear(INIT_DURATION); break;
            default: agent = null; break;
        }
        return agent;
    }
    @ParameterType("Axe")
    public Gear gear (String gearText){
        Gear gear;
        switch (gearText){
            case "Axe": gear = new Axe(); break;
            case "Bag": gear = new Bag(); break;
            default: gear = null; break;
        }
        return gear;
    }
}
