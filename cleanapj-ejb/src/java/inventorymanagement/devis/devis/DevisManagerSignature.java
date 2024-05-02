package inventorymanagement.devis.devis;

import inventorymanagement.devis.detaildevis.Detaildevis;
import inventorymanagement.devis.detaildevis.Detaildevisview;

import java.sql.Connection;

public interface DevisManagerSignature {
    public void createdevis(Devis devis, Connection connection) throws Exception;
    public Clientdevis[] getalldevis(Connection connection) throws Exception;
    public Detaildevisview[] getalldetailsdevis(String iddevis, Connection connection) throws Exception;
    public DevisPageList contentlist(Connection connection,String iddevis) throws Exception;
    public void createdetails(Detaildevis[] detaildevis, Connection connection) throws Exception;
    public DevisPageList contentform(Connection connection) throws Exception;

}
