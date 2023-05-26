package test.java;

import components.agent.Agent;
import components.agent.Dementia;
import components.agent.Immunity;
import components.agent.Stun;
import components.field.Field;
import components.field.Lab;
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
    @ParameterType("Stun")
    public Stun Stun(String text){ return new Stun(INIT_DURATION); }
    @ParameterType("Dementia|Stun")
    public Agent agent(String agentText){
        Agent agent;
        switch (agentText){
            case "Dementia": agent = new Dementia(INIT_DURATION); break;
            case "Stun": agent = new Stun(INIT_DURATION); break;
            default: agent = null; break;
        }
        return agent;
    }
}
