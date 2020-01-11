package org.ftd.mytask.web.cmds;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.ftd.educational.mytask.persistence.daos.BacklogItemDAO;
import org.ftd.educational.mytask.persistence.daos.ProjectStatusDAO;
import org.ftd.educational.mytask.persistence.entities.ProjectStatus;
import org.ftd.mytask.web.adapters.ButtonDefinition;
import org.ftd.mytask.web.adapters.IdNameCountPercentAdapter;
import org.ftd.mytask.web.adapters.Portlet;
import org.ftd.mytask.web.mvc.abstracts.MVC;
import org.ftd.mytask.web.mvc.interfaces.ICmd;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 1.0.0 - 2018-05-17
 *
 */
public class HomeCmd implements ICmd {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        MVC.setDefaultAttributes(request);
        request.setAttribute("title", "My Task - Menu Principal");
        
        /*  BACKLOG ITEM PORTLET MODEL ... */
        List<Object> backlogsPerStatus = this.countBacklogPerStatus(request);
        
        ButtonDefinition backlogPortletButton = new ButtonDefinition(ButtonDefinition.TYPE_SIMPLE,
                "btnBacklog",
                "Administrar",
                "btn btn-lg btn-block btn-primary", 
                "mvc?class=BacklogItemCmd&do=0");
        
        String[] backlogPortletHeaders = {"Status","Qt","%"} ;        
        Portlet backlogPortlet = new Portlet("<i class=\"fas fa-cubes\">",
                "Itens de Backlog",
                backlogPortletHeaders,
                this.countAllBacklog(backlogsPerStatus),
                backlogsPerStatus,
                backlogPortletButton);
        
        request.setAttribute("backlogPorlet", backlogPortlet);
        
        return "WEB-INF/views/Home.jsp";
    }

    private int countAllBacklog(List<Object> backlogs) {
        int count = 0;
        
//        for (Object o:backlogs) {
//            IdNameCountPercentAdapter backlog = (IdNameCountPercentAdapter) o;
//            count += Integer.parseInt(backlog.getCount());
//        }        
        
        count = backlogs.stream().map((o) -> (IdNameCountPercentAdapter) o).map((backlog) -> Integer.parseInt(backlog.getCount())).reduce(count, Integer::sum);

        return count;
    }

    private List<Object> countBacklogPerStatus(HttpServletRequest request) {
        List<Object> datasource = new ArrayList<>();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(MVC.PERSISTENCE_UNIT);
        BacklogItemDAO backlogDAO = new BacklogItemDAO(factory);
        ProjectStatusDAO statusDAO = new ProjectStatusDAO(factory);

        // "SELECT o.statusId, COUNT(o) FROM BacklogItem o WHERE o.companyId = :companyId GROUP BY o.statusId"
        List<Object[]> results = backlogDAO.countByStatus(MVC.getUserCompanySessionId(request));

        Long counter = 0L;
        for (int linha = 0; linha < results.size(); linha++) {
            Object[] array = results.get(linha);
            counter += (Long) array[1];
        }

        NumberFormat intFormatter = new DecimalFormat("#0");
        NumberFormat doubleFormatter = new DecimalFormat("#0.0");
        for (int linha = 0; linha < results.size(); linha++) {
            Object[] array = results.get(linha);
            Long statusId = (Long) array[0];
            Long statusCount = (Long) array[1];
            Double statusPercent = (double) statusCount / counter;
            ProjectStatus status = statusDAO.find(statusId);
            datasource.add(new IdNameCountPercentAdapter(statusId, status.getName(), intFormatter.format(statusCount), doubleFormatter.format(statusPercent * 100)));
        }

        return datasource;
    }

//    List<Object[]> results = query.getResultList();
//    for (int i = 0; i < results.size(); i++) {
//        Object[] arr = results.get(i);
//        for (int j = 0; j < arr.length; j++) {
//            System.out.print(arr[j] + " ");
//        }
//        System.out.println();
//     }
//    

}
