package org.ftd.mytask.web.adapters;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 2018-06-22
 * 
 */
public class FormDefinition implements Serializable {

    private static final long serialVersionUID = 6469367213093982186L;
    
    private String title;
    private String id;
    private String method;
    private String urlToGo;
    private List<FieldDefinition> fields;
    private List<ButtonDefinition> buttons;

    public FormDefinition(String title, String id, String method, String urlToGo, List<FieldDefinition> fields, List<ButtonDefinition> buttons) {
        this.title = title;
        this.id = id;
        this.method = method;
        this.urlToGo = urlToGo;
        this.fields = fields;
        this.buttons = buttons;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrlToGo() {
        return urlToGo;
    }

    public void setUrlToGo(String urlToGo) {
        this.urlToGo = urlToGo;
    }

    public List<FieldDefinition> getFields() {
        return fields;
    }

    public void setFields(List<FieldDefinition> fields) {
        this.fields = fields;
    }

    public List<ButtonDefinition> getButtons() {
        return buttons;
    }

    public void setButtons(List<ButtonDefinition> buttons) {
        this.buttons = buttons;
    }        
    
}
