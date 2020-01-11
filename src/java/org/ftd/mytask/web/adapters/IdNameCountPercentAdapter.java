package org.ftd.mytask.web.adapters;

import java.io.Serializable;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 1.0.0 - 2018-06-29
 *
 */
public class IdNameCountPercentAdapter implements Serializable {

    private static final long serialVersionUID = 52149456458406819L;
    
    private Long id;
    private String name;
    private String count;
    private String percent;

    public IdNameCountPercentAdapter(Long id, String name, String count, String percent) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.percent = percent;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }







}
