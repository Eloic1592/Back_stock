package inventorymanagement.mouvement.mouvementstock;
import inventorymanagement.mouvement.mouvementfictif.Mouvementfictif;
import inventorymanagement.mouvement.mouvementphysique.Mouvementphysique;
import java.sql.Connection;

public interface MouvementstockManagerSignature {
    public void createmvf(Mouvementfictif[] mouvementfictif, Connection connection) throws Exception;
    public void createmvp(Mouvementphysique[] mouvementphysique, Connection connection) throws Exception;
    public void createmouvementfictif(Mouvementstock mouvementstocks, Connection connection) throws Exception;
    public void createmouvementphysique(Mouvementphysique mouvementphysique, Connection connection) throws Exception;
    public MouvementstockPageList contentlistphysique(Connection connection) throws Exception;
    public MouvementstockPageList contentlistfictif(Connection connection) throws Exception;
    public void importexcelfile(Connection connection,String filename) throws  Exception;
}
