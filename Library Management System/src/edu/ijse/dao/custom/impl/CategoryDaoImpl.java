package edu.ijse.dao.custom.impl;

import edu.ijse.dao.custom.CategoryDao;
import edu.ijse.entity.CategoryEntity;

import java.util.ArrayList;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public boolean create(CategoryEntity categoryEntity) throws Exception {
        return false;
    }

    @Override
    public boolean update(CategoryEntity categoryEntity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public CategoryEntity get(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<CategoryEntity> getAll() throws Exception {
        return null;
    }
}
