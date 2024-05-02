package itusolar.jirama;

import java.sql.Connection;
import java.sql.SQLException;

public interface HTaxeManager {
    public Taxe getById(Taxe taxe) throws Exception;
    public Taxe getById(Taxe taxe, Connection connection) throws Exception;
    public TaxeForm formContent(Taxe taxe) throws Exception;
    public TaxeForm formContent(Taxe taxe,Connection connection) throws Exception;
    public void addTaxe(Taxe taxe,Connection connection) throws Exception;
    public void addTaxe(Taxe taxe) throws Exception;
    public Taxe[] getAllTaxes(Connection connection) throws Exception;

    public Taxe[] castAll(Object[] args);

    public TaxeForm formContent(TaxeRequest params, Connection connection) throws Exception;
}
