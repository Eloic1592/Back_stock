package itusolar.historique.confirmation;

import itusolar.historique.HistTempList;
import itusolar.historique.HistTemporary;

import java.sql.Connection;
import java.sql.SQLException;

public interface HistoryValidatorSignature {
    public void validate(ValidatorParams params, Connection connection) throws Exception;
    public void validate(HistTemporary[] params, Connection connection) throws Exception;
}
