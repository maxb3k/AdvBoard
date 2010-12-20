<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="Advertisements">
    <s:layout-component name="body">
        <sec:authorize access="hasRole('ADMIN')">
            <p>Advertisement list:</p>
            <s:form beanclass="advboard.action.admin.AdminIndexActionBean">
                <table width="40%" border="2px" align="center">
                    <tr>
                        <td>
                            Advertisement:
                        </td>
                        <td>
                            delete?
                        </td>
                    </tr>
                    <c:forEach items="${actionBean.users}" var="user" varStatus="loop">
                        <tr>
                            <td>
                                    ${user.name}
                            </td>
                            <td>
                                <s:checkbox name="selectedForRemoval" value="${user.id}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="5">
                            <s:submit name="deleteSelected" value="delete"/>
                        </td>
                    </tr>
                </table>
            </s:form>
        </sec:authorize>
    </s:layout-component>
</s:layout-render>
