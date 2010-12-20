package advboard.action.admin;

import advboard.action.BaseActionBean;
import advboard.data.Role;
import advboard.data.User;
import advboard.data.UserDAO;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import java.util.List;

@UrlBinding("/admin/index.action")
public class AdminIndexActionBean extends BaseActionBean
{
    @SpringBean
    UserDAO userDAO;

    private List<User> users;
    private List<Long> selectedForRemoval;

    @DefaultHandler
    public Resolution view()
    {
        users = userDAO.getUsersByRole(Role.ADVERTISER);
        return new ForwardResolution("/WEB-INF/jsp/admin/index.jsp");
    }

    public Resolution deleteSelected()
    {
        //TODO: check ids
        if (selectedForRemoval != null && !selectedForRemoval.isEmpty())
        {
            userDAO.deleteUsersByIds(selectedForRemoval);
        }
        return view();
    }

    public List<User> getUsers()
    {
        return users;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
    }

    public List<Long> getSelectedForRemoval()
    {
        return selectedForRemoval;
    }

    public void setSelectedForRemoval(List<Long> selectedForRemoval)
    {
        this.selectedForRemoval = selectedForRemoval;
    }
}
