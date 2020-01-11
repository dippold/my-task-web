package org.ftd.mytask.web.adapters;

import java.io.Serializable;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 1.0.0 - 2018-06-22
 *
 */
public class IdNameGroupAdapter implements Serializable {
    
    private Long id;
    private String name;
    private String group;

    public IdNameGroupAdapter(Long id, String name, String group) {
        setId(id);
        setName(name);
        setGroup(group);
    }

    public Long getId() {
        return id;
    }

    public final void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public final void setGroup(String group) {
        this.group = group;
    }



}
