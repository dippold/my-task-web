package org.ftd.mytask.web.cmds;

import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.ftd.educational.mytask.persistence.daos.LogDAO;
import org.ftd.educational.mytask.persistence.daos.UserDAO;
import org.ftd.educational.mytask.persistence.entities.Log;
import org.ftd.educational.mytask.persistence.entities.User;
import org.ftd.mytask.web.mvc.abstracts.MVC;
import org.ftd.mytask.web.mvc.interfaces.ICmd;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 1.0.0 - 2018-05-17
 *
 */
public class LogOutCmd implements ICmd {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);

        if (session != null) {

            EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
            UserDAO userDAO = new UserDAO(factory);
            User u = userDAO.find(Long.parseLong((String) session.getAttribute("userId")));
            
            LogDAO logDAO = new LogDAO(factory);
            Log log = new Log();
            log.setName(MVC.LOG_ACTION_SIGN_OUT);
            log.setCreatedOn(new Date());
            log.setCompanyId(MVC.getUserCompanySessionId(request));
            log.setUserId(u.getName());
            log.setEntityId(this.getClass().getSimpleName());
            logDAO.create(log);

            session.removeAttribute("userId");
            session.removeAttribute("userName");
            session.removeAttribute("companyId");
            session.removeAttribute("ruleId");
            session.invalidate();
        }

        String msg = (String) request.getAttribute(MVC.PARAMETER_NAME_MESSAGE);
        if ((msg == null) || (msg.equals(""))) {
            request.setAttribute(MVC.PARAMETER_NAME_MESSAGE, MVC.MSG_USER_LOG_OUT);
        }

        return MVC.VIEW_SIGN_IN;
    }

}
