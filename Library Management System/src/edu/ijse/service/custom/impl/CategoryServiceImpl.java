package edu.ijse.service.custom.impl;

import edu.ijse.dao.DaoFactory;
import edu.ijse.dao.custom.CategoryDao;
import edu.ijse.dto.CategoryDto;
import edu.ijse.entity.CategoryEntity;
import edu.ijse.service.custom.CategoryService;

import java.util.ArrayList;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = (CategoryDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.CATEGORY);
    @Override
    public String save(CategoryDto categoryDto) throws Exception {
        CategoryEntity entity = getCategoryEntity(categoryDto);
        return categoryDao.create(entity) ? "Success" : "Fail";
    }

    @Override
    public String update(CategoryDto categoryDto) throws Exception {
        CategoryEntity entity = getCategoryEntity(categoryDto);
        return categoryDao.update(entity) ? "Success" : "Fail";
    }

    @Override
    public String delete(String id) throws Exception {
        return categoryDao.delete(id) ? "Success" : "Fail";
    }

    @Override
    public CategoryDto get(String id) throws Exception {
        CategoryEntity entity = categoryDao.get(id);
        if (entity != null) {
            return getCategoryDto(entity);
        }
        return null;
    }

    @Override
    public ArrayList<CategoryDto> getAll() throws Exception {
        ArrayList<CategoryEntity> categoryEntities = categoryDao.getAll();

        if (categoryEntities != null && !categoryEntities.isEmpty()) {
            ArrayList<CategoryDto> categoryDtos = new ArrayList<>();

            for (CategoryEntity categoryEntity : categoryEntities) {
                categoryDtos.add(getCategoryDto(categoryEntity));
            }
            return categoryDtos;
        }
        return null;
    }

    private CategoryEntity getCategoryEntity(CategoryDto categoryDto) throws Exception {
        return new CategoryEntity(
                categoryDto.getCategoryId(),
                categoryDto.getCategoryName()
        );
    }

    private CategoryDto getCategoryDto(CategoryEntity entity) throws Exception {
        return new CategoryDto(
                entity.getCategoryId(),
                entity.getCategoryName()
        );
    }
}
