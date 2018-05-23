package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Category;
import com.revature.util.HibernateUtil;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> getCategories() {
		List<Category> flashcards = new ArrayList<Category>();
		Session s = HibernateUtil.getSession();
		List<Category> categories = s.createQuery("from Category").list();
		s.close();
		return categories;
	}

	@Override
	public Category getCategoryById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addCategory(Category c) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int result = (int) s.save(c);
		tx.commit();
		s.close();
		return result;
	}

	@Override
	public void updateCategory(Category c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCategory(Category c) {
		// TODO Auto-generated method stub

	}

}
