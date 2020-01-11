package org.ftd.mytask.web.mvc.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 2018-05-17 - 1.0.2
 * 
 */
public interface ICmd {
    
    String execute(HttpServletRequest request, HttpServletResponse response);
    
}
