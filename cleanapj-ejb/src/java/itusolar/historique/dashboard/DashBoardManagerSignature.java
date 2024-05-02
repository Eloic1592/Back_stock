package itusolar.historique.dashboard;

import java.sql.Connection;

public interface DashBoardManagerSignature {
    public DashBoard dashboard(Connection connection) throws Exception;
}
