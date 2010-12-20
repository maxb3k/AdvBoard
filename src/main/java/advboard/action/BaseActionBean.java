package advboard.action;

import advboard.security.SecurityUser;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class BaseActionBean implements ActionBean
{
    private ActionBeanContext context;
    protected static Logger log = Logger.getLogger(BaseActionBean.class);

    public ActionBeanContext getContext()
    {
        return context;
    }

    public void setContext(ActionBeanContext context)
    {
        this.context = context;
    }

    public SecurityUser getUser()
    {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof SecurityUser))
        {
            throw new RuntimeException("The user " + principal.getUsername() + " has not been initialized through real UserDetailsService");
        }

        return (SecurityUser) principal;
    }
}
