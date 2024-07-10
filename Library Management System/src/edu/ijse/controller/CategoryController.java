package edu.ijse.controller;

import edu.ijse.dto.CategoryDto;
import edu.ijse.service.ServiceFactory;
import edu.ijse.service.custom.CategoryService;

import java.util.ArrayList;

public class CategoryController {
    private CategoryService categoryService = (CategoryService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.CATEGORY);

    public String save(CategoryDto categoryDto) throws Exception {
        return categoryService.save(categoryDto);
    }

    public String update(CategoryDto categoryDto) throws Exception {
        return categoryService.update(categoryDto);
    }

    public String delete(String id) throws Exception {
        return categoryService.delete(id);
    }
    public CategoryDto get(String id) throws Exception {
        return categoryService.get(id);
    }
    public ArrayList<CategoryDto> getAll() throws Exception {
        return categoryService.getAll();
    }
}
