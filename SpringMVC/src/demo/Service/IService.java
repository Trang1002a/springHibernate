package demo.Service;

import java.util.List;

import demo.entity.Category;

public interface IService<T, ID> {
	List<T> findAll();
	T findById(ID id);
	T save(T category);
	boolean deleteById(ID id);
}
