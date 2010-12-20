<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<html>
<head><title>Login Page</title></head>
<body onload='document.f.j_username.focus();'>


<c:if test="${param.error != null}">
    <p align="center">
        <span style="color: red; ">Bad credentials. Your login attempt was not successful, try again.</span>
    </p>
</c:if>

<form name='f' action='/AdvBoard/j_spring_security_check' method='POST'>
    <table align="center">
        <tr>
            <td>User:</td>
            <td><input type='text' name='j_username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='j_password'/></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit"/></td>
            <td><input name="reset" type="reset"/></td>
        </tr>
    </table>
</form>
</body>
</html>
