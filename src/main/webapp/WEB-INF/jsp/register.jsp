<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="Register new user">
    <s:layout-component name="body">
        <p>Fill the fields below to register new user</p>

        <s:form beanclass="advboard.action.RegisterUserActionBean" id="regForm" focus="">
            <s:errors/>
            <table align="center">
                <tr>
                    <td>Name:</td>
                    <td><s:text name="name"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><s:password name="password"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <s:submit name="register" value="register"/>
                    </td>
                </tr>
            </table>
        </s:form>

    </s:layout-component>
</s:layout-render>
