package org.ftd.mytask.web.cmds;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ftd.educational.mytask.persistence.daos.StakeholderGroupDAO;
import org.ftd.educational.mytask.persistence.entities.StakeholderGroup;
import org.ftd.mytask.web.mvc.abstracts.MVC;
import org.ftd.mytask.web.mvc.interfaces.ICmd;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 2.0.0 - 2018-06-22
 *
 */
public class StakeholderGroupCmd extends MVC implements ICmd {

    private final String EntityTitle = "Grupo de Stakeholder";
    
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

        final String[] headers = {"Nome", "Ações"};
        request.setAttribute("headers", headers);
        request.setAttribute("title", EntityTitle);
        request.setAttribute("datasource", this.findAll(request));

        return "WEB-INF/views/BaseListView.jsp";
    }

    @Override
    public String buildAddNewModel(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("title", "Criando " + EntityTitle);

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
        request.setAttribute("nameFieldPlaceHolder", "Digite o nome do tipo");
        request.setAttribute("nameFieldType", "input");
        request.setAttribute("nameFieldMaxSize", "45");
        request.setAttribute("nameFieldRows", "1");
           
        return "WEB-INF/views/IdNameCreateView.jsp";
    }

    @Override
    public String buildUpdateModel(HttpServletRequest request, HttpServletResponse response) {
        String id = MVC.readParameter(request, "id");

        request.setAttribute("title", "Editando " + EntityTitle);

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
        StakeholderGroup o = this.find(Long.parseLong(id));
        request.setAttribute("o", o);

        /* HTML FORM DEFINITIONS...*/
        request.setAttribute("nameFieldLabel", "Nome");
        request.setAttribute("nameFieldId", "inputName");
        request.setAttribute("nameFieldValue", o.getName());
        request.setAttribute("nameFieldRequired", "required");
        request.setAttribute("nameFieldPlaceHolder", "Digite o nome do tipo");
        request.setAttribute("nameFieldType", "input");
        request.setAttribute("nameFieldMaxSize", "45");
        request.setAttribute("nameFieldRows", "1");
        
        return "WEB-INF/views/IdNameUpdateView.jsp";
    }

    @Override
    public String buildViewModel(HttpServletRequest request, HttpServletResponse response) {
        String id = MVC.readParameter(request, "id");

        request.setAttribute("title", EntityTitle);

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
        StakeholderGroup o = this.find(Long.parseLong(id));
        request.setAttribute("o", o);

        /* HTML FORM DEFINITIONS...*/
        StringBuilder description = new StringBuilder();
        description.append("Id: ");
        description.append(o.getId());
        description.append("\n");
        description.append("Nome: ");
        description.append(o.getName());
        
        request.setAttribute("descriptionFieldLabel", "Informações");
        request.setAttribute("descriptionFieldValue", description.toString());
        request.setAttribute("descriptionFieldId", "inputDescription");
        request.setAttribute("descriptionFieldPlaceHolder", "Digite a fase");
        request.setAttribute("descriptionFieldType", "textArea");
        request.setAttribute("descriptionFieldMaxSize", "255");
        request.setAttribute("descriptionFieldRows", "5");

        return "WEB-INF/views/BaseReadDeleteView.jsp";
    }

    @Override
    public String doAddNew(HttpServletRequest request, HttpServletResponse response) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        StakeholderGroupDAO dao = new StakeholderGroupDAO(factory);
        try {
            StakeholderGroup o = new StakeholderGroup();
            o.setCompanyId(MVC.getUserCompanySessionId(request));
            o.setName(MVC.readParameter(request, "inputName"));
            dao.create(o);

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
            StakeholderGroupDAO dao = new StakeholderGroupDAO(factory);
            StakeholderGroup o = dao.findStakeholderGroup(id);
            
            o.setName(MVC.readParameter(request, "inputName"));
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
            StakeholderGroupDAO dao = new StakeholderGroupDAO(factory);
            dao.destroy(id);

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

    private List<StakeholderGroup> findAll(HttpServletRequest request) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        StakeholderGroupDAO dao = new StakeholderGroupDAO(factory);

        return dao.findAllByCompany(MVC.getUserCompanySessionId(request));
    }

    private StakeholderGroup find(Long id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        StakeholderGroupDAO dao = new StakeholderGroupDAO(factory);

        return dao.find(id);
    }

}
