package edu.ijse.dao.custom.impl;

import edu.ijse.dao.CrudUtil;
import edu.ijse.dao.custom.CategoryDao;
import edu.ijse.entity.CategoryEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public boolean create(CategoryEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO category VALUES(?,?)",t.getCategoryId(),t.getCategoryName());
    }

    @Override
    public boolean update(CategoryEntity t) throws Exception {
        return CrudUtil.executeUpdate("UPDATE category SET categoryName=? WHERE categoryId=?",t.getCategoryName(),t.getCategoryId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM category WHERE categoryId=?",id);
    }

    @Override
    public CategoryEntity get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM category WHERE categoryId=?",id);
        if(rst.next()){
            CategoryEntity entity = new CategoryEntity(
                    rst.getString("categoryId"),
                    rst.getString("categoryName")
            );
            return entity;
        }
        return null;
    }

    @Override
    public ArrayList<CategoryEntity> getAll() throws Exception {
        ArrayList<CategoryEntity> categoryEntities = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM category");
        while(rst.next()){
            CategoryEntity entity = new CategoryEntity(
                    rst.getString("categoryId"),
                    rst.getString("categoryName")
            );
            categoryEntities.add(entity);
        }
        return categoryEntities;
    }
}
