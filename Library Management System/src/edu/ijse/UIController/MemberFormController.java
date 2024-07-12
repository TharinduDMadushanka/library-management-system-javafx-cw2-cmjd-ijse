package edu.ijse.UIController;

import edu.ijse.dto.MemberDto;
import edu.ijse.service.custom.MemberService;
import edu.ijse.service.custom.impl.MemberServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class MemberFormController {
    public AnchorPane memberContext;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtMobile;
    public TextField txtEmail;
    public TextField txtAge;
    public DatePicker txtDob;
    public TextField txtGender;
    public ComboBox cmbGender;
    public TextField txtId;
    public TableView<MemberDto> memberTable;
    public TableColumn<MemberDto,String> colId;
    public TableColumn<MemberDto,String> colName;
    public TableColumn<MemberDto,String> colAddress;
    public TableColumn<MemberDto,String> colMobile;
    public TableColumn<MemberDto,String> colEmail;
    public TableColumn<MemberDto,String> colDob;
    public TableColumn<MemberDto,String> colAge;
    public TableColumn<MemberDto,String> colGender;


    private MemberService memberService = new MemberServiceImpl();
    private ObservableList<MemberDto> memberList = FXCollections.observableArrayList();


    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        loadMember();

        memberTable.setItems(memberList);
        memberTable.setOnMouseClicked(this::selectMember);

        ObservableList<String> gender = FXCollections.observableArrayList("Male", "Female");
        cmbGender.setItems(gender);
        cmbGender.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtGender.setText(newValue.toString());
        });
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

    private void loadMember(){
        try {

            ArrayList<MemberDto> members = memberService.getAll();
            if(members != null){
                memberList.setAll(members);
                memberTable.setItems(memberList);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void clearFields(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtMobile.clear();
        txtEmail.clear();
        txtAge.clear();
        txtGender.clear();
        cmbGender.getSelectionModel().clearSelection();
        txtDob.setValue(null);
    }

    private void selectMember(MouseEvent mouseEvent){
        MemberDto selectedMember = memberTable.getSelectionModel().getSelectedItem();
        if(selectedMember != null){
            txtId.setText(selectedMember.getMemberId());
            txtName.setText(selectedMember.getMemberName());
            txtAddress.setText(selectedMember.getAddress());
            txtMobile.setText(selectedMember.getMobile());
            txtEmail.setText(selectedMember.getEmail());
            txtAge.setText(String.valueOf(selectedMember.getAge()));
            txtDob.setValue(selectedMember.getDob());
            cmbGender.getSelectionModel().select(selectedMember.getGender());
        }
    }
}
