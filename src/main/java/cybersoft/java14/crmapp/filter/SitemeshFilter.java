package cybersoft.java12.crmapp.filter;

import javax.servlet.annotation.WebFilter;

import com.opensymphony.sitemesh.webapp.SiteMeshFilter;

import cybersoft.java12.crmapp.util.UrlConst;

@WebFilter(filterName = "sitemesh", urlPatterns = UrlConst.ROOT)
public class SitemeshFilter extends SiteMeshFilter{

}
