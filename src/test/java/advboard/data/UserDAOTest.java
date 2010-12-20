package advboard.data;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import static org.junit.Assert.*;

/**
 * DB integration test
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class UserDAOTest
{
    @Resource
    private advboard.data.UserDAO userDAO;

    @Transactional
    @Test
    public void createUser() throws Exception
    {
        User user = new User("testUser", "pass");
        user.setRoles(EnumSet.allOf(Role.class));
        userDAO.addUser(user);

        assertNotNull(userDAO.getUserByName("testUser"));
    }

    @Transactional
    @Test(expected = javax.persistence.PersistenceException.class)
    public void createUserWithExistingName() throws Exception
    {
        User user = new User("testUser", "pass");
        userDAO.addUser(user);
        User user2 = new User("testUser", "passzz");
        userDAO.addUser(user2);
    }

    @Transactional
    @Test
    public void getUser() throws Exception
    {
        User user = new User("testUser", "pass");
        user.setRoles(EnumSet.of(Role.ADMIN, Role.SECURITY_ADMIN));
        userDAO.addUser(user);

        User user2 = new User("testUser2", "pass");
        user2.setRoles(EnumSet.of(Role.ADMIN, Role.SECURITY_ADMIN));
        userDAO.addUser(user2);

        assertEquals("testUser", userDAO.getUserByNamePass("testUser", "pass").getName());

        assertTrue(userDAO.getUsersByRole(Role.ADMIN).size() >= 2);
    }

    @Transactional
    @Test
    public void getNotExistingUser() throws Exception
    {
        assertNull(userDAO.getUserByName("impossibleName"));
        assertNull(userDAO.getUserByNamePass("impossibleName", "AndPass"));
    }

    @Transactional
    @Test
    public void deleteUsers() throws Exception
    {
        List<User> advs = userDAO.getUsersByRole(Role.ADVERTISER);
        List<Long> userIds = Lists.transform(advs, new Function<User, Long>()
        {
            public Long apply(User user)
            {
                return user.getId();
            }
        });

        userDAO.deleteUsersByIds(userIds);

        assertEquals(Collections.EMPTY_LIST, userDAO.getUsersByRole(Role.ADVERTISER));
    }
}
