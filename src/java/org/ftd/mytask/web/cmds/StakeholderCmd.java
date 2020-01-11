package org.ftd.mytask.web.cmds;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ftd.educational.mytask.persistence.entities.Stakeholder;
import org.ftd.educational.mytask.persistence.daos.StakeholderDAO;
import org.ftd.educational.mytask.persistence.daos.StakeholderGroupDAO;
import org.ftd.educational.mytask.persistence.entities.StakeholderGroup;
import org.ftd.mytask.web.adapters.ButtonDefinition;
import org.ftd.mytask.web.adapters.FieldDefinition;
import org.ftd.mytask.web.adapters.FormDefinition;
import org.ftd.mytask.web.adapters.IdNameAdapter;
import org.ftd.mytask.web.adapters.IdNameGroupAdapter;
import org.ftd.mytask.web.mvc.abstracts.MVC;
import org.ftd.mytask.web.mvc.interfaces.ICmd;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 2.0.0 - 2018-06-22
 *
 */
public class StakeholderCmd extends MVC implements ICmd {

    private final String EntityTitle = "Stakeholder";

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

        final String[] headers = {"Nome", "Grupo", "Ações"};
        request.setAttribute("headers", headers);
        request.setAttribute("showGroup", Boolean.TRUE);
        request.setAttribute("title", EntityTitle);
        request.setAttribute("datasource", this.findAll(request));

        return "WEB-INF/views/SimpleListView.jsp";
    }

    @Override
    public String buildAddNewModel(HttpServletRequest request, HttpServletResponse response) {
        final String formTitle = "Novo " + EntityTitle;

        final String urlToGo = MVC.URL_MVC_SERVICE
                + "?" + MVC.PARAMETER_NAME_CMD
                + "=" + this.getClass().getSimpleName()
                + "&" + MVC.PARAMETER_NAME_ACTION
                + "=" + MVC.MVC_ACTION_ADDNEW;

        final String urlToGoBack = MVC.URL_MVC_SERVICE
                + "?" + MVC.PARAMETER_NAME_CMD
                + "=" + this.getClass().getSimpleName()
                + "&" + MVC.PARAMETER_NAME_ACTION
                + "=" + MVC.MVC_ACTION_BUILD_GRID_MODEL;

        List<IdNameAdapter> groups = this.findAllGroup(request);

        /*  HTML FIELD DEFINITIONS ... */
        final List<FieldDefinition> fields = new ArrayList<>();
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_INPUT, "nameInput", "Nome", "", null, "Digite um nome!", "required", "", 45, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_COMBO, "groupIdInput", "Grupo", "", groups, "Selecione um grupo!", "required", "", 0, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_EMAIL, "emailInput", "E-mail", "", null, "Digite um E-mail!", "", "", 45, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_TEXT, "observationInput", "Observações", "", null, "Digite suas observações", "", "", 255, 5));

        /*  HTML BUTTON DEFINITIONS ... */
        final List<ButtonDefinition> buttons = new ArrayList<>();
        buttons.add(new ButtonDefinition(ButtonDefinition.TYPE_SUBMIT, "btnSubmit", "Salvar", "btn btn-outline-primary", ""));
        buttons.add(new ButtonDefinition(ButtonDefinition.TYPE_SIMPLE, "btnCancel", "Cancelar", "btn btn-outline-success", urlToGoBack));
        
        /* HTML FORM DEFINITIONS ...*/
        final FormDefinition form = new FormDefinition(formTitle, "formCreate", "POST", urlToGo, fields, buttons);

        request.setAttribute("oForm", form);

        return "WEB-INF/views/__OneColumnFormEngine.jsp";
    }

    @Override
    public String buildUpdateModel(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(MVC.readParameter(request, "id"));
        Stakeholder o = this.find(id);

        final String formTitle = "Editando " + EntityTitle;

        final String urlToGo = MVC.URL_MVC_SERVICE
                + "?" + MVC.PARAMETER_NAME_CMD
                + "=" + this.getClass().getSimpleName()
                + "&" + MVC.PARAMETER_NAME_ACTION
                + "=" + MVC.MVC_ACTION_UPDATE
                + "&" + MVC.PARAMETER_NAME_ID
                + "=" + id;

        final String urlToGoBack = MVC.URL_MVC_SERVICE
                + "?" + MVC.PARAMETER_NAME_CMD
                + "=" + this.getClass().getSimpleName()
                + "&" + MVC.PARAMETER_NAME_ACTION
                + "=" + MVC.MVC_ACTION_BUILD_GRID_MODEL;

        List<IdNameAdapter> groups = this.findAllGroup(request);

        /*  HTML FIELD DEFINITIONS ... */
        final List<FieldDefinition> fields = new ArrayList<>();
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_INPUT, "nameInput", "Nome", o.getName(), null, "Digite um nome!", "required", "", 45, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_COMBO, "groupIdInput", "Grupo", String.valueOf(o.getStakeholderGroupId()), groups, "Selecione um grupo!", "required", "", 0, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_EMAIL, "emailInput", "E-mail", o.getEmail(), null, "Digite um E-mail!", "", "", 45, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_TEXT, "observationInput", "Observações", o.getObservation(), null, "Digite suas observações", "", "", 255, 5));

        final List<ButtonDefinition> buttons = new ArrayList<>();
        buttons.add(new ButtonDefinition(ButtonDefinition.TYPE_SUBMIT, "btnSubmit", "Salvar", "btn btn-outline-primary", ""));
        buttons.add(new ButtonDefinition(ButtonDefinition.TYPE_SIMPLE, "btnCancel", "Cancelar", "btn btn-outline-success", urlToGoBack));        
        
        /* HTML FORM DEFINITIONS ...*/
        final FormDefinition form = new FormDefinition(formTitle, "formUpdate", "POST", urlToGo, fields, buttons);

        request.setAttribute("oForm", form);

        return "WEB-INF/views/__OneColumnFormEngine.jsp";
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
        Stakeholder o = this.find(Long.parseLong(id));
        request.setAttribute("o", o);

        /* HTML FORM DEFINITIONS...*/
        StringBuilder description = new StringBuilder();
        description.append("Id: ");
        description.append(o.getId());
        description.append("\n");
        description.append("Nome: ");
        description.append(o.getName());
        description.append("\n");
        description.append("Grupo: ");
        description.append(this.findGroup(o.getStakeholderGroupId()).getName());        
        description.append("\n");
        description.append("E-mail: ");
        description.append(o.getEmail());
        description.append("\n");
        description.append("Observações: \n");
        description.append(o.getObservation());

        request.setAttribute("descriptionFieldLabel", "Informações");
        request.setAttribute("descriptionFieldValue", description.toString());
        request.setAttribute("descriptionFieldId", "inputDescription");
        request.setAttribute("descriptionFieldPlaceHolder", "Digite a fase");
        request.setAttribute("descriptionFieldType", "textArea");
        request.setAttribute("descriptionFieldMaxSize", "255");
        request.setAttribute("descriptionFieldRows", "12");

        return "WEB-INF/views/BaseReadDeleteView.jsp";
    }

    @Override
    public String doAddNew(HttpServletRequest request, HttpServletResponse response) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        StakeholderDAO dao = new StakeholderDAO(factory);
        try {
            Stakeholder o = new Stakeholder();
            o.setCompanyId(MVC.getUserCompanySessionId(request));
            o.setName(MVC.readParameter(request, "nameInput"));
            o.setStakeholderGroupId(Long.parseLong(MVC.readParameter(request, "groupIdInput")));
            o.setEmail(MVC.readParameter(request, "emailInput"));
            o.setObservation(MVC.readParameter(request, "observationInput"));
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
            StakeholderDAO dao = new StakeholderDAO(factory);
            Stakeholder o = dao.findStakeholder(id);

            o.setName(MVC.readParameter(request, "nameInput"));
            o.setStakeholderGroupId(Long.parseLong(MVC.readParameter(request, "groupIdInput")));
            o.setEmail(MVC.readParameter(request, "emailInput"));
            o.setObservation(MVC.readParameter(request, "observationInput"));

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
            StakeholderDAO dao = new StakeholderDAO(factory);
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

    private List<IdNameAdapter> findAllGroup(HttpServletRequest request) {
        List<IdNameAdapter> datasource = new ArrayList<>();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        StakeholderGroupDAO DAO = new StakeholderGroupDAO(factory);
        List<StakeholderGroup> groups = DAO.findAllByCompany(MVC.getUserCompanySessionId(request));

        groups.forEach((o) -> {
            datasource.add(new IdNameAdapter(o.getId(), o.getName()));
        });

        return datasource;
    }

    private List<IdNameGroupAdapter> findAll(HttpServletRequest request) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        StakeholderDAO DAO = new StakeholderDAO(factory);
        List<Stakeholder> stakeholders = DAO.findAllByCompany(MVC.getUserCompanySessionId(request));
        List<IdNameGroupAdapter> datasource = new ArrayList<>();
        StakeholderGroupDAO groupDAO = new StakeholderGroupDAO(factory);
        stakeholders.forEach((o) -> {
            StakeholderGroup group = groupDAO.find(o.getStakeholderGroupId());
            datasource.add(new IdNameGroupAdapter(o.getId(), o.getName(), group.getName()));
        });

        return datasource;
    }

    private Stakeholder find(Long id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        StakeholderDAO dao = new StakeholderDAO(factory);

        return dao.find(id);
    }

    private StakeholderGroup findGroup(Long id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        StakeholderGroupDAO dao = new StakeholderGroupDAO(factory);

        return dao.find(id);
    }    
    
}
