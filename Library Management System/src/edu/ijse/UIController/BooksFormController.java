package edu.ijse.UIController;

import edu.ijse.dto.BookDto;
import edu.ijse.dto.CategoryDto;
import edu.ijse.service.custom.impl.BookServiceImpl;
import edu.ijse.service.custom.impl.CategoryServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class BooksFormController {
    public AnchorPane bookContext;
    public TextField txtBookId;
    public TextField txtTitle;
    public TextField txtAuthor;
    public TextField txtCategoryShow;
    public DatePicker txtYear;
    public TextField txtSearch;
    public TextField txtCategoryId;
    public TableView<BookDto> bookTable;
    public TableColumn<BookDto,String> colCategoryId;
    public TableColumn<BookDto,String> colBookId;
    public TableColumn<BookDto,String> colTitle;
    public TableColumn<BookDto,String> colAuthor;
    public TableColumn<BookDto,String> colYear;
    public TableColumn<BookDto,String> colAvailable;
    public RadioButton txtYes;
    public RadioButton txtNo;

    private ToggleGroup availabilityGroup;
    private CategoryServiceImpl categoryService = new CategoryServiceImpl();
    private BookServiceImpl bookService = new BookServiceImpl();
    private ObservableList<BookDto> bookList= FXCollections.observableArrayList();

    public void initialize() {
        colCategoryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("publishYear"));
        colAvailable.setCellValueFactory(new PropertyValueFactory<>("available"));

        availabilityGroup = new ToggleGroup();
        txtYes.setToggleGroup(availabilityGroup);
        txtNo.setToggleGroup(availabilityGroup);

        loadBook();
        setNewCategoryId();
        setNewBookId();

        bookTable.setItems(bookList);
        bookTable.setOnMouseClicked(this::selectValue);
    }

    public void categorySearchOnAction(ActionEvent actionEvent) {
        try {
            String categoryId = txtCategoryId.getText();
            CategoryDto category = categoryService.get(categoryId);

            if (category != null) {
                txtCategoryShow.setText(category.getCategoryId() + " | " + category.getCategoryName());
            } else {
                txtCategoryShow.setText("No Category Found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addOnAction(ActionEvent actionEvent) {
        try {
            String categoryId = txtCategoryId.getText();
            CategoryDto category = categoryService.get(categoryId);

            if (txtCategoryId.getText().equalsIgnoreCase("BC-") || txtBookId.getText().equalsIgnoreCase("B-")) {
                new Alert(Alert.AlertType.WARNING, "Please Enter Category ID or Book ID..!").show();
                return;
            } else if (txtTitle.getText().trim().isEmpty() || txtAuthor.getText().trim().isEmpty() || txtYear.getValue() == null) {
                new Alert(Alert.AlertType.WARNING, "Please complete all details..!").show();
                return;
            } else if (!txtAuthor.getText().matches("^[^0-9]*$")) {
                new Alert(Alert.AlertType.WARNING, "Author name can't be a number..!", ButtonType.OK).show();
                return;
            }

            if (category == null) {
                new Alert(Alert.AlertType.ERROR, "Category does not exist", ButtonType.OK).showAndWait();
                return;
            }

            // Proceed with book addition
            LocalDate localDate = txtYear.getValue();
            String availability = txtYes.isSelected() ? "yes" : "no";

            BookDto book = new BookDto(
                    categoryId,
                    txtBookId.getText(),
                    txtTitle.getText(),
                    txtAuthor.getText(),
                    localDate,
                    availability
            );

            String result = bookService.save(book);

            if ("Success".equals(result)) {
                bookList.add(book);
                bookTable.setItems(bookList);
                clearFields();
                setNewCategoryId();
                setNewBookId();
                new Alert(Alert.AlertType.INFORMATION, "Book Added Successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to add book").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error in add book.Please check ID numbers..!").show();
        }
    }


    public void updateOnAction(ActionEvent actionEvent) {
        try {
            String categoryId = txtCategoryId.getText();
            CategoryDto category = categoryService.get(categoryId);

            if (txtCategoryId.getText().equalsIgnoreCase("BC-") || txtBookId.getText().equalsIgnoreCase("B-")) {
                new Alert(Alert.AlertType.WARNING, "Please Enter Category ID or Book ID..!").show();
                return;
            } else if (txtTitle.getText().trim().isEmpty() || txtAuthor.getText().trim().isEmpty() || txtYear.getValue() == null) {
                new Alert(Alert.AlertType.WARNING, "Please complete all details..!").show();
                return;
            } else if (!txtAuthor.getText().matches("^[^0-9]*$")) {
                new Alert(Alert.AlertType.WARNING, "Author name can't be a number..!", ButtonType.OK).show();
                return;
            }

            if (category == null) {
                new Alert(Alert.AlertType.ERROR, "Category does not exist", ButtonType.OK).showAndWait();
                return;
            }

            // Proceed with book addition
            LocalDate localDate = txtYear.getValue();
            String availability = txtYes.isSelected() ? "yes" : "no";

            BookDto book = new BookDto(
                    categoryId,
                    txtBookId.getText(),
                    txtTitle.getText(),
                    txtAuthor.getText(),
                    localDate,
                    availability
            );

            String result = bookService.update(book);

            if ("Success".equals(result)) {
                loadBook();
                clearFields();
                setNewCategoryId();
                setNewBookId();
                new Alert(Alert.AlertType.INFORMATION, "Book Updated Successfully").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Failed to update book").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error in update book..!").show();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {

        try {

            String result =bookService.delete(txtBookId.getText());
            if ("Success".equals(result)) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this book?...!",
                        ButtonType.YES,ButtonType.NO);

                Optional<ButtonType> buttonType = alert.showAndWait();

                if (buttonType.get() == ButtonType.YES) {
                    loadBook();
                    clearFields();
                    new Alert(Alert.AlertType.INFORMATION, "Book Deleted Successfully").showAndWait();
                }

            }else {
                new Alert(Alert.AlertType.WARNING, "Failed to delete book").show();
            }

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error in delete book..!").show();
        }

    }

    public void clearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void searchOnAction(ActionEvent actionEvent) {
        try {
            String bookId = txtSearch.getText().trim();
            if (!bookId.isEmpty()) {
                BookDto book = bookService.get(bookId);
                if (book != null) {
                    bookList.clear();
                    bookList.add(book);
                    bookTable.setItems(bookList);
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "No Book Found with ID: " + bookId).show();
                    loadBook();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Please enter a Book ID to search").show();
                loadBook();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred while searching for the book").show();
        }
    }

    private void loadBook() {
        try {
            ArrayList<BookDto> books = bookService.getAll();
            if (books != null && !books.isEmpty()) {
                bookList.setAll(books);
                bookTable.setItems(bookList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtCategoryId.clear();
        txtBookId.clear();
        txtTitle.clear();
        txtAuthor.clear();
        txtCategoryShow.clear();
        txtYear.setValue(null);
        availabilityGroup.selectToggle(null); // Clear radio button selection
        setNewCategoryId();
        setNewBookId();
    }

    private void selectValue(MouseEvent mouseEvent) {
        BookDto selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            txtCategoryId.setText(selectedBook.getCategoryId());
            txtBookId.setText(selectedBook.getBookId());
            txtTitle.setText(selectedBook.getTitle());
            txtAuthor.setText(selectedBook.getAuthor());
            txtYear.setValue(selectedBook.getPublishYear());
            // Set the radio button selection based on the availability value
            String availability = selectedBook.getAvailable();
            if ("yes".equalsIgnoreCase(availability)) {
                txtYes.setSelected(true);
            } else if ("no".equalsIgnoreCase(availability)) {
                txtNo.setSelected(true);
            } else {
                availabilityGroup.selectToggle(null); // Clear selection if value is invalid
            }
        }
    }

    private void setNewCategoryId() {
        String newCategoryId = "BC-";
        txtCategoryId.setText(newCategoryId);
    }
    private void setNewBookId() {
        String newBookId = "B-";
        txtBookId.setText(newBookId);
    }
}
