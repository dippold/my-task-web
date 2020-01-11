package org.ftd.mytask.web.cmds;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ftd.educational.mytask.persistence.daos.BacklogItemDAO;
import org.ftd.educational.mytask.persistence.daos.CompanyAreaDAO;
import org.ftd.educational.mytask.persistence.daos.ProjectStatusDAO;
import org.ftd.educational.mytask.persistence.entities.Stakeholder;
import org.ftd.educational.mytask.persistence.daos.StakeholderDAO;
import org.ftd.educational.mytask.persistence.entities.BacklogItem;
import org.ftd.educational.mytask.persistence.entities.CompanyArea;
import org.ftd.educational.mytask.persistence.entities.ProjectStatus;
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
public class BacklogItemCmd extends MVC implements ICmd {

    private final String EntityTitle = "Item de Backlog";

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

        final String[] headers = {"Nome", "Status", "Ações"};
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

        List<IdNameAdapter> areas = this.findAreas(request);
        List<IdNameAdapter> groups = this.findStakeholders(request);
        List<IdNameAdapter> status = this.findAllStatus(request);

        /*  HTML FIELD DEFINITIONS ... */
        final List<FieldDefinition> fields = new ArrayList<>();
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_INPUT, "nameInput", "Nome", "", null, "Digite um nome resumido.", "required", "", 45, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_COMBO, "companyAreaIdInput", "Área", "", areas, "Qual a área da empresa?", "required", "", 0, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_COMBO, "stakeholderIdInput", "Stakeholder", "", groups, "Quem é o patrocinador?", "required", "", 0, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_TEXT, "stakeholderConcernInput", "Preocupações dos Stakeholders", "", null, "Digite observações dos stakeholders.", "", "", 255, 5));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_TEXT, "readyConceptInput", "Conceito de Pronto", "", null, "Digite os conceitos de pronto ou critérios de aceite.)", "", "", 255, 5));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_COMBO, "statusIdInput", "Status", "", status, "Selecione um status.", "required", "", 0, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_TEXT, "descriptionInput", "Descrição", "", null, "Digite a descrição do pacote de trabalho.", "", "", 255, 5));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_TEXT, "estimatedResourcesInput", "Recursos Estimados", "", null, "Digite os tipos de profissionais, equipamentos e materiais de consumo necessários!", "", "", 255, 5));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_NUMBER, "estimatedWorkInput", "Trabalho Estimado (Hr)", "", null, "", "", "", 10000, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_NUMBER, "estimatedDurationInput", "Duração Estimada (Dias)", "", null, "", "", "", 1000, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_NUMBER, "estimatedCostInput", "Custo Estimado (R$)", "", null, "", "", "", 1000000, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_DATE, "requestDateInput", "Data Solicitação", "", null, "", "", "", 0, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_DATE, "wishDateInput", "Data Desejada", "", null, "", "", "", 0, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_DATE, "plannedDateInput", "Data Planejada", "", null, "", "", "", 0, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_DATE, "dateDeliveredInput", "Data Entregue", "", null, "", "", "", 0, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_TEXT, "observationInput", "Observações de Progresso", "", null, "Digite observações de progresso.", "", "", 255, 5));

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
        BacklogItem o = this.find(id);

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

        List<IdNameAdapter> areas = this.findAreas(request);
        List<IdNameAdapter> groups = this.findStakeholders(request);
        List<IdNameAdapter> status = this.findAllStatus(request);

        /*  HTML FIELD DEFINITIONS ... */
        final List<FieldDefinition> fields = new ArrayList<>();

        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_INPUT, "nameInput", "Nome", o.getName(), null, "Digite um nome resumido", "required", "", 45, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_COMBO, "companyAreaIdInput", "Área", String.valueOf(o.getCompanyAreaId()), areas, "Qual a área da empresa?", "required", "", 0, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_COMBO, "stakeholderIdInput", "Stakeholder", String.valueOf(o.getStakeholderId()), groups, "Quem é o patrocinador?", "required", "", 0, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_TEXT, "stakeholderConcernInput", "Preocupações dos Stakeholders", o.getStakeholderConcern(), null, "Digite observações dos stakeholders", "", "", 255, 5));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_TEXT, "readyConceptInput", "Conceito de Pronto", o.getReadyConcept(), null, "Digite os conceitos de pronto (critérios de aceite)", "", "", 255, 5));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_COMBO, "statusIdInput", "Status", String.valueOf(o.getStatusId()), status, "Selecione um Status!", "required", "", 0, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_TEXT, "descriptionInput", "Descrição", o.getDescription(), null, "Digite a descrição do pacote de trabalho", "", "", 255, 5));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_TEXT, "estimatedResourcesInput", "Recursos Estimados", o.getEstimatedResources(), null, "Digite os tipos de profissionais, equipamentos e materiais de consumo necessários!", "", "", 255, 5));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_NUMBER, "estimatedWorkInput", "Trabalho Estimado (Hr)", String.valueOf(o.getEstimatedWork()), null, "", "", "", 10000, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_NUMBER, "estimatedDurationInput", "Duração Estimada (Dias)", String.valueOf(o.getEstimatedDuration()), null, "", "", "", 1000, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_NUMBER, "estimatedCostInput", "Custo Estimado (R$)", String.valueOf(o.getEstimatedCost()), null, "", "", "", 1000000, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_DATE, "requestDateInput", "Data Solicitação", MVC.getFormatedStringDate(o.getRequestDate()), null, "", "", "", 0, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_DATE, "wishDateInput", "Data Desejada", MVC.getFormatedStringDate(o.getWishDate()), null, "", "", "", 0, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_DATE, "plannedDateInput", "Data Planejada", MVC.getFormatedStringDate(o.getPlannedDate()), null, "", "", "", 0, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_DATE, "dateDeliveredInput", "Data Entregue", MVC.getFormatedStringDate(o.getDateDelivered()), null, "", "", "", 0, 0));
        fields.add(new FieldDefinition(6, FieldDefinition.TYPE_TEXT, "observationInput", "Observações de Progresso", o.getObservation(), null, "Digite observações de progresso", "", "", 255, 5));

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
        BacklogItem o = this.find(Long.parseLong(id));
        request.setAttribute("o", o);

        /* HTML FORM DEFINITIONS...*/
        StringBuilder description = new StringBuilder();
        description.append("Id: ");
        description.append(o.getId());

        description.append("\nNome: ");
        description.append(o.getName());

        description.append("\nDescrição: \n");
        description.append(o.getDescription());

        description.append("\nData Requisição: ");
        description.append(MVC.getFormatedStringDate(o.getRequestDate(), "dd-MM-yyyy"));

        description.append("\nÁrea da empresa: ");
        description.append(this.findArea(o.getCompanyAreaId()).getName());

        description.append("\nPatrocinador: ");
        description.append(this.findStakeholder(o.getStakeholderId()).getName());

        if (o.getStakeholderConcern() != null && !o.getStakeholderConcern().equals("")) {
            description.append("\nPreocupações do Stakeholder: \n");
            description.append(o.getStakeholderConcern());
        }

        if (o.getReadyConcept() != null && !o.getReadyConcept().equals("")) {
            description.append("\nConceito de Pronto: \n");
            description.append(o.getReadyConcept());
        }

        description.append("\nStatus: ");
        description.append(this.findStatus(o.getStatusId()).getName());

        if (o.getEstimatedResources()!= null && !o.getEstimatedResources().equals("")) {
            description.append("\nRecursos Estimados: \n");
            description.append(o.getEstimatedResources());
        }        
        
        NumberFormat intFormatter = new DecimalFormat(",##0");
        description.append("\nTrabalho Estimado (Hr): ");
        description.append(intFormatter.format(o.getEstimatedWork()));

        description.append("\nDuração Estimada (Dias): ");
        description.append(intFormatter.format(o.getEstimatedDuration()));

        NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();
        description.append("\nCusto Estimado (R$): ");
        description.append(moneyFormatter.format(o.getEstimatedCost()));        
        
        description.append("\nData Desejo: ");
        description.append(MVC.getFormatedStringDate(o.getWishDate(), "dd-MM-yyyy"));

        description.append("\nData Planejada: ");
        description.append(MVC.getFormatedStringDate(o.getPlannedDate(), "dd-MM-yyyy"));

        description.append("\nData Entrega: ");
        description.append(MVC.getFormatedStringDate(o.getDateDelivered(), "dd-MM-yyyy"));

        if (o.getObservation() != null && !o.getObservation().equals("")) {
            description.append("\nObservações de Progresso: \n");
            description.append(o.getObservation());
        }

        request.setAttribute("descriptionFieldLabel", "Informações");
        request.setAttribute("descriptionFieldValue", description.toString());
        request.setAttribute("descriptionFieldId", "inputDescription");
        request.setAttribute("descriptionFieldPlaceHolder", "");
        request.setAttribute("descriptionFieldType", "textArea");
        request.setAttribute("descriptionFieldMaxSize", "255");
        request.setAttribute("descriptionFieldRows", "18");

        return "WEB-INF/views/BaseReadDeleteView.jsp";
    }

    @Override
    public String doAddNew(HttpServletRequest request, HttpServletResponse response) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        BacklogItemDAO dao = new BacklogItemDAO(factory);
        try {
            BacklogItem o = new BacklogItem();
            o.setCompanyId(MVC.getUserCompanySessionId(request));
            o.setName(MVC.readParameter(request, "nameInput"));
            o.setCompanyAreaId(Long.parseLong(MVC.readParameter(request, "companyAreaIdInput")));
            o.setStakeholderId(Long.parseLong(MVC.readParameter(request, "stakeholderIdInput")));
            o.setStakeholderConcern(MVC.readParameter(request, "stakeholderConcernInput"));
            o.setReadyConcept(MVC.readParameter(request, "readyConceptInput"));
            o.setStatusId(Long.parseLong(MVC.readParameter(request, "statusIdInput")));
            o.setDescription(MVC.readParameter(request, "descriptionInput"));
            o.setEstimatedWork(Integer.parseInt(MVC.readParameter(request, "estimatedWorkInput")));
            o.setEstimatedDuration(Integer.parseInt(MVC.readParameter(request, "estimatedDurationInput")));
            o.setEstimatedCost(Integer.parseInt(MVC.readParameter(request, "estimatedCostInput")));
            o.setEstimatedResources(MVC.readParameter(request, "estimatedResourcesInput"));

            if (!MVC.readParameter(request, "requestDateInput").equals("")) {
                o.setRequestDate(java.sql.Date.valueOf(MVC.readParameter(request, "requestDateInput")));
            }

            if (!MVC.readParameter(request, "wishDateInput").equals("")) {
                o.setWishDate(java.sql.Date.valueOf(MVC.readParameter(request, "wishDateInput")));
            }

            if (!MVC.readParameter(request, "plannedDateInput").equals("")) {
                o.setPlannedDate(java.sql.Date.valueOf(MVC.readParameter(request, "plannedDateInput")));
            }

            if (!MVC.readParameter(request, "dateDeliveredInput").equals("")) {
                o.setDateDelivered(java.sql.Date.valueOf(MVC.readParameter(request, "dateDeliveredInput")));
            }

            o.setObservation(MVC.readParameter(request, "observationInput"));
            dao.create(o);

            request.setAttribute("msg", "Id=" + o.getId() + " " + MVC.MSG_CRUD_CREATE_SUCESS);

            return MVC.URL_MVC_SERVICE
                    + "?" + MVC.PARAMETER_NAME_CMD
                    + "=" + this.getClass().getSimpleName()
                    + "&" + MVC.PARAMETER_NAME_ACTION
                    + "=" + MVC.MVC_ACTION_BUILD_GRID_MODEL;

        } catch (Exception e) {

            request.setAttribute("msg", MVC.MSG_CRUD_CREATE_FAILURE + "\n" + e.getMessage());

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
            BacklogItemDAO dao = new BacklogItemDAO(factory);
            BacklogItem o = dao.find(id);

            o.setName(MVC.readParameter(request, "nameInput"));
            o.setCompanyAreaId(Long.parseLong(MVC.readParameter(request, "companyAreaIdInput")));
            o.setStakeholderId(Long.parseLong(MVC.readParameter(request, "stakeholderIdInput")));
            o.setStakeholderConcern(MVC.readParameter(request, "stakeholderConcernInput"));
            o.setReadyConcept(MVC.readParameter(request, "readyConceptInput"));
            o.setStatusId(Long.parseLong(MVC.readParameter(request, "statusIdInput")));
            o.setDescription(MVC.readParameter(request, "descriptionInput"));
            o.setEstimatedWork(Integer.parseInt(MVC.readParameter(request, "estimatedWorkInput")));
            o.setEstimatedDuration(Integer.parseInt(MVC.readParameter(request, "estimatedDurationInput")));
            o.setEstimatedCost(Integer.parseInt(MVC.readParameter(request, "estimatedCostInput")));
            o.setEstimatedResources(MVC.readParameter(request, "estimatedResourcesInput"));
            
            if (!MVC.readParameter(request, "requestDateInput").equals("")) {
                o.setRequestDate(java.sql.Date.valueOf(MVC.readParameter(request, "requestDateInput")));
            }
            if (!MVC.readParameter(request, "wishDateInput").equals("")) {
                o.setWishDate(java.sql.Date.valueOf(MVC.readParameter(request, "wishDateInput")));
            }
            if (!MVC.readParameter(request, "plannedDateInput").equals("")) {
                o.setPlannedDate(java.sql.Date.valueOf(MVC.readParameter(request, "plannedDateInput")));
            }
            if (!MVC.readParameter(request, "dateDeliveredInput").equals("")) {
                o.setDateDelivered(java.sql.Date.valueOf(MVC.readParameter(request, "dateDeliveredInput")));
            }
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
            BacklogItemDAO dao = new BacklogItemDAO(factory);
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

    private List<IdNameAdapter> findAreas(HttpServletRequest request) {
        List<IdNameAdapter> datasource = new ArrayList<>();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        CompanyAreaDAO DAO = new CompanyAreaDAO(factory);
        List<CompanyArea> companyAreas = DAO.findAllByCompany(MVC.getUserCompanySessionId(request));

        companyAreas.forEach((o) -> {
            datasource.add(new IdNameAdapter(o.getId(), o.getName()));
        });

        return datasource;
    }

    private List<IdNameAdapter> findStakeholders(HttpServletRequest request) {
        List<IdNameAdapter> datasource = new ArrayList<>();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        StakeholderDAO DAO = new StakeholderDAO(factory);
        List<Stakeholder> stakeholders = DAO.findAllByCompany(MVC.getUserCompanySessionId(request));

        stakeholders.forEach((o) -> {
            datasource.add(new IdNameAdapter(o.getId(), o.getName()));
        });

        return datasource;
    }

    private List<IdNameAdapter> findAllStatus(HttpServletRequest request) {
        List<IdNameAdapter> datasource = new ArrayList<>();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        ProjectStatusDAO DAO = new ProjectStatusDAO(factory);
        List<ProjectStatus> groups = DAO.findAllByCompany(MVC.getUserCompanySessionId(request));

        groups.forEach((o) -> {
            datasource.add(new IdNameAdapter(o.getId(), o.getName()));
        });

        return datasource;
    }

    private List<IdNameGroupAdapter> findAll(HttpServletRequest request) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        BacklogItemDAO DAO = new BacklogItemDAO(factory);
        List<BacklogItem> backlogItens = DAO.findAllByCompany(MVC.getUserCompanySessionId(request));
        List<IdNameGroupAdapter> datasource = new ArrayList<>();
        ProjectStatusDAO statusDAO = new ProjectStatusDAO(factory);
        backlogItens.forEach((o) -> {
            ProjectStatus status = statusDAO.find(o.getStatusId());
            datasource.add(new IdNameGroupAdapter(o.getId(), o.getName(), status.getName()));
        });

        return datasource;
    }

    private BacklogItem find(Long id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        BacklogItemDAO dao = new BacklogItemDAO(factory);

        return dao.find(id);
    }

    private Stakeholder findStakeholder(Long id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        StakeholderDAO dao = new StakeholderDAO(factory);

        return dao.find(id);
    }

    private ProjectStatus findStatus(Long id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        ProjectStatusDAO dao = new ProjectStatusDAO(factory);

        return dao.find(id);
    }

    private CompanyArea findArea(Long id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        CompanyAreaDAO dao = new CompanyAreaDAO(factory);

        return dao.find(id);
    }

}
