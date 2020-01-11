package org.ftd.mytask.web.adapters;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 2018-07-09
 *
 */
public class Portlet implements Serializable {
    
    private String icon;
    private String title;
    private String[] headers;
    private int count;
    private List<Object> datasource;
    private ButtonDefinition button;

    public Portlet(String icon, String title, String[] headers, int count, List<Object> datasource, ButtonDefinition button) {
        this.icon = icon;
        this.title = title;
        this.headers = headers;
        this.count = count;
        this.datasource = datasource;
        this.button = button;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Object> getDatasource() {
        return datasource;
    }

    public void setDatasource(List<Object> datasource) {
        this.datasource = datasource;
    }

    public ButtonDefinition getButton() {
        return button;
    }

    public void setButton(ButtonDefinition button) {
        this.button = button;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }    

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }
    
}
