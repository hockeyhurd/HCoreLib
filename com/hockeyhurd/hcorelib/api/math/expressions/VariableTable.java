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

    private static final VariableTable inst = new VariableTable();

    private Map<Integer, Map<String, Variable>> table;

    private VariableTable() {
        table = new TreeMap<Integer,  Map<String, Variable>>();
    }

    public boolean put(int accessID, Variable variable) {
        if (variable == null)
            return false;

        Map<String, Variable> varMapping = table.get(accessID);

        if (varMapping == null)
            varMapping = new HashMap<String, Variable>(0x40, 2.0f / 3.0f);

        return varMapping.put(variable.toString(), variable) != null;
    }

    public Variable getVariable(int accessID, String var) {
        if (var == null || var.isEmpty())
            return null;

        else if (var.equals("pi") || var.equals("" + GlobalConstants.PI_CHAR))
            return GlobalConstants

        Map<String, Variable> varMapping = table.get(accessID);

        if (varMapping == null)
            return null;

        return varMapping.get(var);
    }

}
