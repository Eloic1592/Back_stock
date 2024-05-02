package itusolar.predict.loader;

import itusolar.lieu.BuildingManagerSignature;
import itusolar.lieu.SectionManagerSignature;
import itusolar.simulation.DateConcern;
import itusolar.simulation.material.MaterialManagerSignature;
import itusolar.source.SectionCapacityManagerSignature;

import java.io.IOException;
import java.sql.Connection;

public class MeteoLoaderOptimized extends MeteoLoader {

    public MeteoLoaderOptimized(BuildingManagerSignature lieuManager, MaterialManagerSignature appareilManager, SectionCapacityManagerSignature sectionCapacityManager, SectionManagerSignature sectionManager) {
        super(lieuManager, appareilManager, sectionCapacityManager, sectionManager);
    }

    @Override
    public void loadTemperature(DateConcern[] dates, Connection connection) throws IOException {
        if (dates.length == 0)
            return;
        try {
            this.search(dates[0]);
            this.search(dates[dates.length - 1]);
            this.setDateConcerns(dates);
            this.loadTemp();
        } catch (NullPointerException e) {
            super.loadTemperature(dates, connection);
        }
    }
}
