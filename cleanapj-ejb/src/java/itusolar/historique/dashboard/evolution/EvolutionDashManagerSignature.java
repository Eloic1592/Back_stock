package itusolar.historique.dashboard.evolution;

import java.sql.Connection;
import java.sql.Date;

public interface EvolutionDashManagerSignature {
    public EvolutionDash dashContent(Date start, Date end, Connection connection) throws Exception;
    public EvolutionDash dashContent(EvolutionParams params, Connection connection) throws Exception;
}
