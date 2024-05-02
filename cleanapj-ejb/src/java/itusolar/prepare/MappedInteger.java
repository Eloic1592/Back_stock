package itusolar.prepare;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.lang.reflect.Field;
import java.util.Vector;

public class MappedInteger extends PreparedMap{
    int id;

    @JsonIgnore
    public String getKey() {
        return String.valueOf(this.getId());
    }

    @JsonIgnore
    @Override
    public String getAttrMere() {
        return super.getAttrMere();
    }

    @JsonIgnore
    @Override
    public String getNomTable() {
        return super.getNomTable();
    }

    @JsonIgnore
    @Override
    public String getColumnlibelle() {
        return super.getColumnlibelle();
    }

    @JsonIgnore
    @Override
    public String getMode() {
        return super.getMode();
    }

    @JsonIgnore
    @Override
    public String getNomProcedureSequence() {
        return super.getNomProcedureSequence();
    }
    @JsonIgnore
    @Override
    public int getNombreChamp() {
        return super.getNombreChamp();
    }

    @JsonIgnore
    @Override
    public String getClassMere() {
        return super.getClassMere();
    }

    @JsonIgnore
    @Override
    public String getMemo() {
        return super.getMemo();
    }

    @JsonIgnore
    @Override
    public boolean getEstHistorise() {
        return super.getEstHistorise();
    }

    @JsonIgnore
    @Override
    public String getNomSequenceDirecte() {
        return super.getNomSequenceDirecte();
    }

    @JsonIgnore
    @Override
    public int getNombrepargroupe() {
        return super.getNombrepargroupe();
    }

    @JsonIgnore
    @Override
    public boolean getGroupe() {
        return super.getGroupe();
    }

    @JsonIgnore
    @Override
    public Class getCls() {
        return super.getCls();
    }

    @JsonIgnore
    @Override
    public void setNombreChamp() {
        super.setNombreChamp();
    }

    @JsonIgnore
    @Override
    public Vector getChamp() {
        return super.getChamp();
    }

    @JsonIgnore
    @Override
    public String getTuppleID() {
        return String.valueOf(this.getId());
    }
    @JsonIgnore
    @Override
    public String getAttributIDName() {
        return "id";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    @JsonIgnore
    public Field[] getFieldList() throws Exception {
        return super.getFieldList();
    }

    @JsonIgnore
    @Override
    public int getLonguerClePrimaire() {
        return super.getLonguerClePrimaire();
    }

    @JsonIgnore
    @Override
    public String getClassName() {
        return super.getClassName();
    }

    @JsonIgnore
    @Override
    public String getIdLibelle() {
        return super.getIdLibelle();
    }

    @JsonIgnore
    @Override
    public String getINDICE_PK() {
        return super.getINDICE_PK();
    }

    @JsonIgnore
    @Override
    public String getValColLibelle() {
        return super.getValColLibelle();
    }

    @JsonIgnore
    @Override
    public String getNomTableSelect() {
        return super.getNomTableSelect();
    }
}
