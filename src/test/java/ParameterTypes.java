package test.java;

import components.agent.Immunity;
import components.agent.Stun;
import components.field.Field;
import components.field.Lab;
import components.scientist.Scientist;
import io.cucumber.java.ParameterType;
import io.cucumber.java.ja.且つ;

public class ParameterTypes {
    @ParameterType("Scientist")
    public Scientist Scientist(String text) {
        return new Scientist();
    }
    @ParameterType("Field")
    public Field Field(String text){ return new Field(); }
    @ParameterType("Lab")
    public Lab Lab(String text){ return new Lab(false); }
    @ParameterType("Immunity")
    public Immunity Immunity(String text) { return new Immunity(10); }
    @ParameterType("Stun")
    public Stun Stun(String text){ return new Stun(10); }
}
