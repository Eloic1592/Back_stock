package itusolar.historique;

import user.UserEJB;

import java.sql.Connection;
import java.sql.Timestamp;

public interface HistoriqueManagerSignature {
    public void create(HistoryParams params, Connection connection) throws Exception;
    public HistEnergyCalc[] getAll(Connection connection) throws Exception;
    public HistEnergyCalc[] cast(Object[] datas);
    public Object[] search(HistEnergyCalc histEnergy, HistoriqueParams params, Connection connection, String afterWhere) throws Exception;
}
