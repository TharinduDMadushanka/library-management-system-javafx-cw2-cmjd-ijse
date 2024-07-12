package edu.ijse.UIController;

import edu.ijse.dto.CategoryDto;
import edu.ijse.service.custom.impl.CategoryServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class BookCategoryFormController {
    public AnchorPane categoryContext;
    public TextField txtId;
    public TextField txtName;
    public TextField txtSearch;
    public TableView<CategoryDto> categoryTable;
    public TableColumn<CategoryDto,String> colId;
    public TableColumn<CategoryDto,String> colName;

    private CategoryServiceImpl categoryService = new CategoryServiceImpl();
    private ObservableList<CategoryDto> categoryList = FXCollections.observableArrayList();

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("categoryName"));

        loadCategory();
        setNewCategoryId();

        categoryTable.setItems(categoryList);
        categoryTable.setOnMouseClicked(this::selectCategory);
    }

    public void addOnAction(ActionEvent actionEvent) {

        if (txtName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.INFORMATION, "Please Enter Category name..!").show();
            return;
        }

        String newCategoryId = txtId.getText();
        CategoryDto category = new CategoryDto(
                newCategoryId,
                txtName.getText()
        );

        try {
            String result = categoryService.save(category);
            if("Success".equals(result)) {
                categoryList.add(category);
                categoryTable.setItems(categoryList);
                clearField();
                setNewCategoryId();
                new Alert(Alert.AlertType.INFORMATION, "Category added successfully").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.INFORMATION, "Error in save..!").show();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {

        if (txtName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.INFORMATION, "Please Enter Category name..!").show();
            return;
        }

        String newCategoryId = txtId.getText();
        CategoryDto category = new CategoryDto(
                newCategoryId,
                txtName.getText()
        );

        try {
            String result = categoryService.update(category);
            if("Success".equals(result)) {
                loadCategory();
                clearField();
                setNewCategoryId();
                new Alert(Alert.AlertType.INFORMATION, "Category updated successfully").show();
            }
        }catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.INFORMATION, "Error in update..!").show();
        }

    }

    public void deleteOnAction(ActionEvent actionEvent) {

        try {
            String result = categoryService.delete(txtId.getText());
            if("Success".equals(result)) {
                loadCategory();
                clearField();
                setNewCategoryId();
            }
        }catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.INFORMATION, "Error in delete...").show();
        }

    }

    public void clearOnAction(ActionEvent actionEvent) {
        clearField();
        setNewCategoryId();
    }

    private void loadCategory() {
        try {
            ArrayList<CategoryDto> category = categoryService.getAll();
            if (category != null) {
                categoryList.setAll(category);
                categoryTable.setItems(categoryList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearField() {
        txtId.clear();
        txtName.clear();
    }

    private void selectCategory(MouseEvent mouseEvent) {
        CategoryDto selectCategory = categoryTable.getSelectionModel().getSelectedItem();
        if (selectCategory != null) {
            txtId.setText(selectCategory.getCategoryId());
            txtName.setText(selectCategory.getCategoryName());
        }
    }

    private String generateCategoryId() {

        return "BC-";
    }


    private void setNewCategoryId() {
        // Generate a new category ID and set it to the txtId field
        String newCategoryId = generateCategoryId();
        txtId.setText(newCategoryId);
    }

}
