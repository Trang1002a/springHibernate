package demo.Service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.entity.Product;
import demo.entity.Warehouse;

@Service
public class WarehouseImpl implements IService<Warehouse, Integer> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Warehouse> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Warehouse> findAll(int position, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Warehouse> findAll(int position, int pageSize, String name) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Warehouse.class)
					.add(Restrictions.like("name", "%" + name + "%"));
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
	public Warehouse findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Warehouse save(Warehouse category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Long countTotalRecords(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
