package org.ftd.mytask.web.mvc.abstracts;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 2.0.0 - 2018-05-17
 *
 */
public abstract class MVC {
    public static final String APP_NAME = "MyTask";

    public static final String PERSISTENCE_UNIT = "my-task-PU";
    public static final String CMD_PACKAGE = "org.ftd.mytask.web.cmds";

    public static final String URL_MVC_SERVICE = "mvc";
    public static final String URL_AUTHENTICATION_SERVICE = "signin";

    public static final String PARAMETER_NAME_CMD = "class";
    public static final String PARAMETER_NAME_ACTION = "do";
    public static final String PARAMETER_NAME_ID = "id";
    public static final String PARAMETER_NAME_PARENT_ID = "pid";
    public static final String PARAMETER_NAME_MESSAGE = "msg";

    public static final String VIEW_SIGN_IN = "SignIn.jsp";

    public static final String CMD_HOME = "HomeCmd";
    public static final String CMD_LOG_OUT = "LogOutCmd";

    public static final String MVC_ACTION_BUILD_GRID_MODEL = "0";
    public static final String MVC_ACTION_BUILD_ADD_MODEL = "1";
    public static final String MVC_ACTION_BUILD_UPD_MODEL = "2";
    public static final String MVC_ACTION_BUILD_VIEW_MODEL = "3";

    public static final String MVC_ACTION_ADDNEW = "4";
    public static final String MVC_ACTION_UPDATE = "5";
    public static final String MVC_ACTION_DELETE = "6";

    public static final String MSG_USER_LOG_OUT = "O usuário solicitou sair!";
    public static final String MSG_INVALID_SESSION = "Sessão inválida! Logue-se novamente.";
    public static final String MSG_INVALID_CMD = "Erro MVC: Não encontrei a Classe ";
    public static final String MSG_INVALID_ACTION = "Erro CMD: Não encontrei a Action ";
    public static final String MSG_INVALID_EMAIL = "E-mail não registrado ou bloqueado!";
    public static final String MSG_INVALID_PASSWD = "Senha inválida!";
    public static final String MSG_INVALID_LOGIN_INFO = "Informe email e senha p/ autenticação!";

    public static final String MSG_CRUD_CREATE_SUCESS = "Registro criado!";
    public static final String MSG_CRUD_CREATE_FAILURE = "Criar novo registro falhou!";
    public static final String MSG_CRUD_UPDATE_SUCESS = "Registro atualizado!";
    public static final String MSG_CRUD_UPDATE_FAILURE = "Editar o registro falhou!";
    public static final String MSG_CRUD_DELETE_SUCESS = "Registro deletado!";
    public static final String MSG_CRUD_DELETE_FAILURE = "Deletar o registro falhou!";
    
    public static final String MSG_CHANGE_PASSWD_SUCESS = "Senha alterada com sucesso!";
    public static final String MSG_CHANGE_PASSWD_CURRENT_PASSWD_DONT_MACTH = "A senha atual não confere!";
    public static final String MSG_CHANGE_PASSWD_NEW_CHANGE_DONT_MATCH = "A nova senha é diferente da validação!";
    public static final String MSG_RESET_PASSWD_SUCESS = "Foi reinicializada do usuário ";
    public static final String MSG_RESET_PASSWD_FAILURE = "Falha na reinicializada da senha do usuário ";
    
    public static final String KEY_NAME_DEFAULT_PASSWD = "default.passwd";
    
    public static final String LOG_ACTION_SIGN_IN = "SignIn";
    public static final String LOG_ACTION_SIGN_OUT = "SignOut";
    public static final String LOG_ACTION_CREATE = "Create";
    public static final String LOG_ACTION_UPDATE = "Update";
    public static final String LOG_ACTION_DELETE = "Delete";
    
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final String action = readParameter(request, PARAMETER_NAME_ACTION, "");
        final String nextAction;

        switch (action) {
            case MVC_ACTION_BUILD_GRID_MODEL:
                nextAction = buildGridModel(request, response);
                break;
            case MVC_ACTION_BUILD_ADD_MODEL:
                nextAction = buildAddNewModel(request, response);
                break;
            case MVC_ACTION_BUILD_UPD_MODEL:
                nextAction = buildUpdateModel(request, response);
                break;
            case MVC_ACTION_BUILD_VIEW_MODEL:
                nextAction = buildViewModel(request, response);
                break;
            case MVC_ACTION_ADDNEW:
                nextAction = doAddNew(request, response);
                break;
            case MVC_ACTION_UPDATE:
                nextAction = doUpdate(request, response);
                break;
            case MVC_ACTION_DELETE:
                nextAction = doRemove(request, response);
                break;
            default:
                request.setAttribute(PARAMETER_NAME_MESSAGE, MSG_INVALID_ACTION);
                nextAction = URL_MVC_SERVICE
                        + "?"
                        + PARAMETER_NAME_CMD
                        + "="
                        + CMD_LOG_OUT
                        + "&"
                        + PARAMETER_NAME_MESSAGE
                        + "="
                        + MSG_INVALID_ACTION;
        }

        MVC.setDefaultAttributes(request);
        
        return nextAction;
    }

    public static void setDefaultAttributes(HttpServletRequest request) {
        request.setAttribute("appname", MVC.APP_NAME);
        request.setAttribute("userId", MVC.getUserSessionId(request));
        request.setAttribute("userName", MVC.getUserFirstNameSession(request));
        request.setAttribute("companyId", MVC.getUserCompanySessionId(request));
        request.setAttribute("ruleId", MVC.getUserRuleSessionId(request));
    }
    
    public static String readParameter(HttpServletRequest req, String parameterName, String defaultValue) {
        String value = req.getParameter(parameterName);
        if ((value == null) || (value.equals(""))) {
            value = defaultValue;
        }

        return value;
    }

    public static String readParameter(HttpServletRequest req, String parameterName) {

        return readParameter(req, parameterName, "");
    }

    /*
     * ABSTRACTS MEMBERS...
     */
    public abstract String buildGridModel(HttpServletRequest request, HttpServletResponse response);

    public abstract String buildAddNewModel(HttpServletRequest request, HttpServletResponse response);

    public abstract String buildUpdateModel(HttpServletRequest request, HttpServletResponse response);

    public abstract String buildViewModel(HttpServletRequest request, HttpServletResponse response);

    public abstract String doAddNew(HttpServletRequest request, HttpServletResponse response);

    public abstract String doUpdate(HttpServletRequest request, HttpServletResponse response);

    public abstract String doRemove(HttpServletRequest request, HttpServletResponse response);

    /*
     * UTILITY MEMBERS...
     */
    public static Long getUserSessionId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        return Long.parseLong((String) session.getAttribute("userId"));
    }

    public static String getUserNameSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        return (String) session.getAttribute("userName");
    }

    public static String getUserFirstNameSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String userName = (String) session.getAttribute("userName");
        String[] names = userName.split(" ");

        return names[0];
    }

    public static Long getUserRuleSessionId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        return Long.parseLong((String) session.getAttribute("ruleId"));
    }

    public static Long getUserCompanySessionId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        return Long.parseLong((String) session.getAttribute("companyId"));
    }

    public static String getFormatedStringDate(Date date) {
        return getFormatedStringDate(date, "yyyy-MM-dd");
    }

    public static String getFormatedStringDate(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        if (date != null) {
            return formatter.format(date);
        } else {
            return "";
        }
        
    }    
    
}
