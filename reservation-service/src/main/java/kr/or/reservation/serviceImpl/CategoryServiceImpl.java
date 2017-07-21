package kr.or.reservation.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.reservation.dao.CategoryDao;
import kr.or.reservation.domain.Category;
import kr.or.reservation.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
    CategoryDao categorydao;
	
	@Override
	public Long insert(Category category) {
		// TODO Auto-generated method stub
		if(category != null) {
			return categorydao.insert(category);
		}
		return null;
	}

	@Override
	public int update(Category category) {
		if(category != null) {
			return categorydao.updateById(category);
		}
		return 0;
	}

	@Override
	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		if(id != null) {
			return categorydao.deleteById(id);
		}
		return 0;
	}

	@Override
	@Transactional(readOnly = true)
	public Category selectById(Long id) {
		if(id != null) {
			return categorydao.selectById(id);
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Category> selectforList() {
		// TODO Auto-generated method stub
		return categorydao.selectforList();
	}



}
