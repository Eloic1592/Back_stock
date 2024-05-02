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
    public static void main(String[] args) throws Exception {
        Connection c = null;
        int verif = 0;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                verif = 1;
            }
            Article[] articles = (Article[]) CGenUtil.rechercher(new Article(), null, null, c, " and marque like 'DELL'");
            for (int i = 0; i < articles.length; i++) {
                System.out.println(articles[i].getModele());
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (c != null && verif == 1) {
                c.close();
            }
        }
    }
}
