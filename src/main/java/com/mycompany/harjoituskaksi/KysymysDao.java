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


public class KysymysDao implements Dao<Kysymys, Integer> {

    private Database database;

    public KysymysDao(Database database) {
        this.database = database;
    }

    @Override
    public Kysymys findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Kysymys WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        Integer id = rs.getInt("id");
        String kurs = rs.getString("kurssi");
        String aih = rs.getString("aihe");
        String kysy = rs.getString("kysymysteksti");

        Kysymys o = new Kysymys(id, kurs, aih, kysy);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }
   public String getTeksti(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT kysymysteksti FROM Kysymys WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        
        String kysy = rs.getString("kysymysteksti");


        rs.close();
        stmt.close();
        connection.close();

        return kysy;
    }

    @Override
    public List<Kysymys> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Kysymys");

        ResultSet rs = stmt.executeQuery();
        List<Kysymys> kysymykset = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String kurs = rs.getString("kurssi");
            String aih = rs.getString("aihe");
            String kysy = rs.getString("kysymysteksti");


            kysymykset.add(new Kysymys(id, kurs, aih, kysy));
        }

        rs.close();
        stmt.close();
        connection.close();

        return kysymykset;
    }

    public void add(String kurssi, String aihe, String kysymys) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Kysymys (kurssi, aihe, kysymysteksti) VALUES (?, ?, ?);");
        stmt.setObject(1, kurssi);
        stmt.setObject(2, aihe);
        stmt.setObject(3, kysymys);
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }
    
    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Kysymys WHERE id = ?");
        stmt.setObject(1, key);

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

}