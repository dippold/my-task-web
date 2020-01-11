package org.ftd.mytask.web.cmds;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ftd.educational.mytask.persistence.daos.LogDAO;
import org.ftd.educational.mytask.persistence.daos.PasswdDAO;
import org.ftd.educational.mytask.persistence.daos.UserDAO;
import org.ftd.educational.mytask.persistence.entities.Log;
import org.ftd.educational.mytask.persistence.entities.Passwd;
import org.ftd.educational.mytask.persistence.entities.User;
import org.ftd.mytask.web.mvc.abstracts.MVC;
import org.ftd.mytask.web.mvc.interfaces.ICmd;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 1.0.0 - 2018-06-21
 *
 */
public class ChangePasswdCmd extends MVC implements ICmd {

    @Override
    public String buildUpdateModel(HttpServletRequest request, HttpServletResponse response) {
        Long userId = MVC.getUserSessionId(request);

        request.setAttribute("appname", MVC.APP_NAME);
        request.setAttribute("title", "Alterando Senha");

        request.setAttribute("urlToGo",
                MVC.URL_MVC_SERVICE
                + "?" + MVC.PARAMETER_NAME_CMD
                + "=" + this.getClass().getSimpleName()
                + "&" + MVC.PARAMETER_NAME_ACTION
                + "=" + MVC.MVC_ACTION_UPDATE
                + "&" + MVC.PARAMETER_NAME_ID
                + "=" + userId
        );

        request.setAttribute("urlToGoBack",
                MVC.URL_MVC_SERVICE
                + "?" + MVC.PARAMETER_NAME_CMD
                + "=" + MVC.CMD_HOME
        );

        /* FIND DE ENTITY... */
        User o = this.findUser(userId);

        /* HTML FORM DEFINITIONS...*/
        request.setAttribute("nameFieldLabel", "Usuário logado");
        request.setAttribute("nameFieldId", "inputUserName");
        request.setAttribute("nameFieldValue", o.getName());
        request.setAttribute("nameFieldType", "input");
        request.setAttribute("nameFieldReadonly", "readonly");
        request.setAttribute("nameFieldRequired", "");
        request.setAttribute("nameFieldPlaceHolder", "");
        request.setAttribute("nameFieldMaxSize", "");
        request.setAttribute("nameFieldRows", "");

        request.setAttribute("currentPasswdFieldLabel", "Senha Atual");
        request.setAttribute("currentPasswdFieldId", "inputCurrentPasswd");
        request.setAttribute("currentPasswdFieldValue", "");
        request.setAttribute("currentPasswdFieldType", "passwd");
        request.setAttribute("currentPasswdFieldReadonly", "");
        request.setAttribute("currentPasswdFieldPlaceHolder", "Digite a senha atual!");
        request.setAttribute("currentPasswdFieldMaxSize", "30");
        request.setAttribute("currentPasswdFieldRows", "");

        request.setAttribute("newPasswdFieldLabel", "Nova Senha");
        request.setAttribute("newPasswdFieldId", "inputNewPasswd");
        request.setAttribute("newPasswdFieldValue", "");
        request.setAttribute("newPasswdFieldType", "passwd");
        request.setAttribute("newPasswdFieldReadonly", "");
        request.setAttribute("newPasswdFieldPlaceHolder", "Digite a nova senha!");
        request.setAttribute("newPasswdFieldMaxSize", "30");
        request.setAttribute("newPasswdFieldRows", "");

        request.setAttribute("checkNewPasswdFieldLabel", "Verificação");
        request.setAttribute("checkNewPasswdFieldId", "inputCheckNewPasswd");
        request.setAttribute("checkNewPasswdFieldValue", "");
        request.setAttribute("checkNewPasswdFieldType", "passwd");
        request.setAttribute("checkNewPasswdFieldReadonly", "");
        request.setAttribute("checkNewPasswdFieldPlaceHolder", "Digite novamente a nova senha!");
        request.setAttribute("checkNewPasswdFieldMaxSize", "30");
        request.setAttribute("checkNewPasswdFieldRows", "");

        return "WEB-INF/views/ChangePasswdView.jsp";
    }

    @Override
    public String doUpdate(HttpServletRequest request, HttpServletResponse response) {
        String msgValidation;
        Long userId = MVC.getUserSessionId(request);

        try {

            String inputCurrentPasswd = MVC.readParameter(request, "inputCurrentPasswd");
            String inputNewPasswd = MVC.readParameter(request, "inputNewPasswd");
            String inputCheckNewPasswd = MVC.readParameter(request, "inputCheckNewPasswd");

            EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
            PasswdDAO passwdDAO = new PasswdDAO(factory);
            Passwd currentPasswd = passwdDAO.findByUser(userId);

            /* VERIFICANDO SE A SENHA ATUAL DIGITADA ESTÁ CADASTRADA */
            if (currentPasswd.getName().equals(inputCurrentPasswd)) {

                /* VERIFICANDO SE A NOVA SENHA CONFERE COM A SENHA A VALIDAÇÃO */
                if (inputNewPasswd.equals(inputCheckNewPasswd)) {

                    /* MUDAR OS STATUS DE TODAS AS SENHAS ATUAIS */
                    List<Passwd> userPasswds = passwdDAO.findAllByUser(userId);
                    for (Passwd o : userPasswds) {
                        o.setPasswdStatusId(2L);
                        passwdDAO.edit(o);
                    }

                    /* CRIAR A NOVA SENHA */
                    Passwd newPasswd = new Passwd();
                    newPasswd.setUserId(userId);
                    newPasswd.setName(inputNewPasswd);
                    newPasswd.setPasswdStatusId(1L);
                    newPasswd.setCreatedOn(new Date());
                    passwdDAO.create(newPasswd);

                    /* REGISTRA O LOG DA AÇÃO */
                    UserDAO userDAO = new UserDAO(factory);
                    User user = userDAO.find(MVC.getUserSessionId(request));                            
                    LogDAO logDAO = new LogDAO(factory);
                    Log log = new Log();
                    log.setName(MVC.LOG_ACTION_UPDATE);
                    log.setCreatedOn(new Date());
                    log.setCompanyId(MVC.getUserCompanySessionId(request));
                    log.setUserId(user.getName());
                    log.setEntityId(this.getClass().getSimpleName());
                    logDAO.create(log);

                    msgValidation = MVC.MSG_CHANGE_PASSWD_SUCESS;

                } else {

                    msgValidation = MVC.MSG_CHANGE_PASSWD_NEW_CHANGE_DONT_MATCH;

                }

            } else {

                msgValidation = MVC.MSG_CHANGE_PASSWD_CURRENT_PASSWD_DONT_MACTH;

            }

            request.setAttribute(MVC.PARAMETER_NAME_MESSAGE, msgValidation);

            return MVC.URL_MVC_SERVICE
                    + "?" + MVC.PARAMETER_NAME_CMD
                    + "=" + this.getClass().getSimpleName()
                    + "&" + MVC.PARAMETER_NAME_ACTION
                    + "=" + MVC.MVC_ACTION_BUILD_UPD_MODEL;

        } catch (Exception e) {

            request.setAttribute(MVC.PARAMETER_NAME_MESSAGE, MVC.MSG_CRUD_UPDATE_FAILURE);

            return MVC.URL_MVC_SERVICE
                    + "?" + MVC.PARAMETER_NAME_CMD
                    + "=" + this.getClass().getSimpleName()
                    + "&" + MVC.PARAMETER_NAME_ACTION
                    + "=" + MVC.MVC_ACTION_BUILD_UPD_MODEL;

        }
    }

    @Override
    public String buildGridModel(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buildAddNewModel(HttpServletRequest request, HttpServletResponse response) {
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
    public String doRemove(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private User findUser(Long id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        UserDAO dao = new UserDAO(factory);

        return dao.find(id);
    }

}
