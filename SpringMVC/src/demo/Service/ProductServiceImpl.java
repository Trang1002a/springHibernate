package demo.Service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import demo.entity.Product;

@Repository
public class ProductServiceImpl implements IService<Product, Integer> {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			List list = session.createCriteria(Product.class).list();
			session.getTransaction().commit();
			session.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
		}
		return null;
	}

	@Override
	public Product findById(Integer id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();

			Product product = session.get(Product.class, id);
			session.getTransaction().commit();
			session.close();
			return product;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
		}
		return null;
	}

	@Override
	public Product save(Product product) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(product);
			session.getTransaction().commit();
			session.close();
			return product;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
		}
		return null;
	}

	@Override
	public boolean deleteById(Integer id) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Product product = session.load(Product.class, id);
			session.delete(product);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
			return false;
		}
	}

	
	@Override
	public List<Product> findAll(int position, int pageSize) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setFirstResult(position);
			criteria.setMaxResults(pageSize);
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
		}
		return null;
	}

	@Override
	public Long countTotalRecords() {
		Session session = sessionFactory.openSession();
		String countQ = "Select count (p.id) from Product p";
		Query countQuery = session.createQuery(countQ);
		return (Long) countQuery.uniqueResult();
	}

}
