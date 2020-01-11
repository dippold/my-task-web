package org.ftd.mytask.web.cmds;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ftd.educational.mytask.persistence.daos.LogDAO;
import org.ftd.mytask.web.mvc.abstracts.MVC;
import org.ftd.mytask.web.mvc.interfaces.ICmd;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 1.0.0 - 2018-06-21
 * 
 */
public class LogCmd extends MVC implements ICmd {

    @Override
    public String buildGridModel(HttpServletRequest request, HttpServletResponse response) {        
        request.setAttribute("title", "Logs");

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        LogDAO dao = new LogDAO(factory);        
        request.setAttribute("datasource", dao.findAll(MVC.getUserCompanySessionId(request)));        
            
        final String[] headers = {"Data", "Usuário", "Ação", "Entidade","Campo","Old Value","New Value"};
        request.setAttribute("headers", headers);
        
        return "WEB-INF/views/LogListView.jsp";
    }

    
    
    @Override
    public String buildAddNewModel(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buildUpdateModel(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buildViewModel(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String doAddNew(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String doUpdate(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String doRemove(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
