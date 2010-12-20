package advboard.data;

import java.util.Collection;
import java.util.List;

public interface AdvertisementDAO
{
    List<Advertisement> getAdvertisements(int from, int count, OrderBy order);

    List<Advertisement> getUserAdvertisements(User user, int from, int count);

    void addAdvertisement(Advertisement a);

    void deleteAdvertisementsByIds(Collection<Long> ids);

    void deleteAdvertisementsForUser(User u);

    public enum OrderBy
    {
        asc_creationDate,
        desc_creationDate,
        asc_name,
        desc_name
    }
}
