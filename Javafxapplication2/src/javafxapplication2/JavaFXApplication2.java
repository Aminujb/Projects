/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author AminujB
 */
public class JavaFXApplication2 extends Application {
    public static Connection con, cone;
    
    public static String url = "jdbc:sqlite:C:\\Users\\AminujB\\Documents\\"
          + "NetBeansProjects\\JavaFXApplication2\\test\\Database.db";
    
    public boolean works;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
       
        stage.setScene(scene);
        stage.setResizable(false);
        //stage.setMaxWidth(876);
        stage.show();
    }
    
    public static void connect(){
        
       
        try (Connection conn = DriverManager.getConnection(url)){
            
            //Create a connection to the database
            if (conn != null){
                System.out.println("Database Connection: Successful!");
            } else {
            }
       
        }catch(SQLException e){
            System.out.println("Database Connection: Failed!");
            System.out.println(e.getMessage());
        }
        
    }
    
    public static Connection getCon(String cstr){
        try{
            Class.forName("org.sqlite.JDBC");
            cone = DriverManager.getConnection("jdbc:sqlite:: resource: "+ cstr);
            
        }catch(ClassNotFoundException | SQLException ec){
            System.out.println(ec.getMessage());
        }
        return cone;
    }
    
    public static void createTables(){
        String sql1 = "CREATE TABLE IF NOT EXISTS Details("
                + "SN integer PRIMARY KEY,OwnerName text,IdentificationNumber text,"
                + "VehicleMake text,ChassisNumber text,EngineNumber text"
                + ",RegistrationDate date,LicencePlate text,"
                + "FancyPlate text);";
        
        String sql2 = "CREATE TABLE IF NOT EXISTS Vehicles(SN interger PRIMARY KEY,IdentificationNumber text,Manufacturer text,"
                + "ChassisNumber text,EngineNumber text,YearOfManufacture text,Colour text, Model text,"
                + "VehicleCategory text,EngineCapacity text,FuelType text,VehicleType text);";
        
        String sql3 = "CREATE TABLE IF NOT EXISTS Owners(SN integer PRIMARY KEY,"
                + "OwnerName text,IdentificationNumber text,"
                + "PhoneNumber integer,address text,DriverLicence text,BearerName text);";
                
        
        try (Connection conne = DriverManager.getConnection(url); 
                Statement stmt = conne.createStatement()){
            stmt.execute(sql1);
            stmt.execute(sql2);
            stmt.execute(sql3);
            System.out.println("Table created successfully.");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        connect();
        createTables();
        launch(args);
    }
    
}
