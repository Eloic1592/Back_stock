package itusolar.lieu;

import bean.CGenUtil;
import itusolar.prepare.HServiceManager;

import java.sql.Connection;

public class BuildingManager extends HServiceManager implements BuildingManagerSignature {
    SectionManagerSignature sectionManager;

    public BuildingManager(SectionManagerSignature sectionManager) {
        this.sectionManager = sectionManager;
    }

    @Override
    public void create(Building building, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        if (building.getId() > 0) {
            building.updateToTable(connection);
            return;
        }
        building.construirePK(connection);
        CGenUtil.save(building, connection);
    }

    @Override
    public itusolar.lieu.BuildingForm contentForm(Building building, Connection connection) throws Exception {
        Section[] sections = this.sectionManager.getAll(connection);
        String where = " and id = "+building.getId();
        Building[] buildings = this.searchBuilding(where, connection);
        building = buildings.length > 0 ? buildings[0] : null;
        return new BuildingForm(building, sections);
    }

    @Override
    public BuildingList contentList(Connection connection) throws Exception {
        connection = this.getConnection(connection);
        return new BuildingList(this.getBuildings(connection));
    }

    @Override
    public BuildingView[] getBuildings(Connection connection) throws Exception {
        return this.searchBuilding("",connection);
    }

    public BuildingView[] searchBuilding(String where, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Object[] result = CGenUtil.rechercher(new BuildingView(),new String[0],new String[0],connection, where);
        return this.castBuilding(result);
    }

    private BuildingView[] castBuilding(Object[] result) {
        BuildingView[] results = new BuildingView[result.length];
        for (int i = 0; i < results.length; i++) {
            results[i] = (BuildingView) result[i];
        }
        return results;
    }

    @Override
    public Building[] getAll(Connection connection) throws Exception {
        return this.search("",connection);
    }

    public Building[] search(String where, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Object[] result = CGenUtil.rechercher(new Building(),new String[0],new String[0],connection, where);
        return this.cast(result);
    }

    public Building[] cast(Object[] datas) {
        Building[] result = new Building[datas.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = (Building) datas[i];
        }
        return result;
    }

    @Override
    public Building getById(int id, Connection connection) throws Exception {
        connection = this.getConnection(connection);
        Building[] lieux = this.cast(CGenUtil.rechercher(new Building(),new String[0],new String[0],connection, " and id="+id));
        return lieux[0];
    }

    public SectionManagerSignature getSectionManager() {
        return sectionManager;
    }

    public void setSectionManager(SectionManagerSignature sectionManager) {
        this.sectionManager = sectionManager;
    }
}
