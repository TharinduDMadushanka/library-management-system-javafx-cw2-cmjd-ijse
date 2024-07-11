package edu.ijse.UIController;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class BooksFormController {
    public AnchorPane bookContext;
    public TextField txtBookId;
    public TextField txtTitle;
    public TextField txtAuthor;
    public TextField txtCategoryShow;
    public DatePicker txtYear;
    public TextField txtSearch;
    public TextField txtCategoryId;
    public TableView bookTable;
    public TableColumn colCategoryId;
    public TableColumn colBookId;
    public TableColumn colTitle;
    public TableColumn colAuthor;
    public TableColumn colYear;
    public TableColumn colAvailable;

    public void categorySearchOnAction(ActionEvent actionEvent) {
    }

    public void addOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }

    public void clearOnAction(ActionEvent actionEvent) {
    }

    public void searchOnAction(ActionEvent actionEvent) {

    }
}
