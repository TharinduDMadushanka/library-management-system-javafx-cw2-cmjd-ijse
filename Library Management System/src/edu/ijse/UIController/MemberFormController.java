package edu.ijse.UIController;

import edu.ijse.dto.MemberDto;
import edu.ijse.service.custom.MemberService;
import edu.ijse.service.custom.impl.MemberServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
    public TextField txtSearch;

    public TableView<MemberDto> memberTable;
    public TableColumn<MemberDto, String> colId;
    public TableColumn<MemberDto, String> colName;
    public TableColumn<MemberDto, String> colAddress;

//    @FXML
//    public TableColumn<MemberDto, String> colMobile;


    @FXML
    private TableColumn<MemberDto, String> colMobile;

    public TableColumn<MemberDto, String> colEmail;
    public TableColumn<MemberDto, String> colDob;
    public TableColumn<MemberDto, String> colAge;
    public TableColumn<MemberDto, String> colGender;


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
        setMemberId();

        memberTable.setItems(memberList);
        memberTable.setOnMouseClicked(this::selectMember);

        ObservableList<String> gender = FXCollections.observableArrayList("Male", "Female");
        cmbGender.setItems(gender);
        cmbGender.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtGender.setText(newValue.toString());
        });
    }


    public void addOnAction(ActionEvent actionEvent) {
        try {

            if (isValidField()) {

                LocalDate dob = txtDob.getValue();

                MemberDto member = new MemberDto(
                        txtId.getText(),
                        txtName.getText(),
                        txtAddress.getText(),
                        txtMobile.getText(),
                        txtEmail.getText(),
                        Integer.parseInt(txtAge.getText()),
                        dob,
                        txtGender.getText()
                );


                String result = memberService.save(member);
                if ("Success".equals(result)) {
                    memberList.add(member);
                    memberTable.refresh();
                    new Alert(Alert.AlertType.INFORMATION, "Member Added Successfully").show();
                    clearFields();
                    setMemberId();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.INFORMATION, "Member save failed..!").show();
        }

    }

    public void updateOnAction(ActionEvent actionEvent) {

        try {

            if (isValidField()) {
                LocalDate dob = txtDob.getValue();

                MemberDto member = new MemberDto(
                        txtId.getText(),
                        txtName.getText(),
                        txtAddress.getText(),
                        txtMobile.getText(),
                        txtEmail.getText(),
                        Integer.parseInt(txtAge.getText()),
                        dob,
                        txtGender.getText()
                );


                String result = memberService.update(member);
                if ("Success".equals(result)) {
                    loadMember();
                    new Alert(Alert.AlertType.INFORMATION, "Member Updated Successfully").show();
                    clearFields();
                    setMemberId();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.INFORMATION, "Member update failed..!").show();
        }

    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {

            String result = memberService.delete(id);
            if ("Success".equals(result)) {
                loadMember();
                new Alert(Alert.AlertType.INFORMATION, "Member Deleted Successfully").show();
                clearFields();
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.INFORMATION, "Member delete failed..!").show();
        }

    }

    public void clearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void searchOnAction(ActionEvent actionEvent) {

        try {

            String memberId = txtSearch.getText().trim();

            if (!memberId.isEmpty()) {
                MemberDto member = memberService.get(memberId);
                if (member != null) {
                    memberList.clear();
                    memberList.add(member);
                    memberTable.setItems(memberList);
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "No member found with ID " + memberId).show();
                    loadMember();
                }
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Please enter a Member ID to search").show();
                loadMember();
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.INFORMATION, "Member search failed..!").show();
        }

    }

    private void loadMember() {
        try {

            ArrayList<MemberDto> members = memberService.getAll();
            if (members != null) {
                memberList.setAll(members);
//                memberList.stream().forEach(memberDto -> {
//                    System.out.println(memberDto.getMobile());
//                });
                memberTable.setItems(memberList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        if (txtId != null) txtId.clear();
        if (txtName != null) txtName.clear();
        if (txtAddress != null) txtAddress.clear();
        if (txtMobile != null) txtMobile.clear();
        if (txtEmail != null) txtEmail.clear();
        if (txtAge != null) txtAge.clear();
        if (txtDob != null) txtDob.setValue(null);
        if (txtGender != null) txtGender.clear();
        setMemberId();
    }

    private void selectMember(MouseEvent mouseEvent) {
        MemberDto selectedMember = memberTable.getSelectionModel().getSelectedItem();
        if (selectedMember != null) {
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

    private void setMemberId() {
        String memberId = "M-";
        txtId.setText(memberId);
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        return emailPattern.matcher(email).matches();
    }

    private boolean isValidField() {
        boolean checkEmail = isValidEmail(txtEmail.getText());

        if (txtId.getText().equalsIgnoreCase("M-")) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid ID number..!").show();
            return false;
        } else if (txtName.getText().trim().isEmpty() || txtAddress.getText().trim().isEmpty() || txtMobile.getText().trim().isEmpty()
                || txtAge.getText().trim().isEmpty() || txtDob.getValue() == null || cmbGender == null) {
            new Alert(Alert.AlertType.WARNING, "Please complete all details..!").show();
            return false;
        } else if (!checkEmail) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid email address.").show();
            return false;
        } else if (!txtName.getText().matches("^[^0-9]*$")) {
            new Alert(Alert.AlertType.WARNING, "Member name can't be a number..!").show();
            return false;
        } else if (!txtMobile.getText().matches("^0[7][01245678][0-9]{7}$")) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid mobile number.").show();
            return false;
        } else if (!txtAge.getText().matches("^[0-9]+$")) {
            new Alert(Alert.AlertType.WARNING, "Please enter valid age..!..!").show();
            return false;
        }
        return true;
    }
}