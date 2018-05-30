package com.revature.dao;

import java.util.List;

import com.revature.beans.Category;

public interface CategoryDao {
	
	public List<Category> getCategories();

	public Category getCategoryById(int id);

	public int addCategory(Category c);

	public void updateCategory(Category c);

	public void deleteCategory(Category c);

}
