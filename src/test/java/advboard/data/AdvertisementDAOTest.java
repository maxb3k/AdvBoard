package advboard.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.EnumSet;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * DB integration test
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class AdvertisementDAOTest
{
    @Resource
    private AdvertisementDAO advertisementDAO;

    @Resource
    private advboard.data.UserDAO userDAO;

    @Resource
    DBTestUtils testUtils;

    @Transactional
    @Test
    public void createAdvertisement() throws Exception
    {
        User author = createAuthor("test_author", "p");
        putAdv(author, "test message", "tag1, tag2", "2010-10-10 00:00:00");
        putAdv(author, "test message2", "tag1, tag2", "2010-10-10 00:00:00");

        List<Advertisement> advertisements = advertisementDAO.getUserAdvertisements(author, 0, 2);

        assertEquals(2, advertisements.size());

        assertEquals("test_author", advertisements.get(0).getAuthor().getName());
        assertEquals("test_author", advertisements.get(1).getAuthor().getName());

        assertEquals("test message", advertisements.get(0).getMessage());
        assertEquals("test message2", advertisements.get(1).getMessage());

        assertEquals("tag1, tag2", advertisements.get(0).getTags());
        assertEquals("tag1, tag2", advertisements.get(1).getTags());
    }

    @Transactional
    @Test
    public void getAdvertisementByDate() throws Exception
    {
        testUtils.clearAdvertisements();

        User author = createAuthor("test_author", "p");
        putAdv(author, "test message", "tag1, tag2", "2011-10-10 00:00:00");
        putAdv(author, "test message2", "tag1, tag2", "2011-10-10 00:00:01");

        List<Advertisement> advertisements = advertisementDAO.getAdvertisements(0, 1000, AdvertisementDAO.OrderBy.desc_creationDate);

        assertEquals(2, advertisements.size());
        assertEquals("test_author", advertisements.get(0).getAuthor().getName());
        assertEquals("test_author", advertisements.get(1).getAuthor().getName());

        assertEquals("test message2", advertisements.get(0).getMessage());
        assertEquals("test message", advertisements.get(1).getMessage());

        assertEquals(java.sql.Timestamp.valueOf("2011-10-10 00:00:01"), advertisements.get(0).getCreationDate());
        assertEquals(java.sql.Timestamp.valueOf("2011-10-10 00:00:00"), advertisements.get(1).getCreationDate());
    }

    @Transactional
    @Test
    public void getAdvertisementByAuthor() throws Exception
    {
        testUtils.clearAdvertisements();

        User author = createAuthor("author_", "p");
        User author2 = createAuthor("author__", "p");

        putAdv(author, "test message", "tag1, tag2", "2011-10-10 00:00:00");
        putAdv(author2, "test message", "tag1, tag2", "2011-10-10 00:00:01");

        List<Advertisement> advertisements = advertisementDAO.getAdvertisements(0, 1000, AdvertisementDAO.OrderBy.asc_name);

        assertEquals(2, advertisements.size());
        assertEquals("author_", advertisements.get(0).getAuthor().getName());
        assertEquals("author__", advertisements.get(1).getAuthor().getName());
    }

    private void putAdv(User author, String message, String tags, String creationDate)
    {
        Advertisement advertisement = new Advertisement(message, tags, author);
        advertisement.setCreationDate(Timestamp.valueOf(creationDate));
        advertisementDAO.addAdvertisement(advertisement);
    }

    private User createAuthor(String authorName, String authorPassword)
    {
        User author = new User(authorName, authorPassword, EnumSet.of(Role.ADVERTISER));
        userDAO.addUser(author);
        return author;
    }
}