<%-- Stripes TLD --%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sdyn" uri="http://stripes.sourceforge.net/stripes-dynattr.tld" %>

<%-- JSTL TLDs --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"       %>--%>
<%--<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>--%>

<%-- Spring Security TLDs --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="/WEB-INF/tld/SpringSecurityUtils.tld" prefix="su" %>

<%-- This is so that you can conveniently refer to the context path with ${contextPath} --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

