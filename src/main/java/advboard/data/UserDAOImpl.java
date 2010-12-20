package advboard.data;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO
{
    @PersistenceContext
    private EntityManager em;

    //used to remove users advertisement before removing user, to satisfy (advertisement -> user) foreign key.
    @Resource
    private AdvertisementDAO advertisementDAO;

    @Transactional(readOnly = true)
    public User getUserById(Long id)
    {
        return em.find(User.class, id);
    }

    @Transactional(readOnly = true)
    public User getUserByName(String name)
    {
        List users = em.createQuery("SELECT u FROM User u WHERE u.name = :name")
                .setParameter("name", name)
                .setMaxResults(1)
                .getResultList();

        return (users.isEmpty()) ? null : (User) users.get(0);
    }

    @Transactional(readOnly = true)
    public User getUserByNamePass(String name, String pass)
    {
        List users = em.createQuery("SELECT u FROM User u WHERE u.name = :name AND u.password = :password")
                .setParameter("name", name)
                .setParameter("password", pass)
                .setMaxResults(1)
                .getResultList();

        return (users.isEmpty()) ? null : (User) users.get(0);
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<User> getUsersByRole(Role role)
    {
        return em.createQuery("SELECT u FROM User u JOIN u.roles r WHERE r = :role")
                .setParameter("role", role)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<User> getUsersByIds(Collection<Long> ids)
    {
        return em.createQuery("SELECT u FROM User u WHERE u.id in (:ids)")
                .setParameter("ids", ids)
                .getResultList();
    }

    /**
     * Removes users with specified ids one by one
     * Notice that JPA has no correct way to bulk delete entity with all related objects.
     * The alternative is to remove related manually.
     *
     * @param ids collection of removed ids
     */
    public void deleteUsersByIds(Collection<Long> ids)
    {
        deleteUsers(getUsersByIds(ids));
    }

    private void deleteUsers(Collection<User> users)
    {
        for (User u : users)
        {
            deleteUser(u);
        }
    }

    public void addUser(User user)
    {
        em.persist(user);
    }

    public void deleteUser(User user)
    {
        advertisementDAO.deleteAdvertisementsForUser(user);
        em.remove(user);
    }
}
