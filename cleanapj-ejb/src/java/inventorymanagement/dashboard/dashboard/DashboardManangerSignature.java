package inventorymanagement.dashboard.dashboard;

import java.sql.Connection;

public interface DashboardManangerSignature  {
    public FIFO[] getFIFO(Connection connection) throws Exception;
    public LIFO[] getLIFO(Connection connection) throws  Exception;
    public Rotationstock[] getrotation(Connection connection) throws  Exception;
}
