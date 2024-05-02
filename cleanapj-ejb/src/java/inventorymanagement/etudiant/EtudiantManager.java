package inventorymanagement.etudiant;

import bean.CGenUtil;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class EtudiantManager extends HServiceManager implements EtudiantManagerSignature {
    EtudiantManagerSignature etudiantManagerSignature;

    @Override
    public ListeEtudiant[] getall(Connection connection) throws Exception {
        connection=this.getConnection(connection);
        Object[] data = CGenUtil.rechercher(new ListeEtudiant(), null, null, connection, "");
        return castdata(data);
    }

    public ListeEtudiant[] castdata(Object[] datas) {

        ListeEtudiant[] etudiants = new ListeEtudiant[datas.length];
        for (int i = 0; i < datas.length; i++) {
            etudiants[i] = (ListeEtudiant) datas[i];
        }
        return etudiants;
    }
}
