<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-definition>

    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
    <html>
    <head>
        <title>${title}</title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css">
        <s:layout-component name="head">
        </s:layout-component>
    </head>
    <body>
    <div id="main">
        <div id="user" width="100%">
            <nobr>

                <c:choose>
                    <c:when test="${su:isAnonymous()}">
                        Hello, guest! |
                        <a href="${contextPath}/login">Login</a>
                    </c:when>
                    <c:otherwise>
                        Hello, <sec:authentication property="name"/> |
                        <a href="${contextPath}/j_spring_security_logout">Logout</a>
                    </c:otherwise>
                </c:choose>

                <sec:authorize access="hasRole('SECURITY_ADMIN')">
                    | <a href="${contextPath}/registeruser.action">Register</a>
                </sec:authorize>

                <sec:authorize access="hasRole('ADMIN')">
                    | <a href="${contextPath}/index.action">Simple view</a>
                    | <a href="${contextPath}/admin/index.action">Admin view</a>
                </sec:authorize>

            </nobr>
        </div>
        <div style="text-align: center;">
            <div id="staff">
                <s:layout-component name="body">
                </s:layout-component>
            </div>
        </div>
    </div>
    </body>
    </html>

</s:layout-definition>