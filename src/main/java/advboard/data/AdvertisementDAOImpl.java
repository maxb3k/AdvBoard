package advboard.data;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public class AdvertisementDAOImpl implements AdvertisementDAO
{
    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public List<Advertisement> getAdvertisements(int from, int count, OrderBy order)
    {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Advertisement> criteriaQuery = qb.createQuery(Advertisement.class);
        Root<Advertisement> advertisementRoot = criteriaQuery.from(Advertisement.class);
        criteriaQuery.select(advertisementRoot);
        Join<Object, Object> authorJoin;

        switch (order)
        {
            case asc_name:
                authorJoin = advertisementRoot.join("author");
                criteriaQuery.orderBy(qb.asc(authorJoin.get("name")));
                break;
            case desc_name:
                authorJoin = advertisementRoot.join("author");
                criteriaQuery.orderBy(qb.desc(authorJoin.get("name")));
                break;
            case asc_creationDate:
                criteriaQuery.orderBy(qb.asc(advertisementRoot.get("creationDate")));
                break;
            case desc_creationDate:
                criteriaQuery.orderBy(qb.desc(advertisementRoot.get("creationDate")));
                break;
        }

        TypedQuery<Advertisement> query = em.createQuery(criteriaQuery).setFirstResult(from).setMaxResults(count);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Advertisement> getUserAdvertisements(User user, int from, int count)
    {
        return em.createQuery("SELECT a FROM Advertisement a WHERE a.author = :author").setParameter("author", user).setFirstResult(from).setMaxResults(count).getResultList();
    }

    public void addAdvertisement(Advertisement a)
    {
        em.persist(a);
    }

    /*
     * Notice: the following are BULK operations, they will not delete advertisement related objects
     */

    public void deleteAdvertisementsByIds(Collection<Long> ids)
    {
        em.createQuery("DELETE FROM Advertisement WHERE id IN(:ids)").setParameter("ids", ids).executeUpdate();
    }

    public void deleteAdvertisementsForUser(User u)
    {
        em.createQuery("DELETE FROM Advertisement WHERE author = (:u)").setParameter("u", u).executeUpdate();
    }
}
