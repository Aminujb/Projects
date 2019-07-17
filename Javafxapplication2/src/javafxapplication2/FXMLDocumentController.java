/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import static javafxapplication2.JavaFXApplication2.url;
import javax.swing.JOptionPane;

/**
 *
 * @author AminujB
 */
public class FXMLDocumentController implements Initializable {
    HashMap<String,String> lga = new HashMap<>();
    String previous,neww;
    String licencePlate;
    Random rand = new Random();
    String num;
    
    private final ObservableList<GeneralDetails> GeneralData= FXCollections.observableArrayList();
    
    @FXML
    private TextField ownerName;

    @FXML
    private DatePicker regDate;

    @FXML
    private TextField carManufacturer;

    @FXML
    private TextField carModel;
    
    @FXML
    private ChoiceBox localgov;

    @FXML
    private ChoiceBox engCapacity;

    @FXML
    private ChoiceBox vehicleType;

    @FXML
    private TextField mobileNumber;
    
    @FXML
    private TextField vehicleIDnum;

    @FXML
    private TextField driverLicence;

    @FXML
    private TextField idNumber;

    @FXML
    private Label licencePlateView;

    @FXML
    private ColorPicker colour;

    @FXML
    private TextField bearerName;

    @FXML
    private ChoiceBox manufactureYear;
    
    @FXML
    public CheckBox checkLP;
    
    @FXML
    private TextArea address;

    @FXML
    private ChoiceBox vCategory;

    @FXML
    private ChoiceBox selectTableView;
    
     @FXML
    private ChoiceBox fuelType;
    
    @FXML
    private TableView<GeneralDetails> tableView;
    
    @FXML
    private TabPane tabPane;
       
    @FXML
    private Pane platePane;
    
    @FXML
    private TextField plateName;
    
    @FXML
    private Button delete;
    
    @FXML
    private Button save;
    
    @FXML
    private TextField engNumber;
    
    @FXML
    private Button viewTable;
    
    private boolean exist = false;


    @FXML
    private void handleButtonAction(ActionEvent event) {
        exist = false;
        String name = this.ownerName.getText();
        LocalDate date = this.regDate.getValue();
        String manufacturer = this.carManufacturer.getText();
        String model = this.carModel.getText();
        String phoneNumber = this.mobileNumber.getText();
        String vehicleID = this.vehicleIDnum.getText();
        String dLicence = this.driverLicence.getText();
        String idNum = this.idNumber.getText();
        String bName = this.bearerName.getText();
        String manYear = (String) this.manufactureYear.getSelectionModel().getSelectedItem();
        String pName = this.plateName.getText();
        String engNum = this.engNumber.getText();
        String ownerAddress = this.address.getText();
        String vehicleCat = (String) this.vCategory.getSelectionModel().getSelectedItem();
        String engineCapacity = (String) this.engCapacity.getSelectionModel().getSelectedItem();
        String fType = (String) this.fuelType.getSelectionModel().getSelectedItem();
        String vType = (String) this.vehicleType.getSelectionModel().getSelectedItem();
        
        
        Object[] id = {name,manufacturer,model,phoneNumber,vehicleID,dLicence,
        idNum,bName,manYear};
        boolean isFieldEmpty = false;
        
        for (Object id1 : id) {

            if (id1.equals("")) {
                isFieldEmpty = true;
                break;
            }   
        }
        
        if (checkLP.isSelected()){
            if ("".equals(pName)){
                isFieldEmpty = true;
            }
        }
        
        if (isFieldEmpty != false){
            JOptionPane.showMessageDialog(null, "No field Can be empty");
        }
        else {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to save?", "SAVE", JOptionPane.YES_NO_OPTION);
            
            String sql = "SELECT ChassisNumber FROM Details";
        
            try (Connection conn = this.connect();
                 Statement stmt  = conn.createStatement();
                 ResultSet rs    = stmt.executeQuery(sql)){

                // loop through the result set
                while (rs.next()) {
                    if (vehicleID.equals(rs.getString("ChassisNumber"))){
                        exist = true;
                        break;
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        if (exist == false){    
            switch(result){
                case 0:
                    
                    String col = String.valueOf( this.colour.getValue());
                    
                    String llga = lga.get((String) this.localgov.getSelectionModel().getSelectedItem());
                    
                    licencePlate = generateplateNum(llga);
                    
                    neww = vehicleID+" "+manufacturer+" "+model+" "+llga;
                    if (neww == null ? previous != null : !neww.equals(previous)){
                        
                        this.licencePlateView.setText(pName);
                        String sql1 = "INSERT INTO Details(OwnerName,IdentificationNumber,VehicleMake,"
                                + "ChassisNumber,EngineNumber,RegistrationDate,LicencePlate,FancyPlate)VALUES(?,?,?,?,?,?,?,?)";
                        
                        try(Connection conn = this.connect();
                                PreparedStatement pstmt = conn.prepareStatement(sql1);)
                        {
                            pstmt.setString(1, name);
                            pstmt.setString(2, idNum);
                            pstmt.setString(3, model);
                            pstmt.setString(4, vehicleID);
                            pstmt.setString(5, engNum);
                            pstmt.setString(6, String.valueOf(date));
                            pstmt.setString(7, licencePlate);
                            String fancyPlate = checkLP.isSelected() ? pName : "NA";
                            pstmt.setString(8, fancyPlate);
                            
                            pstmt.executeUpdate();
                            
                            
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
                       
                        
                        String sql2 = "INSERT INTO Vehicles(IdentificationNumber,Manufacturer,"
                                + "ChassisNumber,EngineNumber,YearOfManufacture,Colour,Model,VehicleCategory,EngineCapacity,FuelType,"
                                + "VehicleType)"
                                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
                        
                        try(Connection conn = this.connect();
                                PreparedStatement pstmt = conn.prepareStatement(sql2);)
                        {
                            pstmt.setString(1, manufacturer);
                            pstmt.setString(2, vehicleID);
                            pstmt.setString(3, engNum);
                            pstmt.setString(4, manYear);
                            pstmt.setString(5, col);
                            pstmt.setString(6, model);
                            pstmt.setString(7, vehicleCat);
                            pstmt.setString(8, engineCapacity);
                            pstmt.setString(9, fType);
                            pstmt.setString(10, vType);
                            
                            pstmt.executeUpdate();
                            
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
                        
                        String sql3 = "INSERT INTO Owners(OwnerName,"
                                + "IdentificationNumber,PhoneNumber,address,DriverLicence,"
                                + "BearerName) VALUES(?,?,?,?,?,?)";
                        
                        try(Connection conn = this.connect();
                                PreparedStatement pstmt = conn.prepareStatement(sql3);)
                        {
                            pstmt.setString(1, name);
                            pstmt.setString(2, idNum);
                            pstmt.setString(3, phoneNumber);
                            pstmt.setString(4, ownerAddress);
                            pstmt.setString(5, dLicence);
                            pstmt.setString(6, bName);
                            
                            pstmt.executeUpdate();
                            
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
                        
                        JOptionPane.showMessageDialog(null, "Added Successfully");
                        platePane.setVisible(true);
                        if (checkLP.isSelected()){
                            licencePlateView.setText(pName);
                        }else {
                            licencePlateView.setText(licencePlate);
                        }
                        
                        previous = neww;
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "This data has just been saved");
                        
                    }
                    
                    break;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Vehicle is already registered!");
        }
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        regDate.setValue(LocalDate.now());
        platePane.setVisible(false);
        plateName.setDisable(true);
        
        String localgovs[] = {"Agege","Ajeromi-Ifelodun","Alimosho","Amuwo-Odofin"
                ,"Apapa","Badagry","Epe","Eti Osa","Ibeju-Lekki","Ifako-Ijaiye","Ikeja"
                ,"Ikorodu","Kosofe","Lagos Island","Lagos Mainland","Mushin","Ojo"
                ,"Oshodi-Isolo","Somolu","Surulere"};
        String locGovCodes[] ={"GGE","AGL","KTU","FST","APP","BDG","EPE","EKY","AKD"
                ,"FKJ","KJA","KRD","KSF","AAA","LND","MUS","JJJ","LSD","SMK","LSR"};
        
        for (int i =0; i <localgovs.length; i++){
            lga.put(localgovs[i],locGovCodes[i]);
        }

        localgov.getItems().removeAll(localgov.getItems());
        Set set = lga.entrySet();
        Iterator iter = set.iterator();
        
        
        while (iter.hasNext()){
            Map.Entry mk = (Map.Entry)iter.next();
            localgov.getItems().add(mk.getKey());
        }
        localgov.getSelectionModel().select("Agege");
        localgov.setTooltip(new Tooltip("Select Local Government Area"));
        
        manufactureYear.setItems(FXCollections.observableArrayList("2001",
                "2002","2003","2004","2005","2006","2007","2008","2009","2011",
                "2012","2013","2014","2015","2016","2017","2018"));
        
        vCategory.setItems(FXCollections.observableArrayList("M [Passenger Vehicles]",
                "N [Goods Vehicles]","L [Motorbikes etc]","T [Tractors]","O [Trailers]"
                     ,"S [Special purpose vehicles]","G [Off Road Vehicles]"));
        
        engCapacity.setItems(FXCollections.observableArrayList("up to 1000cc",
                "1000cc to 1500cc","1500cc to 1800cc","1800cc to 2500cc","Above 2500cc", "NA"));
        
        fuelType.setItems(FXCollections.observableArrayList("Diesel",
                "Petrol","NA"));
        
        vehicleType.setItems(FXCollections.observableArrayList("Sport Utility Vehicle",
                "Wagon","Sedan","Luxury","Minivan","Covertible","Coupe",
                "Crossover","Hatchback","Hybrid Electric"));
        
        selectTableView.setItems(FXCollections.observableArrayList("Owner",
                "Vehicles","General Details"));
        
        selectTableView.getSelectionModel().select("General Details");
        //loadGeneralDetails();
        
        tabPane.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) -> {
            if(newValue.getId().equals("viewDatabase")){
                
                //refresh Table
                tableView.getItems().clear();
                loadGeneralDetails();
            }else{
                newValue.getGraphic();
            }
        });
        
        
        checkLP.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue){
                plateName.setDisable(false);
            }else{
                plateName.setDisable(true); 
            }
        });
        
      selectTableView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
      
          @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
            
                if (number2.equals(0)){
                    
                    tableView.getColumns().clear();
                    loadOwnerTable();
                }
                
                if (number2.equals(1)){
                    
                    tableView.getColumns().clear();
                    loadVehiclesTable();
                }
                
                if (number2.equals(2)){
                    
                    tableView.getColumns().clear();
                    loadGeneralDetails();
                }
            }
    });
        
        delete.setOnAction((ActionEvent event) -> {
            String selectedTable = (String) this.selectTableView.getSelectionModel().getSelectedItem();
            if ("General Details".equals(selectedTable)){
                try{
                String pos = tableView.getSelectionModel().getSelectedItem().getIdentificationNumber();
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you "
                        + "want to delete?", "DELETE", JOptionPane.YES_NO_OPTION);
                if (result == 0){
                    delete(pos);
                    JOptionPane.showMessageDialog(null, "Deleted Successfully");
                    tableView.getItems().clear();
                    loadGeneralDetails();
                }
                }catch(NullPointerException npe){
                    JOptionPane.showMessageDialog(null, "Select a row to delete");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Unable to delete from this view.");
            }
            
        });
        
        delete.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        save.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        
    }
    
    private Connection connect(){
        String url = "jdbc:sqlite:C:\\Users\\AminujB\\Documents\\"
            + "NetBeansProjects\\JavaFXApplication2\\test\\Database.db";
        
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(url);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        
        return conn;
        
    }
    
    private String generateplateNum(String lga){
        
        num = String.valueOf( 100+ rand.nextInt(899));
        
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        char let = 0;
        String letters ="";
        for (int i =0; i <2; i++){
            let = abc.charAt(rand.nextInt(abc.length()));
            letters += (String.valueOf(let));
        }
        String result = lga +"-"+ num + letters;
        
        String sql = "SELECT LicencePlate FROM Details;";

        try(    Statement stmt = connect().createStatement();
                ResultSet rs = stmt.executeQuery(sql)){
            
            while (rs.next()){
                if (result.equals(rs.getString("LicencePlate"))){
                    generateplateNum(lga);
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return result;
    }
    
    
    private void delete(String id){
        String sql = "DELETE FROM Details WHERE IdentificationNumber = '"+id+"';";
        String sql1 = "DELETE FROM Vehicles WHERE IdentificationNumber = '"+id+"';";
        String sql2 = "DELETE FROM Owners WHERE IdentificationNumber = '"+id+"';";
        
        try (Connection conne = DriverManager.getConnection(url); 
                Statement stmt = conne.createStatement()){
            stmt.execute(sql);
            stmt.execute(sql1);
            stmt.execute(sql2);
            System.out.println("Deleted successfully.");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private void loadOwnerTable() {
       TableColumn sn = new TableColumn();
       sn.setText("Sn");
       sn.setSortable(false);
       sn.setCellValueFactory(new PropertyValueFactory<>("Snn"));
       sn.setPrefWidth(50);
       //tableView.getColumns().add(sn);
       
       TableColumn name = new TableColumn();
       name.setText("Name");
       name.setSortable(false);
       name.setCellValueFactory(new PropertyValueFactory<>("ownerName"));
       name.setPrefWidth(200);
       tableView.getColumns().add(name);
       
       TableColumn id = new TableColumn();
       id.setText("Identification Number");
       id.setSortable(false);
       id.setCellValueFactory(new PropertyValueFactory<>("identificationNumber"));
       id.setPrefWidth(150);
       tableView.getColumns().add(id);
       
       TableColumn phone = new TableColumn();
       phone.setText("Phone Number");
       phone.setSortable(false);
       phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
       phone.setPrefWidth(150);
       tableView.getColumns().add(phone);
       
       TableColumn location = new TableColumn();
       location.setText("Address");
       location.setSortable(false);
       location.setCellValueFactory(new PropertyValueFactory<>("address"));
       location.setPrefWidth(250);
       tableView.getColumns().add(location);
       
       TableColumn dl = new TableColumn();
       dl.setText("Driver Licence");
       dl.setSortable(false);
       dl.setCellValueFactory(new PropertyValueFactory<>("driverLicence"));
       dl.setPrefWidth(150);
       tableView.getColumns().add(dl);
       
       TableColumn bn = new TableColumn();
       bn.setText("Bearer's Name");
       bn.setSortable(false);
       bn.setCellValueFactory(new PropertyValueFactory<>("bearerName"));
       bn.setPrefWidth(150);
       tableView.getColumns().add(bn);
       
       tableView.getItems().clear();
       
       String sql = "SELECT * FROM Owners;";

        try(    Statement stmt = connect().createStatement();
                ResultSet rs = stmt.executeQuery(sql)){
           
             while (rs.next()){
                    GeneralData.addAll(new GeneralDetails(
                               rs.getInt("Sn"),
                               rs.getString("ownerName"),    
                               rs.getString("identificationNumber"),
                               rs.getString("phoneNumber"),
                               rs.getString("address"),
                               rs.getString("driverLicence"),
                               rs.getString("bearerName")
                        ));
                            tableView.setItems(GeneralData);
                        }
                        
                        stmt.close();
                        rs.close();

        }catch(SQLException e){
                        System.out.println(e.getMessage());
        }
    }

    private void loadVehiclesTable() {
       TableColumn sn = new TableColumn();
       sn.setText("Sn");
       sn.setSortable(false);
       sn.setCellValueFactory(new PropertyValueFactory<>("Snn"));
       sn.setPrefWidth(50);
       //tableView.getColumns().add(sn);
       
       TableColumn id = new TableColumn();
       id.setText("Identification Number");
       id.setSortable(false);
       id.setCellValueFactory(new PropertyValueFactory<>("identificationNumber"));
       id.setPrefWidth(150);
       tableView.getColumns().add(id);
       
       TableColumn make = new TableColumn();
       make.setText("Vehicle Manufacturer");
       make.setSortable(false);
       make.setCellValueFactory(new PropertyValueFactory<>("make"));
       make.setPrefWidth(200);
       tableView.getColumns().add(make);
       
       TableColumn chassis = new TableColumn();
       chassis.setText("Chassis Number");
       chassis.setSortable(false);
       chassis.setCellValueFactory(new PropertyValueFactory<>("chassisNumber"));
       chassis.setPrefWidth(150);
       tableView.getColumns().add(chassis);
       
       TableColumn eNum = new TableColumn();
       eNum.setText("Engine Number");
       eNum.setSortable(false);
       eNum.setCellValueFactory(new PropertyValueFactory<>("engineNumber"));
       eNum.setPrefWidth(150);
       tableView.getColumns().add(eNum);
       
       TableColumn yearMan = new TableColumn();
       yearMan.setText("Year of Manufacture");
       yearMan.setSortable(false);
       yearMan.setCellValueFactory(new PropertyValueFactory<>("yom"));
       yearMan.setPrefWidth(150);
       tableView.getColumns().add(yearMan);
       
       TableColumn vColour = new TableColumn();
       vColour.setText("Colour");
       vColour.setSortable(false);
       vColour.setCellValueFactory(new PropertyValueFactory<>("colour"));
       vColour.setPrefWidth(150);
       tableView.getColumns().add(vColour);
       
       TableColumn model = new TableColumn();
       model.setText("Model");
       model.setSortable(false);
       model.setCellValueFactory(new PropertyValueFactory<>("model"));
       model.setPrefWidth(150);
       tableView.getColumns().add(model);
       
       TableColumn vCat = new TableColumn();
       vCat.setText("Category");
       vCat.setSortable(false);
       vCat.setCellValueFactory(new PropertyValueFactory<>("vehicleCategory"));
       vCat.setPrefWidth(150);
       tableView.getColumns().add(vCat);
       
       TableColumn eCap = new TableColumn();
       eCap.setText("Engine Capacity");
       eCap.setSortable(false);
       eCap.setCellValueFactory(new PropertyValueFactory<>("engineCapacity"));
       eCap.setPrefWidth(150);
       tableView.getColumns().add(eCap);
       
       TableColumn fType = new TableColumn();
       fType.setText("Fuel Type");
       fType.setSortable(false);
       fType.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
       fType.setPrefWidth(150);
       tableView.getColumns().add(fType);
       
       TableColumn vType = new TableColumn();
       vType.setText("Vehicle Type");
       vType.setSortable(false);
       vType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
       vType.setPrefWidth(150);
       tableView.getColumns().add(vType);
       
       tableView.getItems().clear();
       String sql = "SELECT * FROM Vehicles;";

        try(    Statement stmt = connect().createStatement();
                ResultSet rs = stmt.executeQuery(sql)){
           
             while (rs.next()){
                    GeneralData.addAll(new GeneralDetails(
                               rs.getInt("Sn"),
                               rs.getString("identificationNumber"),
                               rs.getString("Manufacturer"),    
                               rs.getString("ChassisNumber"),
                               rs.getString("EngineNumber"),
                               rs.getString("YearOfManufacture"),
                               rs.getString("Colour"),
                               rs.getString("Model"),
                               rs.getString("VehicleCategory"),
                               rs.getString("EngineCapacity"),
                               rs.getString("FuelType"),
                               rs.getString("VehicleType")
                        ));
                            tableView.setItems(GeneralData);
                        }
                        
                        stmt.close();
                        rs.close();

        }catch(SQLException e){
                        System.out.println(e.getMessage());
        }
    }

    private void loadGeneralDetails() {
       
       TableColumn sn = new TableColumn();
       sn.setText("Sn");
       sn.setSortable(false);
       sn.setCellValueFactory(new PropertyValueFactory<>("Snn"));
       sn.setPrefWidth(50);
       //tableView.getColumns().add(sn);
       
       TableColumn name = new TableColumn();
       name.setText("Name");
       name.setSortable(false);
       name.setCellValueFactory(new PropertyValueFactory<>("ownerName"));
       name.setPrefWidth(200);
       tableView.getColumns().add(name);
       
       TableColumn id = new TableColumn();
       id.setText("Identification Number");
       id.setSortable(false);
       id.setCellValueFactory(new PropertyValueFactory<>("identificationNumber"));
       id.setPrefWidth(150);
       tableView.getColumns().add(id);
       
       TableColumn make = new TableColumn();
       make.setText("Vehicle Manufacturer");
       make.setSortable(false);
       make.setCellValueFactory(new PropertyValueFactory<>("make"));
       make.setPrefWidth(200);
       tableView.getColumns().add(make);
       
       TableColumn chassis = new TableColumn();
       chassis.setText("Chassis Number");
       chassis.setSortable(false);
       chassis.setCellValueFactory(new PropertyValueFactory<>("chassisNumber"));
       chassis.setPrefWidth(150);
       tableView.getColumns().add(chassis);
       
       TableColumn eNum = new TableColumn();
       eNum.setText("Engine Number");
       eNum.setSortable(false);
       eNum.setCellValueFactory(new PropertyValueFactory<>("engineNumber"));
       eNum.setPrefWidth(150);
       tableView.getColumns().add(eNum);
       
       TableColumn dor = new TableColumn();
       dor.setText("Date Of Registration");
       dor.setSortable(false);
       dor.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
       dor.setPrefWidth(150);
       tableView.getColumns().add(dor);
       
       TableColumn lp = new TableColumn();
       lp.setText("Licence Plate");
       lp.setSortable(false);
       lp.setCellValueFactory(new PropertyValueFactory<>("licencePlate"));
       lp.setPrefWidth(150);
       tableView.getColumns().add(lp);
       
       TableColumn fp = new TableColumn();
       fp.setText("Fancy Plate");
       fp.setSortable(false);
       fp.setCellValueFactory(new PropertyValueFactory<>("fancyPlate"));
       fp.setPrefWidth(150);
       tableView.getColumns().add(fp);
       
       tableView.getItems().clear();
       
       String sql = "SELECT * FROM Details;";
        
        try(    Statement stmt = connect().createStatement();
                ResultSet rs = stmt.executeQuery(sql)){

                        
             while (rs.next()){
                    GeneralData.addAll(new GeneralDetails(
                               rs.getInt("SN"),
                               rs.getString("ownerName"),    
                               rs.getString("identificationNumber"),
                               rs.getString("vehicleMake"),
                               rs.getString("chassisNumber"),
                               rs.getString("engineNumber"),
                               rs.getString("registrationDate"),
                               rs.getString("licencePlate"),
                               rs.getString("fancyPlate")
                        ));
                            tableView.setItems(GeneralData);
                        }
                        
                        stmt.close();
                        rs.close();

        }catch(SQLException e){
                        System.out.println(e.getMessage());
        }
    }
}