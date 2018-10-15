/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.harjoituskaksi;

/**
 *
 * @author Jere
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class VastausDao implements Dao<Vastaus, Integer> {

    private Database database;

    public VastausDao(Database database) {
        this.database = database;
    }

    @Override
    public Vastaus findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Vastaus WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        Integer id = rs.getInt("id");
        Integer kysy = rs.getInt("kysymys_id");
        String aih = rs.getString("vastausteksti");
        String oikeus = rs.getString("oikein");
        Boolean oikeu = false;
        if (oikeus.equals("TRUE")){
            oikeu = true;
        } else {
            oikeu = false;
        }

        Vastaus o = new Vastaus(id, kysy, aih, oikeu);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<Vastaus> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Vastaus");

        ResultSet rs = stmt.executeQuery();
        List<Vastaus> vastaukset = new ArrayList<>();
        while (rs.next()) {
            
            Integer id = rs.getInt("id");
            Integer kysy = rs.getInt("kysymys_id");
            String aih = rs.getString("vastausteksti");
            String oikeus = rs.getString("oikein");
            Boolean oikeu = false;
        if (oikeus.equals("TRUE")){
            oikeu = true;
        }

        Vastaus o = new Vastaus(id, kysy, aih, oikeu);
        vastaukset.add(o);
        }

        rs.close();
        stmt.close();
        connection.close();

        return vastaukset;
    }
    public List<Vastaus> findVastaukset(Integer key) throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Vastaus WHERE kysymys_id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        List<Vastaus> vastaukset = new ArrayList<>();
        while (rs.next()) {
            
            Integer id = rs.getInt("id");
            Integer kysy = rs.getInt("kysymys_id");
            String aih = rs.getString("vastausteksti");
            String oikeus = rs.getString("oikein");
            Boolean oikeu = false;
        if (oikeus.equals("TRUE")){
            oikeu = true;
        }

        Vastaus o = new Vastaus(id, kysy, aih, oikeu);
        vastaukset.add(o);
        }

        rs.close();
        stmt.close();
        connection.close();

        return vastaukset;
    }
    public void add(Integer kysymys_id, String vastaus, String oikeus) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Vastaus (kysymys_id, vastausteksti, oikein) VALUES (?, ?, ?);");
        stmt.setObject(1, kysymys_id);
        stmt.setObject(2, vastaus);
        stmt.setObject(3, oikeus);
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Vastaus WHERE id = ?");
        stmt.setObject(1, key);

        stmt.executeUpdate();

        stmt.close();
        connection.close();

        
        
    }
    public void deleteId(Integer key) throws SQLException {
        // ei toteutettu
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Vastaus WHERE kysymys_id = ?");
        stmt.setObject(1, key);

        stmt.executeUpdate();

        stmt.close();
        connection.close();

        
        
    }

}