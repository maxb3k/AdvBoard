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

@UrlBinding("/advertiser/addadvertisement.action")
public class AddAdvertisementActionBean extends BaseActionBean
{
    private String message;
    private String tags;

    @SpringBean
    AdvertisementDAO advertisementDAO;

    @SpringBean
    UserDAO userDAO;

    public AddAdvertisementActionBean()
    {
    }

    @DefaultHandler
    public Resolution view()
    {
        return new ForwardResolution("/WEB-INF/jsp/advertiser/addAdvertisement.jsp");
    }

    public Resolution addAdvertisement()
    {
        Advertisement a = new Advertisement();
        User author = userDAO.getUserById(getUser().getEntityId());
        a.setAuthor(author);
        a.setCreationDate();
        a.setMessage(message);
        a.setTags(tags);
        advertisementDAO.addAdvertisement(a);
        return new ForwardResolution("/index.html");
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getTags()
    {
        return tags;
    }

    public void setTags(String tags)
    {
        this.tags = tags;
    }
}
