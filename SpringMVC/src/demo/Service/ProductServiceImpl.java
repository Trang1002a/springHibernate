package demo.Service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
	public List<Product> findAll(int position, int pageSize, String name) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Product.class).add(Restrictions.like("name", "%" + name + "%"));
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
	public Long countTotalRecords(String name) {
		Session session = sessionFactory.openSession();
		Long count;
		if (name == null) {
			count = (Long) session.createCriteria(Product.class).setProjection(Projections.rowCount()).uniqueResult();
		} else {
			count = (Long) session.createCriteria(Product.class).setProjection(Projections.rowCount())
					.add(Restrictions.like("name", "%" + name + "%")).uniqueResult();
		}
		return count;
	}

	@Override
	public List<Product> findByIdIn(List<Integer> ids) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
//					 = session.createCriteria().add(
//				            Restrictions.eq("id", ids)).setProjection(Projections.property("id")).list();
			List list = session.createCriteria(Product.class).add(Restrictions.in("id", ids)).list();
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

}
