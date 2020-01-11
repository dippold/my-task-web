package org.ftd.mytask.web.services;

import java.io.IOException;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.ftd.educational.mytask.persistence.daos.LogDAO;
import org.ftd.educational.mytask.persistence.daos.PasswdDAO;
import org.ftd.educational.mytask.persistence.daos.UserDAO;
import org.ftd.educational.mytask.persistence.entities.Log;
import org.ftd.educational.mytask.persistence.entities.Passwd;
import org.ftd.educational.mytask.persistence.entities.User;
import org.ftd.mytask.web.mvc.abstracts.MVC;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 1.0.0 - 2018-05-17
 *
 */
@WebServlet(name = "SignInServiceServlet", urlPatterns = {"/signin"})
public class SignInServiceServlet extends HttpServlet {

    private static final long serialVersionUID = 2388257921769362666L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String email = MVC.readParameter(request, "email");
        String passwd = MVC.readParameter(request, "passwd");

        if ((email != null) && (passwd != null)) {
            
            User user = this.findUser(email);            
            if ((user != null) && (!user.isBlocked())) {

                Passwd oPasswd = this.findPasswd(user);
                if ((oPasswd != null) && (oPasswd.getName().equals(passwd))) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("userId", Long.toString(user.getId()));
                    session.setAttribute("userName", user.getName());
                    session.setAttribute("companyId", Long.toString(user.getCompanyId()));
                    session.setAttribute("ruleId", Long.toString(user.getRuleId()));
                    
                    EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
                    LogDAO dao = new LogDAO(factory);
                    Log log = new Log();
                    log.setName(MVC.LOG_ACTION_SIGN_IN);
                    log.setCreatedOn(new Date());
                    log.setCompanyId(user.getCompanyId());
                    log.setUserId(user.getName());
                    log.setEntityId(this.getClass().getSimpleName());
                    dao.create(log);
                    
                    final String resource = MVC.URL_MVC_SERVICE
                            + "?"
                            + MVC.PARAMETER_NAME_CMD
                            + "="
                            + MVC.CMD_HOME;
                    
                    request.getRequestDispatcher(resource).forward(request, response);
                    
                } else {
                    
                    request.setAttribute(MVC.PARAMETER_NAME_MESSAGE, MVC.MSG_INVALID_PASSWD);
                    request.getRequestDispatcher(MVC.VIEW_SIGN_IN).forward(request, response);
                    
                }
            } else {
                
                request.setAttribute(MVC.PARAMETER_NAME_MESSAGE, MVC.MSG_INVALID_EMAIL);
                request.getRequestDispatcher(MVC.VIEW_SIGN_IN).forward(request, response);
                
            }
        } else {
            
            request.setAttribute(MVC.PARAMETER_NAME_MESSAGE, MVC.MSG_INVALID_LOGIN_INFO);
            request.getRequestDispatcher(MVC.VIEW_SIGN_IN).forward(request, response);
            
        }
    }

    private User findUser(String email) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        UserDAO dao = new UserDAO(factory);
        User user;
        try {
            user = dao.findUserByEmail(email);
        } catch (NoResultException e) {
            user = null;
        }

        return user;
    }

    private Passwd findPasswd(User user) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        PasswdDAO dao = new PasswdDAO(factory);
        Passwd passwd;
        try {
            passwd = dao.findByUser(user.getId());
        } catch (NoResultException e) {
            passwd = null;
        }

        return passwd;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
