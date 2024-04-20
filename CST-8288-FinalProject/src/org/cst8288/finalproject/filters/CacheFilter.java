package org.cst8288.finalproject.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet Filter implementation class for cache control.
 * This filter sets HTTP headers to prevent the browser from caching HTTP responses.
 * It is applied globally to all paths (/*) within the application to ensure that all responses are served fresh.
 * This is particularly important in applications where sensitive data is displayed, and outdated
 * or cached content could lead to security risks or poor user experience.
 *
 * The filter enforces 'no-store' directive to prevent browsers and all intermediate caches (like CDNs)
 * from storing any version of returned HTTP responses. 'No-cache' and 'must-revalidate' directives are set to ensure
 * that if any content is accidentally cached, it must be revalidated on the server before being served.
 *
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-20
 * @see javax.servlet.Filter
 */
@WebFilter("/*")
public class CacheFilter implements Filter {
	
	/**
	 * This method implements the filter logic to prevent caching of HTTP responses.
	 * It sets appropriate cache control headers to ensure that responses are not cached by the client or intermediary caches.
	 * 
	 * @param req The servlet request object.
	 * @param res The servlet response object.
	 * @param filter The filter chain for invoking subsequent filters in the chain.
	 * @throws IOException If an I/O exception occurs while processing the request or response.
	 * @throws ServletException If a servlet exception occurs while processing the request or response.
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filter)
            throws IOException, ServletException 
    {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.
        filter.doFilter(req, res);
    }

    
}
