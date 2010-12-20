<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="create new advertisement">
    <s:layout-component name="body">
        <s:form beanclass="advboard.action.advertiser.AddAdvertisementActionBean" focus="">
            <s:errors/>
            <table align="center">
                <tr>
                    <td>Tags:</td>
                    <td><s:text name="tags"/></td>
                </tr>
                <tr>
                    <td>Message:</td>
                    <td><s:textarea cols="25" rows="3" name="message"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <s:submit name="addAdvertisement" value="add"/>
                    </td>
                </tr>
            </table>
        </s:form>
    </s:layout-component>
</s:layout-render>
