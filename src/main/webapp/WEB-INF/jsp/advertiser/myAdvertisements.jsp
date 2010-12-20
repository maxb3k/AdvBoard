<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="My advertisements">
    <s:layout-component name="body">
        <p>My advertisement list:
            <a href="${contextPath}/index.html"> back to all advertisements</a>
        </p>
        <s:form beanclass="advboard.action.advertiser.MyAdvertisementsActionBean">
            <s:errors/>
            <table width="40%" border="2px" align="center">
            <tr>
                <td>
                    Author
                </td>
                <td>
                    creation date
                </td>
                <td>
                    tags
                </td>
                <td>
                    message
                </td>
                <td>
                    delete?
                </td>
            </tr>
            <c:forEach items="${actionBean.advertisements}" var="adv" varStatus="loop">
                <tr>
                    <td>
                            ${adv.author.name}
                    </td>
                    <td>
                            ${adv.creationDate}
                    </td>
                    <td>
                            ${adv.tags}
                    </td>
                    <td>
                            ${adv.message}
                    </td>
                    <td>
                        <s:checkbox name="selectedForRemoval" value="${adv.id}"/>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="5">
                    <s:submit name="deleteSelected" value="delete"/>
                </td>
            </tr>
        </s:form>
        </table>
    </s:layout-component>
</s:layout-render>
