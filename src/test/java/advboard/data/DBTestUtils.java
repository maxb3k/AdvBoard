package advboard.data;

import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static junit.framework.Assert.assertEquals;

/**
 * DB layer util methods, that should not be in any DAO.
 * I'd like to implement that utils methods using jdbcTemplate,
 * but as i know there is no way to use JPA and jdbcTemplate
 * in one transaction without involving the J2EEs jtaTransactionManager.
 * Am i right?
 */

@ContextConfiguration("/test-context.xml")
@Repository
public class DBTestUtils
{
    @PersistenceContext
    private EntityManager em;

    public void clearAdvertisements()
    {
        em.createQuery("DELETE FROM Advertisement").executeUpdate();
        assertEquals(0L, countAdvertisements());
    }

    private long countAdvertisements()
    {
        return (Long) em.createQuery("SELECT COUNT(a) FROM Advertisement a").getSingleResult();
    }
}
