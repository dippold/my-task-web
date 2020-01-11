package org.ftd.mytask.web.adapters;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 2018-06-19
 *
 */
public class FieldDefinition implements Serializable {

    public static final String TYPE_INPUT = "input";
    public static final String TYPE_EMAIL = "email";
    public static final String TYPE_PASSWD = "passwd";
    public static final String TYPE_TEXT = "text";
    public static final String TYPE_COMBO = "combobox";
    public static final String TYPE_NUMBER = "number";
    public static final String TYPE_DATE = "date";
    
    private static final long serialVersionUID = -4434119741229435375L;

    private int divSize;
    private String type;
    private String id;
    private String label;
    private String value;
    private List<IdNameAdapter> values;
    private String placeholder;
    private String required;
    private String readonly;
    private int maxSize;
    private int rows;

    public FieldDefinition(int divSize, String type, String id, String label, String value, List<IdNameAdapter> values, String placeholder, String required, String readonly, int maxSize, int rows) {
        this.divSize = divSize;
        this.type = type;
        this.id = id;
        this.label = label;
        this.value = value;
        this.values = values;
        this.placeholder = placeholder;
        this.required = required;
        this.readonly = readonly;
        this.maxSize = maxSize;
        this.rows = rows;
    }

    public int getDivSize() {
        return divSize;
    }

    public void setDivSize(int divSize) {
        this.divSize = divSize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<IdNameAdapter> getValues() {
        return values;
    }

    public void setValues(List<IdNameAdapter> values) {
        this.values = values;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getReadonly() {
        return readonly;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    
    
}
