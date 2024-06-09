package inventorymanagement.stockage.inventaire;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itusolar.prepare.MappedInteger;

import java.sql.Connection;
import java.sql.Timestamp;

public class Calendrierinventaire extends MappedInteger {
    String idcalendrierinventaire;
    Timestamp datecalendrier;
    String description;
    Timestamp heuredebut;
    Timestamp heurefin;
    Timestamp datecreation;

    public Calendrierinventaire() {
        setNomTable("calendrierinventaire");
    }
    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("calendrierinventaire");
        this.preparePk("CAL", "getseqcalendrierinventaire");
        this.setIdcalendrierinventaire(makePK(c));

    }

    @Override
    public String getTuppleID() {
        return idcalendrierinventaire;
    }

    @Override
    public String getAttributIDName() {
        return "idcalendrierinventaire";
    }


    public String getIdcalendrierinventaire() {
        return idcalendrierinventaire;
    }

    public void setIdcalendrierinventaire(String idcalendrierinventaire) {
        this.idcalendrierinventaire = idcalendrierinventaire;
    }

    public Timestamp getDatecalendrier() {
        return datecalendrier;
    }

    public void setDatecalendrier(Timestamp datecalendrier) {
        this.datecalendrier = datecalendrier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getHeuredebut() {
        return heuredebut;
    }

    public void setHeuredebut(Timestamp heuredebut) {
        this.heuredebut = heuredebut;
    }

    public Timestamp getHeurefin() {
        return heurefin;
    }

    public void setHeurefin(Timestamp heurefin) {
        this.heurefin = heurefin;
    }

    public Timestamp getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Timestamp datecreation) {
        this.datecreation = datecreation;
    }

}
