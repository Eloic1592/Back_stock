package itusolar.prepare.schedule.devide;

import itusolar.prepare.schedule.Schedule;

import java.sql.Connection;

public interface DividerSignature {
    public void divide(DividerParams params, Connection connection) throws Exception;
}
