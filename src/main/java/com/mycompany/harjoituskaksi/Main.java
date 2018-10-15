/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.harjoituskaksi;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.sql.*;
import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import static spark.Spark.get;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
/**
 *
 * @author Jere
 */


public class Main {

    public static void main(String[] args) throws Exception {
        if (System.getenv("PORT") != null) {
            Spark.port(Integer.valueOf(System.getenv("PORT")));
        }
        Database database = new Database("jdbc:sqlite:Kysely.db");
        //Alla oleva komento luo uudet taulukot ja täyttää niihin esimerkit. Käytä vain jos tiedostoa ei löydy.
        //database.init();
        KysymysDao kysymysDao = new KysymysDao(database);
        VastausDao vastausDao = new VastausDao(database);
        
        
        Spark.get("/kysymykset", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("kysymykset", kysymysDao.findAll());
            return new ModelAndView(map, "kysymykset");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/kysymykset", (req, res) -> {
            kysymysDao.add(req.queryParams("kurssi"),req.queryParams("aihe"),req.queryParams("kysymysteksti"));
            res.redirect("/kysymykset");
            return "";
        });
        Spark.post("/delete/:id", (req, res) -> {
            kysymysDao.delete(Integer.parseInt(req.params(":id")));
            vastausDao.deleteId(Integer.parseInt(req.params(":id")));
            res.redirect("/kysymykset");
            return "";
        });
        
        
        
        Spark.get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            return new ModelAndView(map ,"index");
        }, new ThymeleafTemplateEngine());
        
        
        
        Spark.get("/vastaukset", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("vastaukset", vastausDao.findAll());
            return new ModelAndView(map, "vastaukset");
        }, new ThymeleafTemplateEngine());
        
        
        Spark.get("/vastaus/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("kysymys", kysymysDao.getTeksti(Integer.parseInt(req.params(":id"))));
            map.put("vastaukset", vastausDao.findVastaukset(Integer.parseInt(req.params(":id"))));
            map.put("kysid", Integer.parseInt(req.params(":id")));
            
            return new ModelAndView(map, "vastaus");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/delet/:id", (req, res) -> {
            vastausDao.delete(Integer.parseInt(req.params(":id")));
            res.redirect("/kysymykset");
            return "";
        });
        Spark.post("/vastaukset", (req, res) -> {
            try {
            vastausDao.add(Integer.parseInt(req.queryParams("kysymys_id")),req.queryParams("vastaus"),req.queryParams("oikeus"));
            res.redirect("/kysymykset");
            return "";
            } catch (Exception e){
                res.redirect("/kysymykset");
                return "";
            }
        });
        
        
        
    }
}
