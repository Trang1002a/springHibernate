package demo.Service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import antlr.StringUtils;
import demo.entity.Category;

@Repository
public class CategoryDaoImpl implements IService<Category, Integer> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			List list = session.createCriteria(Category.class).list();
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
	public Category findById(Integer id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();

			Category category = session.get(Category.class, id);
			session.getTransaction().commit();
			session.close();
			return category;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
		}
		return null;
	}

	@Override
	public Category save(Category cat) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(cat);
			session.getTransaction().commit();
			session.close();
			return cat;
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
			Category category = session.load(Category.class, id);
			session.delete(category);
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
	public List<Category> findAll(int position, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countTotalRecords(String name) {
		Session session = sessionFactory.openSession();
		String countQ = "Select count (c.id) from tbl_category c";
		Query countQuery = session.createQuery(countQ);
		return (Long) countQuery.uniqueResult();
	}

	@Override
	public List<Category> findAll(int position, int pageSize, String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
