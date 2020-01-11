package org.ftd.mytask.web.adapters;

import java.io.Serializable;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 2018-06-26
 *
 */
public class ButtonDefinition implements Serializable {

    public static final String TYPE_SUBMIT = "submit";
    public static final String TYPE_RESET = "reset";
    public static final String TYPE_SIMPLE= "button";

    private String type;
    private String id;
    private String label;
    private String cssClass;
    private String url;

    public ButtonDefinition(String type, String id, String label, String cssClass, String url) {
        this.type = type;
        this.id = id;
        this.label = label;
        this.cssClass = cssClass;
        this.url = url;
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

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    
    
}
