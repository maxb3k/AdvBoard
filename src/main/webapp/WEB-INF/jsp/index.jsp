<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<%--TODO: add "user registered successfully" message--%>

<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="Welcome">
    <s:layout-component name="body">
        <p>Advertisement list:
            <sec:authorize access="hasRole('ADVERTISER')">
                <s:link beanclass="advboard.action.advertiser.AddAdvertisementActionBean">create new</s:link>
                <s:link beanclass="advboard.action.advertiser.MyAdvertisementsActionBean">my advertisements</s:link>
            </sec:authorize>
        </p>
        <table width="40%" border="2px" align="center">
            <tr>
                <td>
                    <s:link beanclass="advboard.action.IndexActionBean" event="sortByAuthor">Author</s:link>
                </td>
                <td>
                    <s:link beanclass="advboard.action.IndexActionBean"
                            event="sortByDate">creation date</s:link>
                </td>
                <td>
                    tags
                </td>
                <td>
                    message
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
                </tr>
            </c:forEach>
        </table>
    </s:layout-component>
</s:layout-render>
