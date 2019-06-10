package DB;

import Model.Drone;
import Model.LogEntry;
import Model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Database {
    //region Fields
    private static Database instance;
    private String currentUser;
    Connection connection;
    //endregion

    //region Constructor
    private Database(){
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://192.168.99.100:3306/mydb", "user", "passme");
            System.out.println("Connection OK!");
        } catch (SQLException e) {
            System.out.println("Verbindung zur DB nicht möglich");
            e.printStackTrace();
        }
    }
    public static Database getInstance(){
        if(instance == null)instance = new Database();
        return instance;
    }
    //endregion

    //region GetterSetter
    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    //endregion

    public void addLogEntry(LogEntry le){
        String sql = "INSERT INTO log (droneID, userID, description, time_of_flight)" +
                "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, le.getDroneID());
            prep.setString(2, le.getUserID());
            prep.setString(3, le.getDescription());
            prep.setTimestamp(4, le.getTimeOfFlight());
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User u){
        //TODO
    }

    public void addDrone(Drone d){
        //TODO
    }

    public List<LogEntry> getLogEntriesOfDrone(int droneID){
        //TODO
        return null;
    }

    public String getUsersName(String id) {
        String sql = "SELECT name FROM user WHERE id LIKE '" + id + "'";
        try {
            PreparedStatement prep = connection.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public List<LogEntry> getLogEntries() {
        String sql = "SELECT id, droneID, userID, description, time_of_flight FROM log";
        List<LogEntry> entries = new LinkedList<>();
        try {
            PreparedStatement prep = connection.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                entries.add(new LogEntry(rs.getInt(1), rs.getInt(2),
                        rs.getString(3), rs.getString(4), rs.getTimestamp(5)));
            }
            return entries;

        } catch (SQLException e) {
            System.out.println("Fehler bei SQL Abfrage der Logs");
            e.printStackTrace();
        }
        return null;
    }

    public List<Drone> getDrones() {
        String sql = "SELECT id, name, description, available FROM drone";
        List<Drone> drones = new LinkedList<>();
        try {
            PreparedStatement prep = connection.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                drones.add(new Drone(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getBoolean(4)));
            }
            return drones;

        } catch (SQLException e) {
            System.out.println("Fehler bei SQL Abfrage der Drohnen");
            e.printStackTrace();
        }
        return null;
    }
}
