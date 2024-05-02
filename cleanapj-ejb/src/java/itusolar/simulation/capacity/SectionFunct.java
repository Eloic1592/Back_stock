package itusolar.simulation.capacity;

import java.sql.Connection;

public interface SectionFunct {
    public SectionCapacity[] getAll(Connection connection);
}
