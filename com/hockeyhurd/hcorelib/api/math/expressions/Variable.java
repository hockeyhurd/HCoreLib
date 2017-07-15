package com.hockeyhurd.hcorelib.api.math.expressions;

/**
 * @author hockeyhurd
 * @version 4/15/17
 */
class Variable extends EToken {

    private String var;
    private double value;

    /**
     * @param var Variable name.
     */
    Variable(String var) {
        this(var, 0.0d);
    }

    /**
     * @param var Variable name.
     * @param value double value.
     */
    Variable(String var, double value) {
        super("<", ">");
        this.var = var;
        this.value = value;
    }

    /**
     * Sets the value.
     *
     * @param value double.
     */
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    double evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return var;
    }
}
