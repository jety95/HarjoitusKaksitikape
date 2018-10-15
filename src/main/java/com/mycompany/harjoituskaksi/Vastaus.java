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
public class Vastaus {
    private Integer id;
    private Integer kysymys_id;
    private String vastausteksti;
    private Boolean oikein;
    
    public Vastaus(Integer id, Integer ky, String vastausteksti, Boolean oikein) {
        this.id = id;
        this.kysymys_id = ky;
        this.oikein = oikein;
        this.vastausteksti = vastausteksti;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKysymys_id() {
        return kysymys_id;
    }

    public void setKysymys(Integer kysymys) {
        this.kysymys_id = kysymys;
    }

    public String getVastausteksti() {
        return vastausteksti;
    }

    public void setVastausteksti(String nimi) {
        this.vastausteksti = nimi;
    }
    
    public Boolean getOikein(){
        return oikein;
    }
    
    public void setOikein(Boolean oikein){
        this.oikein = oikein;
    }
}
