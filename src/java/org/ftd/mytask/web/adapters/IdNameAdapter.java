package org.ftd.mytask.web.adapters;

import java.io.Serializable;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 1.0.0 - 2018-06-18
 *
 */
public class IdNameAdapter implements Serializable {

    private static final long serialVersionUID = -941648625088241740L;
    
    private Long id;
    private String name;

    public IdNameAdapter(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
