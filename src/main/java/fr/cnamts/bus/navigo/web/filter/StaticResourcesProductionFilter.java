package fr.cnamts.bus.navigo.web.filter;


import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * This filter is used in production, to serve static resources generated by "grunt build".
 * <p/>
 * <p>
 * It is configured to serve resources from the "dist" directory, which is the Grunt
 * destination directory.
 * </p>
 */
public class StaticResourcesProductionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Nothing to initialize
    }

    @Override
    public void destroy() {
        // Nothing to destroy
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String contextPath = ((HttpServletRequest) request).getContextPath();
        String requestURI = httpRequest.getRequestURI();
        requestURI = StringUtils.substringAfter(requestURI, contextPath);
        if (StringUtils.equals("/", requestURI)) {
            requestURI = "/index.html";
        }
        String newURI = "/dist" + requestURI;
        request.getRequestDispatcher(newURI).forward(request, response);
    }
}
