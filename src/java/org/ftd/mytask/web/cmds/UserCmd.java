package org.ftd.mytask.web.cmds;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ftd.educational.mytask.persistence.daos.CompanyDAO;
import org.ftd.educational.mytask.persistence.daos.ConfigKeyDAO;
import org.ftd.educational.mytask.persistence.daos.PasswdDAO;
import org.ftd.educational.mytask.persistence.daos.RuleDAO;
import org.ftd.educational.mytask.persistence.daos.UserDAO;
import org.ftd.educational.mytask.persistence.daos.UserStatusDAO;
import org.ftd.educational.mytask.persistence.entities.Company;
import org.ftd.educational.mytask.persistence.entities.ConfigKey;
import org.ftd.educational.mytask.persistence.entities.Passwd;
import org.ftd.educational.mytask.persistence.entities.Rule;
import org.ftd.educational.mytask.persistence.entities.User;
import org.ftd.educational.mytask.persistence.entities.UserStatus;
import org.ftd.mytask.web.mvc.abstracts.MVC;
import org.ftd.mytask.web.mvc.interfaces.ICmd;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 2.0.0 - 2018-06-20
 *
 */
public class UserCmd extends MVC implements ICmd {

    @Override
    public String buildGridModel(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("urlToCreate",
                MVC.URL_MVC_SERVICE
                + "?" + MVC.PARAMETER_NAME_CMD
                + "=" + this.getClass().getSimpleName()
                + "&" + MVC.PARAMETER_NAME_ACTION
                + "=" + MVC.MVC_ACTION_BUILD_ADD_MODEL
        );

        request.setAttribute("urlToUpdate",
                MVC.URL_MVC_SERVICE
                + "?" + MVC.PARAMETER_NAME_CMD
                + "=" + this.getClass().getSimpleName()
                + "&" + MVC.PARAMETER_NAME_ACTION
                + "=" + MVC.MVC_ACTION_BUILD_UPD_MODEL
                + "&" + MVC.PARAMETER_NAME_ID
                + "="
        );

        request.setAttribute("urlToView",
                MVC.URL_MVC_SERVICE
                + "?" + MVC.PARAMETER_NAME_CMD
                + "=" + this.getClass().getSimpleName()
                + "&" + MVC.PARAMETER_NAME_ACTION
                + "=" + MVC.MVC_ACTION_BUILD_VIEW_MODEL
                + "&" + MVC.PARAMETER_NAME_ID
                + "="
        );

        final String[] headers = {"Usuário", "Ações"};

        request.setAttribute("headers", headers);
        request.setAttribute("title", "Usuários");
        request.setAttribute("datasource", this.findAllUser(request));

        return "WEB-INF/views/SimpleListView.jsp";
    }

    @Override
    public String buildAddNewModel(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("title", "Criando Usuário");

        request.setAttribute("urlToGo",
                MVC.URL_MVC_SERVICE
                + "?" + MVC.PARAMETER_NAME_CMD
                + "=" + this.getClass().getSimpleName()
                + "&" + MVC.PARAMETER_NAME_ACTION
                + "=" + MVC.MVC_ACTION_ADDNEW
        );

        request.setAttribute("urlToGoBack",
                MVC.URL_MVC_SERVICE
                + "?" + MVC.PARAMETER_NAME_CMD
                + "=" + this.getClass().getSimpleName()
                + "&" + MVC.PARAMETER_NAME_ACTION
                + "=" + MVC.MVC_ACTION_BUILD_GRID_MODEL
        );

        /* HTML FORM DEFINITIONS ...*/
        request.setAttribute("nameFieldLabel", "Nome");
        request.setAttribute("nameFieldId", "inputName");
        request.setAttribute("nameFieldRequired", "required");
        request.setAttribute("nameFieldPlaceHolder", "Digite o nome do usuário");
        request.setAttribute("nameFieldType", "input");
        request.setAttribute("nameFieldMaxSize", "45");
        request.setAttribute("nameFieldRows", "1");

        request.setAttribute("emailFieldLabel", "E-mail");
        request.setAttribute("emailFieldId", "inputEmail");
        request.setAttribute("emailFieldRequired", "required");
        request.setAttribute("emailFieldPlaceHolder", "Digite um E-mail");
        request.setAttribute("emailFieldType", "email");
        request.setAttribute("emailFieldMaxSize", "45");
        request.setAttribute("emailFieldRows", "1");

        request.setAttribute("ruleFieldLabel", "Papel");
        request.setAttribute("ruleFieldId", "inputRule");
        request.setAttribute("ruleFieldRequired", "required");
        request.setAttribute("ruleFieldPlaceHolder", "Selecione um papel");
        request.setAttribute("ruleFieldType", "combo");
        request.setAttribute("ruleFieldMaxSize", "20");
        request.setAttribute("ruleFieldRows", "1");

        request.setAttribute("statusFieldLabel", "Status");
        request.setAttribute("statusFieldId", "inputStatus");
        request.setAttribute("statusFieldRequired", "required");
        request.setAttribute("statusFieldPlaceHolder", "Selecione um status");
        request.setAttribute("statusFieldType", "combo");
        request.setAttribute("statusFieldMaxSize", "20");
        request.setAttribute("statusFieldRows", "1");

        request.setAttribute("rules", this.findAllRule(request));
        request.setAttribute("status", this.findAllUserStatus(request));

        return "WEB-INF/views/UserCreateView.jsp";
    }

    @Override
    public String buildUpdateModel(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("title", "Editando Usuário");
        String id = MVC.readParameter(request, "id");

        request.setAttribute("urlToGo",
                MVC.URL_MVC_SERVICE
                + "?" + MVC.PARAMETER_NAME_CMD
                + "=" + this.getClass().getSimpleName()
                + "&" + MVC.PARAMETER_NAME_ACTION
                + "=" + MVC.MVC_ACTION_UPDATE
                + "&" + MVC.PARAMETER_NAME_ID
                + "=" + id
        );

        request.setAttribute("urlToGoBack",
                MVC.URL_MVC_SERVICE
                + "?" + MVC.PARAMETER_NAME_CMD
                + "=" + this.getClass().getSimpleName()
                + "&" + MVC.PARAMETER_NAME_ACTION
                + "=" + MVC.MVC_ACTION_BUILD_GRID_MODEL
        );

        /* FIND DE ENTITY... */
        User o = this.findUser(Long.parseLong(id));
        request.setAttribute("entity", o);

        /* HTML FORM DEFINITIONS...*/
        request.setAttribute("nameFieldLabel", "Nome");
        request.setAttribute("nameFieldId", "inputName");
        request.setAttribute("nameFieldValue", o.getName());
        request.setAttribute("nameFieldRequired", "required");
        request.setAttribute("nameFieldPlaceHolder", "Digite um nome do usuário");
        request.setAttribute("nameFieldType", "input");
        request.setAttribute("nameFieldMaxSize", "45");
        request.setAttribute("nameFieldRows", "1");

        request.setAttribute("emailFieldLabel", "E-mail");
        request.setAttribute("emailFieldId", "inputEmail");
        request.setAttribute("emailFieldValue", o.getEmail());
        request.setAttribute("emailFieldRequired", "required");
        request.setAttribute("emailFieldPlaceHolder", "Digite um E-mail");
        request.setAttribute("emailFieldType", "email");
        request.setAttribute("emailFieldMaxSize", "45");
        request.setAttribute("emailFieldRows", "1");

        request.setAttribute("ruleFieldLabel", "Papel");
        request.setAttribute("ruleFieldId", "inputRule");
        request.setAttribute("ruleFieldValue", o.getRuleId());
        request.setAttribute("ruleFieldRequired", "required");
        request.setAttribute("ruleFieldPlaceHolder", "Selecione um papel");
        request.setAttribute("ruleFieldType", "combo");
        request.setAttribute("ruleFieldMaxSize", "20");
        request.setAttribute("ruleFieldRows", "1");

        request.setAttribute("statusFieldLabel", "Status");
        request.setAttribute("statusFieldId", "inputStatus");
        request.setAttribute("statusFieldValue", o.getUserStatusId());
        request.setAttribute("statusFieldRequired", "required");
        request.setAttribute("statusFieldPlaceHolder", "Selecione um status");
        request.setAttribute("statusFieldType", "combo");
        request.setAttribute("statusFieldMaxSize", "20");
        request.setAttribute("statusFieldRows", "1");

        request.setAttribute("rules", this.findAllRule(request));
        request.setAttribute("status", this.findAllUserStatus(request));

        return "WEB-INF/views/UserUpdateView.jsp";
    }

    @Override
    public String buildViewModel(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("title", "Usuário");
        String id = MVC.readParameter(request, "id");

        request.setAttribute("urlToGo",
                MVC.URL_MVC_SERVICE
                + "?" + MVC.PARAMETER_NAME_CMD
                + "=" + this.getClass().getSimpleName()
                + "&" + MVC.PARAMETER_NAME_ACTION
                + "=" + MVC.MVC_ACTION_DELETE
                + "&" + MVC.PARAMETER_NAME_ID
                + "=" + id
        );

        request.setAttribute("urlToGoBack",
                MVC.URL_MVC_SERVICE
                + "?" + MVC.PARAMETER_NAME_CMD
                + "=" + this.getClass().getSimpleName()
                + "&" + MVC.PARAMETER_NAME_ACTION
                + "=" + MVC.MVC_ACTION_BUILD_GRID_MODEL
        );

        /* FIND DE ENTITY... */
        User o = this.findUser(Long.parseLong(id));
        request.setAttribute("o", o);

        /* HTML FORM DEFINITIONS...*/
        StringBuilder description = new StringBuilder();
        description.append("Id: ");
        description.append(o.getId());
        description.append("\n");
        description.append("Nome: ");
        description.append(o.getName());
        description.append("\n");
        description.append("Empresa: ");
        description.append(o.getId());
        description.append(" - ");
        description.append(this.findCompany(o.getCompanyId()).getName());
        description.append("\n");
        description.append("E-mail: ");
        description.append(o.getEmail());
        description.append("\n");
        description.append("Papel: ");
        description.append(this.findRule(o.getRuleId()).getName());
        description.append("\n");
        description.append("Status: ");
        description.append(this.findStatus(o.getUserStatusId()).getName());

        request.setAttribute("descriptionFieldLabel", "Informações");
        request.setAttribute("descriptionFieldValue", description.toString());
        request.setAttribute("descriptionFieldId", "inputDescription");
        request.setAttribute("descriptionFieldPlaceHolder", "Digite o valor da chave");
        request.setAttribute("descriptionFieldType", "textArea");
        request.setAttribute("descriptionFieldMaxSize", "255");
        request.setAttribute("descriptionFieldRows", "8");

        return "WEB-INF/views/BaseReadDeleteView.jsp";
    }

    @Override
    public String doAddNew(HttpServletRequest request, HttpServletResponse response) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);

        try {
            UserDAO dao = new UserDAO(factory);
            User o = new User();
            o.setCompanyId(MVC.getUserCompanySessionId(request));
            o.setName(MVC.readParameter(request, "inputName"));
            o.setEmail(MVC.readParameter(request, "inputEmail"));
            o.setRuleId(Long.parseLong(MVC.readParameter(request, "inputRule")));
            o.setUserStatusId(Long.parseLong(MVC.readParameter(request, "inputStatus")));
            dao.create(o);

            /* BUSCANDO A SENHA PADRÃO */
            ConfigKeyDAO cfgDao = new ConfigKeyDAO(factory);
            ConfigKey key = cfgDao.findConfigKey(MVC.getUserCompanySessionId(request), "passwd.default");

            /* CADASTRANDO A SENHA PADRÃO */
            PasswdDAO passDao = new PasswdDAO(factory);
            Passwd passwd = new Passwd();
            passwd.setUserId(o.getId());
            passwd.setName(key.getKeyValue());
            passwd.setPasswdStatusId(1L);
            passwd.setCreatedOn(new Date());
            passDao.create(passwd);

            request.setAttribute("msg", "Id=" + o.getId() + " " + MVC.MSG_CRUD_CREATE_SUCESS);

            return MVC.URL_MVC_SERVICE
                    + "?" + MVC.PARAMETER_NAME_CMD
                    + "=" + this.getClass().getSimpleName()
                    + "&" + MVC.PARAMETER_NAME_ACTION
                    + "=" + MVC.MVC_ACTION_BUILD_GRID_MODEL;

        } catch (Exception e) {

            request.setAttribute("msg", MVC.MSG_CRUD_CREATE_FAILURE);

            return MVC.URL_MVC_SERVICE
                    + "?" + MVC.PARAMETER_NAME_CMD
                    + "=" + this.getClass().getSimpleName()
                    + "&" + MVC.PARAMETER_NAME_ACTION
                    + "=" + MVC.MVC_ACTION_BUILD_ADD_MODEL;
        }

    }

    @Override
    public String doUpdate(HttpServletRequest request, HttpServletResponse response) {

        Long id = Long.parseLong(MVC.readParameter(request, "id"));
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
            UserDAO dao = new UserDAO(factory);
            User o = dao.find(id);
            o.setName(MVC.readParameter(request, "inputName"));
            o.setEmail(MVC.readParameter(request, "inputEmail"));
            o.setRuleId(Long.parseLong(MVC.readParameter(request, "inputRule")));
            o.setUserStatusId(Long.parseLong(MVC.readParameter(request, "inputStatus")));
            dao.edit(o);

            request.setAttribute("msg", "Id=" + o.getId() + " " + MVC.MSG_CRUD_UPDATE_SUCESS);

            return MVC.URL_MVC_SERVICE
                    + "?" + MVC.PARAMETER_NAME_CMD
                    + "=" + this.getClass().getSimpleName()
                    + "&" + MVC.PARAMETER_NAME_ACTION
                    + "=" + MVC.MVC_ACTION_BUILD_GRID_MODEL;

        } catch (Exception e) {

            request.setAttribute("msg", MVC.MSG_CRUD_UPDATE_FAILURE);

            return MVC.URL_MVC_SERVICE
                    + "?" + MVC.PARAMETER_NAME_CMD
                    + "=" + this.getClass().getSimpleName()
                    + "&" + MVC.PARAMETER_NAME_ACTION
                    + "=" + MVC.MVC_ACTION_BUILD_UPD_MODEL
                    + "&" + MVC.PARAMETER_NAME_ID
                    + "=" + id;

        }
    }

    @Override
    public String doRemove(HttpServletRequest request, HttpServletResponse response) {

        Long id = Long.parseLong(MVC.readParameter(request, "id"));
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
            /* APAGANDO TODAS AS SENHAS DO USUÁRIO */
            PasswdDAO passwdDAO = new PasswdDAO(factory);
            List<Passwd> userPasswds = passwdDAO.findAllByUser(id);
            for (Passwd o : userPasswds) {
                passwdDAO.destroy(o.getId());
            }
            /* APAGANDO O USUÁRIO */
            UserDAO userDAO = new UserDAO(factory);
            userDAO.destroy(id);

            request.setAttribute("msg", "Id=" + id + " " + MVC.MSG_CRUD_DELETE_SUCESS);

            return MVC.URL_MVC_SERVICE
                    + "?" + MVC.PARAMETER_NAME_CMD
                    + "=" + this.getClass().getSimpleName()
                    + "&" + MVC.PARAMETER_NAME_ACTION
                    + "=" + MVC.MVC_ACTION_BUILD_GRID_MODEL;

        } catch (Exception e) {

            request.setAttribute("msg", MVC.MSG_CRUD_DELETE_FAILURE);

            return MVC.URL_MVC_SERVICE
                    + "?" + MVC.PARAMETER_NAME_CMD
                    + "=" + this.getClass().getSimpleName()
                    + "&" + MVC.PARAMETER_NAME_ACTION
                    + "=" + MVC.MVC_ACTION_BUILD_VIEW_MODEL
                    + "&" + MVC.PARAMETER_NAME_ID
                    + "=" + id;
        }

    }

    private List<User> findAllUser(HttpServletRequest request) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        UserDAO dao = new UserDAO(factory);

        return dao.findAllByCompany(MVC.getUserCompanySessionId(request));
    }

    private User findUser(Long id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        UserDAO dao = new UserDAO(factory);

        return dao.find(id);
    }

    private Rule findRule(Long id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        RuleDAO dao = new RuleDAO(factory);

        return dao.find(id);
    }

    private Company findCompany(Long id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        CompanyDAO dao = new CompanyDAO(factory);

        return dao.find(id);
    }    
    
    private UserStatus findStatus(Long id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        UserStatusDAO dao = new UserStatusDAO(factory);

        return dao.find(id);
    }

    private List<UserStatus> findAllUserStatus(HttpServletRequest request) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        UserStatusDAO dao = new UserStatusDAO(factory);

        return dao.findAll();
    }

    private List<Rule> findAllRule(HttpServletRequest request) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        RuleDAO dao = new RuleDAO(factory);

        return dao.findAll();
    }

}
