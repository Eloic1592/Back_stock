package inventorymanagement.article;

import bean.CGenUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.prepare.MappedInteger;
import utilitaire.UtilDB;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Article extends MappedInteger {
    String idarticle;
    String marque;
    String modele;
    String description;
    String idtypemateriel;
    double prix;
    double quantitestock;
    String codearticle;
    double stocksecurite;


    public Article() {
        super.setNomTable("article");
    }
    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("article");
        this.preparePk("ART", "getseqarticle");
        this.setIdarticle(makePK(c));

    }
    @Override
    public String getTuppleID() {
        return idarticle;
    }

    @Override
    public String getAttributIDName() {
        return "idarticle";
    }



    public String getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(String idarticle) {
        this.idarticle = idarticle;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }


    public String getModele() {
        return this.modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getDescription() throws SQLException {
        return  description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdtypemateriel() {
        return idtypemateriel;
    }

    public void setIdtypemateriel(String idtypemateriel) {
        this.idtypemateriel = idtypemateriel;
    }

    public String handleClob(Clob clob) throws SQLException {
        if (clob == null)
            return null;

        Reader reader = null;
        try {
            reader = clob.getCharacterStream();
            if (reader == null)
                return null;
            char[] buffer = new char[(int)clob.length()];
            if (buffer.length == 0)
                return null;
            reader.read(buffer);
            return new String(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (reader != null)
                try {reader.close();} catch (IOException e) {throw new RuntimeException(e);}
        }
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getQuantitestock() {
        return quantitestock;
    }

    public void setQuantitestock(double quantitestock) {
        this.quantitestock = quantitestock;
    }

    public String getCodearticle() {
        return codearticle;
    }

    public void setCodearticle(String codearticle) {
        this.codearticle = codearticle;
    }

    public double getStocksecurite() {
        return stocksecurite;
    }

    public void setStocksecurite(double stocksecurite) {
        this.stocksecurite = stocksecurite;
    }
}
