package edu.ijse.service.custom;

import edu.ijse.dto.CategoryDto;
import edu.ijse.service.SuperService;

import java.util.ArrayList;

public interface CategoryService extends SuperService {
    String save(CategoryDto categoryDto)throws Exception;
    String update(CategoryDto categoryDto)throws Exception;
    String delete(String id)throws Exception;
    CategoryDto get(String id)throws Exception;
    ArrayList<CategoryDto> getAll()throws Exception;
}
