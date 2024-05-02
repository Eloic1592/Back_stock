package itusolar.historique.dashboard;

import java.sql.Connection;

public interface SectionStateSignature {
    public SectionStateResult[] evalState(SectionStateParams params ,Connection connection) throws Exception;
}
