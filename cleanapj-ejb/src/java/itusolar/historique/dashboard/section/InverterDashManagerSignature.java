package itusolar.historique.dashboard.section;

import itusolar.lieu.Section;

import java.sql.Connection;

public interface InverterDashManagerSignature {
    public InverterDash dashContent(Section section, Connection connection) throws Exception;
}
