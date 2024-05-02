package inventorymanagement.depot;


import bean.CGenUtil;
import bean.ClassMAPTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.prepare.MappedInteger;
import utilitaire.UtilDB;

import java.sql.Connection;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Depot extends MappedInteger {
    String iddepot;
    String  depot;

    public Depot() {
        super.setNomTable("depot");
    }

    @Override
    @JsonIgnore
    public void construirePK(Connection c) throws Exception {
        this.setNomTable("depot");
        this.preparePk("DEP", "getseqdepot");
        this.setIddepot(makePK(c));

    }

    @Override
    public String getTuppleID() {
        return iddepot;
    }

    @Override
    public String getAttributIDName() {
        return "iddepot";
    }

    public String getIddepot() {
        return iddepot;
    }
    public void setIddepot(String iddepot) {
        this.iddepot = iddepot;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }


    public static void main(String[] args) throws Exception {
        Connection c = null;
        int verif = 0;
        try {
            if (c == null) {
                c = new UtilDB().GetConn();
                verif = 1;
            }
            Depot depot = new Depot();
            depot.construirePK(c);
            depot.setIddepot("17");
            depot.setDepot("depot 75");
            if(depot.getIddepot()!=null) {
                depot.updateToTable(c);
            }else {
                CGenUtil.save(depot, c);
            }



        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (c != null && verif == 1) {
                c.close();
            }
        }

    }

}
