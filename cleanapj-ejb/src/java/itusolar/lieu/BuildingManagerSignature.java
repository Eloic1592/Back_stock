package itusolar.lieu;

import java.sql.Connection;

public interface BuildingManagerSignature {
    public void create(itusolar.lieu.Building building, Connection connection) throws Exception;
    public BuildingForm contentForm(itusolar.lieu.Building building, Connection connection) throws Exception;
    public BuildingList contentList(Connection connection) throws Exception;
    BuildingView[] getBuildings(Connection connection) throws Exception;
    public itusolar.lieu.Building[] getAll(Connection connection) throws Exception;
    public Building getById(int id, Connection connection) throws Exception;
}
