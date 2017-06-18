package com.hockeyhurd.hcorelib.api.math.expressions;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Variable table for holding pairs of variables and values.
 *
 * @author hockeyhurd
 * @version 4/15/17
 */
public final class VariableTable {

    static final Variable VAR_PI = new Variable(Character.toString(GlobalConstants.PI_CHAR), GlobalConstants.MATH_PI);
    static final Variable VAR_E = new Variable("e", GlobalConstants.MATH_E);

    private static final VariableTable inst = new VariableTable();

    private Map<Integer, Map<String, Variable>> table;

    /*private static Map<String, Variable> publicTable;

    static {
        publicTable = new HashMap<String, Variable>();
        publicTable.put(VAR_PI.toString(), VAR_PI);
        publicTable.put(VAR_E.toString(), VAR_E);
    }*/

    private VariableTable() {
        table = new TreeMap<Integer,  Map<String, Variable>>();
    }

    public static VariableTable getInstance() {
        return inst;
    }

    public boolean put(int accessID, Variable variable) {
        if (variable == null)
            return false;

        Map<String, Variable> varMapping = table.get(accessID);

        if (varMapping == null) {
            varMapping = new HashMap<String, Variable>(0x40, 2.0f / 3.0f);
            table.put(accessID, varMapping);
        }

        return varMapping.put(variable.toString(), variable) != null;
    }

    public Variable getVariable(int accessID, String var) {
        if (var == null || var.isEmpty())
            return null;

        else if (var.equals("pi") || var.equals(Character.toString(GlobalConstants.PI_CHAR)))
            return VAR_PI;

        else if (var.equals(VAR_E.toString()))
            return VAR_E;

        Map<String, Variable> varMapping = table.get(accessID);

        if (varMapping == null)
            return null;

        return varMapping.get(var);
    }

}
