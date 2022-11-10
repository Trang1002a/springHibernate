package demo.Service;

import java.util.List;

import demo.entity.Category;

public interface IService<T, ID> {
	List<T> findAll();
	List<T> findAll(int position, int pageSize);
	List<T> findAll(int position, int pageSize, String name);
	List<T> findByIdIn(List<ID> ids);
	T findById(ID id);
	T save(T category);
	boolean deleteById(ID id);
	Long countTotalRecords(String name);
}
