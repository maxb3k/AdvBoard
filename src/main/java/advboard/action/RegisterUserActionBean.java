package advboard.action;

import advboard.data.Role;
import advboard.data.User;
import advboard.data.UserDAO;
import advboard.security.SpringSecurityUtils;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

import java.util.EnumSet;

public class RegisterUserActionBean extends BaseActionBean
{
    private String name;
    private String password;

    @SpringBean
    private UserDAO userDAO;

    @DefaultHandler
    public Resolution view()
    {
        return new ForwardResolution("/WEB-INF/jsp/register.jsp");
    }

    public Resolution register()
    {
        userDAO.addUser(new User(name, SpringSecurityUtils.md5Encode(password), EnumSet.of(Role.ADVERTISER)));
        return new ForwardResolution("/index.html");
    }

    @ValidationMethod(on = "register")
    public void checkUniqueConstraint(ValidationErrors errors)
    {
        if (userDAO.getUserByName(name) != null)
        {
            errors.add("name", new SimpleError("That name({1}), already taken by someone."));
        }
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
