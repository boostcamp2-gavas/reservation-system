package connect.reservation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import connect.reservation.dao.CategoryDao;
import connect.reservation.dao.ProductDao;
import connect.reservation.domain.Category;
import connect.reservation.service.CategoryService;

@Service
public class CategoryServiceimpl implements CategoryService {
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	ProductDao productDao;
	
	@Override
	@Transactional(readOnly = true)
	public Category get(int id) {
		return categoryDao.selectById(id);
	}
	
	@Override
	@Transactional(readOnly = false)
	public Category addCategory(String newCategory) {
		Category category = new Category(newCategory);

		int insert = categoryDao.insert(category);
		category.setId(insert);
		return category;
	}

	@Override
	public int deleteById(int id) {
		return categoryDao.deleteById(id);
	}
	
	@Override
	public int deleteAll() {
		return categoryDao.deleteAll();
	}
	
	@Override
	public int updateById(String newCategory, int id) {
		Category category = new Category(newCategory);
		category.setId(id);
		
		return categoryDao.updateById(category);
	}
	
	@Override
	public List<Category> getAll() {
		List<Category> list = new ArrayList<Category>();
		list = categoryDao.selectAll();
		
		return list;
	}
	
	@Override
	public int getAllCount() {
		return categoryDao.selectAllCount();
	}
}
