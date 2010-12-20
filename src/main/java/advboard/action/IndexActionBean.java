package advboard.action;

import advboard.data.Advertisement;
import advboard.data.AdvertisementDAO;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import java.util.List;

public class IndexActionBean extends BaseActionBean
{
    private List<Advertisement> advertisements;

    @SpringBean
    private AdvertisementDAO advertisementDAO;

    @DefaultHandler
    public Resolution view()
    {
        advertisements = advertisementDAO.getAdvertisements(0, 1000, AdvertisementDAO.OrderBy.asc_creationDate);
        return new ForwardResolution("/WEB-INF/jsp/index.jsp");
    }

    public Resolution sortByAuthor()
    {
        advertisements = advertisementDAO.getAdvertisements(0, 1000, AdvertisementDAO.OrderBy.asc_name);
        return new ForwardResolution("/WEB-INF/jsp/index.jsp");
    }

    public Resolution sortByDate()
    {
        advertisements = advertisementDAO.getAdvertisements(0, 1000, AdvertisementDAO.OrderBy.asc_creationDate);
        return new ForwardResolution("/WEB-INF/jsp/index.jsp");
    }

    public List<Advertisement> getAdvertisements()
    {
        return advertisements;
    }

    public void setAdvertisements(List<Advertisement> advertisements)
    {
        this.advertisements = advertisements;
    }
}
