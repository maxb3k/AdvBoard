package advboard.action.advertiser;

import advboard.action.BaseActionBean;
import advboard.data.Advertisement;
import advboard.data.AdvertisementDAO;
import advboard.data.User;
import advboard.data.UserDAO;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import java.util.List;

@UrlBinding("/advertiser/myAdvertisements.action")
public class MyAdvertisementsActionBean extends BaseActionBean
{
    private List<Advertisement> advertisements;
    private List<Long> selectedForRemoval;

    @SpringBean
    AdvertisementDAO advertisementDAO;

    @SpringBean
    UserDAO userDAO;

    @DefaultHandler
    public Resolution view()
    {
        User user = userDAO.getUserById(getUser().getEntityId());
        if (user != null)
        {
            advertisements = advertisementDAO.getUserAdvertisements(user, 0, 1000);
        }
        return new ForwardResolution("/WEB-INF/jsp/advertiser/myAdvertisements.jsp");
    }

    public Resolution deleteSelected()
    {
        //TODO: check selected ids is not hacked
        //Collection ids = CollectionUtils.intersection( idsOnView, selectedForRemoval );
        if (selectedForRemoval != null && !selectedForRemoval.isEmpty())
        {
            log.debug("delete ids: " + selectedForRemoval);
            advertisementDAO.deleteAdvertisementsByIds(selectedForRemoval);
        }
        return new ForwardResolution("/index.html");
    }


    public List<Advertisement> getAdvertisements()
    {
        return advertisements;
    }

    public void setAdvertisements(List<Advertisement> advertisements)
    {
        this.advertisements = advertisements;
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
