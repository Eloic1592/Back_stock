package itusolar.simulation.capacity;

import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class SectionCapacityManager extends HServiceManager implements SectionFunct {

    @Override
    public SectionCapacity[] getAll(Connection connection) {
        return new SectionCapacity[0];
    }
}
