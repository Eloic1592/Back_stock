package inventorymanagement.materiel.categoriemateriel;

import inventorymanagement.materiel.typemateriel.TypematerielPageList;

import java.sql.Connection;

public interface CategoriematerielManagerSignature {
    public void create(Categoriemateriel categoriemateriel, Connection connection) throws Exception;
    public Categoriemateriel[] getall(Connection connection) throws Exception;
    public CategoriematerielPageList contentlist(Connection connection) throws Exception;
}
