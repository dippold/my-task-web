package org.ftd.mytask.web.adapters;

import org.ftd.educational.mytask.persistence.entities.AbstractEntity;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 1.0.0 - 2018-06-18
 *
 */
public class BasicListEntityAdapter {
    private Long id;
    private String description;

    public BasicListEntityAdapter(AbstractEntity o) {
        setId(o.getId());
        setDescription(o.getName());
    }

    public Long getId() {
        return id;
    }

    public final void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public final void setDescription(String description) {
        this.description = description;
    }

}
