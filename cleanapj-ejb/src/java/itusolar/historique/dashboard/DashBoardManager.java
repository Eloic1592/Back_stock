package itusolar.historique.dashboard;

import itusolar.predict.loader.LoaderSignature;
import itusolar.prepare.HServiceManager;
import itusolar.simulation.DateConcern;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DashBoardManager extends HServiceManager implements DashBoardManagerSignature{
    SectionStateSignature sectionState;
    LoaderSignature meteoLoader;

    public DashBoardManager(SectionStateSignature sectionState,LoaderSignature meteoLoader) {
        this.sectionState = sectionState;
        this.meteoLoader = meteoLoader;
    }

    @Override
    public DashBoard dashboard(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        DateConcern first = new DateConcern(Timestamp.valueOf(LocalDateTime.now()));
        DateConcern last = new DateConcern(Timestamp.valueOf(LocalDateTime.now()));
        DateConcern[] dates = {first, last};
        this.meteoLoader.loadTemperature(dates, connection);
        SectionStateResult[] states = this.sectionState.evalState(null,connection);
        return new DashBoard(states, first);
    }
}
