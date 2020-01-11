package org.ftd.mytask.web.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.ftd.mytask.web.mvc.abstracts.MVC;

/**
 *
 * @author Fabio Tavares Dippold
 * 
 */
@WebFilter(filterName = "MainFilter",
        urlPatterns = {"/mvc", "/ajax", "/WEB-INF/**"})
public class MainFilter implements Filter {

    private static final boolean DEBUG = true;

    private FilterConfig filterConfig = null;

    public MainFilter() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        boolean isUserAuthorized = false;

        if (session != null) {
            
            String userId = (String) session.getAttribute("userId");
            String userName = (String) session.getAttribute("userName");
            String userCompanyId = (String) session.getAttribute("companyId");
            String userRuleId = (String) session.getAttribute("ruleId");

            if ((userId != null)
                    && (!userId.equals(""))
                    && (userName != null)
                    && (!userName.equals("")
                    && (!userCompanyId.equals("")
                    && (!userRuleId.equals(""))))) {
                
                    isUserAuthorized = true;
                    
            } else {
                session.removeAttribute("userId");
                session.removeAttribute("userName");
                session.removeAttribute("companyId");
                session.removeAttribute("ruleId");
                session.invalidate();
            }
        }

        if (isUserAuthorized) {
            if (DEBUG) {
                System.out.println(MVC.MSG_INVALID_SESSION);
            }
            chain.doFilter(request, response);
        } else {
            if (DEBUG) {
                System.out.println(MVC.MSG_INVALID_SESSION);
            }
            request.setAttribute(MVC.PARAMETER_NAME_MESSAGE, MVC.MSG_INVALID_SESSION);
            req.getRequestDispatcher(MVC.VIEW_SIGN_IN).forward(req, res);
        }

    }

    /**
     * Return the filter configuration object for this filter.
     *
     * @return
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (DEBUG) {
                log("MainFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("MainFilter()");
        }
        StringBuilder sb = new StringBuilder("MainFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (IOException ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
