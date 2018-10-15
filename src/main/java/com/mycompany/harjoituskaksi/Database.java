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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    public void init() {
        List<String> lauseet = sqliteLauseet();

        // "try with resources" sulkee resurssin automaattisesti lopuksi
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String lause : lauseet) {
                System.out.println("Running command >> " + lause);
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
            System.out.println("Error >> " + t.getMessage());
        }
    }
        private List<String> sqliteLauseet() {
        ArrayList<String> lista = new ArrayList<>();

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
        lista.add("DROP TABLE Kysymys;");
        lista.add("DROP TABLE Vastaus;");
        lista.add("CREATE TABLE Kysymys(id integer PRIMARY KEY, kurssi varchar(255), aihe varchar(255), kysymysteksti varchar(255));");
        lista.add("CREATE TABLE Vastaus(id integer PRIMARY KEY, kysymys_id integer, vastausteksti varchar(255), oikein boolean, FOREIGN KEY (kysymys_id) REFERENCES Kysymys(id));");
        lista.add("INSERT INTO Kysymys (kurssi, aihe, kysymysteksti) VALUES ('TiKaPe', 'Perus', 'Toimiiko?');");
        lista.add("INSERT INTO Kysymys (kurssi, aihe, kysymysteksti) VALUES ('TilTuR', 'Kulku', 'Onko helppoa?');");
        lista.add("INSERT INTO Vastaus (kysymys_id, vastausteksti, oikein) VALUES (1, 'Toimii', 'TRUE');");
        lista.add("INSERT INTO Vastaus (kysymys_id, vastausteksti, oikein) VALUES (1, 'Ei', 'FALSE');");
        lista.add("INSERT INTO Vastaus (kysymys_id, vastausteksti, oikein) VALUES (2, 'On', 'TRUE');");
        lista.add("INSERT INTO Vastaus (kysymys_id, vastausteksti, oikein) VALUES (2, 'Ei', 'FALSE');");

        return lista;
    }
}
