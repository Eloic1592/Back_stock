package lc;

import bean.ClassMAPTable;
import java.sql.Connection;

/**
 * Classe representant la table "Direction" en metier.
 * 
 * @author
 */
public class Direction extends ClassMAPTable {
	
	public String idDir;
	public String libelledir;
	public String descdir;
	public double idDirecteur;
	public String abbrevDir;
    /**
     * Constructeur par défaut
     */
	public Direction() {
		super.setNomTable("direction");
               
	}
	/**
     * 
     * @param libelledir libelle representant la direction
     * @param descdir description detaillée de la direction
     * @param idDirecteur id de l'utilisateur qui est responsable de la direction
     * @param abbrevDir abbreviation(2 à 4 lettres) de la direction
     * @throws Exception
     */
	public Direction(String libelledir, String descdir, double idDirecteur, String abbrevDir) throws Exception {
		this.libelledir = libelledir;
		this.descdir = descdir;
		this.idDirecteur = idDirecteur;
		this.abbrevDir = abbrevDir;
		
		super.setNomTable("direction");
               
	}
    /**
     * 
     * @param idDir identifiant unique de la direction
     * @param libelledir libelle representant la direction
     * @param descdir description detaillée de la direction
     * @param idDirecteur id de l'utilisateur qui est responsable de la direction
     * @param abbrevDir abbreviation(2 à 4 lettres) de la direction
     * @throws Exception
     */
	public Direction(String idDir, String libelledir, String descdir, double idDirecteur, String abbrevDir) throws Exception {
		this.idDir = idDir;
		this.libelledir = libelledir;
		this.descdir = descdir;
		this.idDirecteur = idDirecteur;
		this.abbrevDir = abbrevDir;		
		super.setNomTable("direction");
               
	}

        @Override
	public String getAttributIDName() {
		return "idDir";
	}

        @Override
	public String getTuppleID() {
		return String.valueOf(idDir);
	}

    /**
     * 
     * @return abbreviation(2 à 4 lettres) de la direction
     */
	public String getAbbrevDir() {
		return abbrevDir;
	}
    /**
     * 
     * @param abbrevDir abbreviation(2 à 4 lettres) de la direction
     */
	public void setAbbrevDir(String abbrevDir) {
		this.abbrevDir = abbrevDir;
	}
    /**
     * 
     * @return description detaillée de la direction
     */
	public String getDescdir() {
		return descdir;
	}
    /**
     * 
     * @param descdi description detaillée de la direction
     */
	public void setDescdir(String descdi) {
		
		this.descdir = descdi;
	}
    /**
     * 
     * @return identifiant unique de la direction
     */
	public String getIdDir() {
		return idDir;
	}
    /**
     * 
     * @param id identifiant unique de la direction
     */
	public void setIdDir(String id) {
		this.idDir = id;
	}
    /**
     * 
     * @return id de l'utilisateur qui est responsable de la direction
     */
	public double getIdDirecteur() {
		return idDirecteur;
	}
    /**
     * 
     * @param idDirecteur  id de l'utilisateur qui est responsable de la direction
     */
	public void setIdDirecteur(double idDirecteur) {
		this.idDirecteur = idDirecteur;
	}
    /**
     * 
     * @return libelle representant la direction
     */
	public String getLibelledir() {
		return libelledir;
	}
    /**
     * 
     * @param libelledir libelle representant la direction
     */
	public void setLibelledir(String libelledir) {
		this.libelledir = libelledir;
	}
        
        @Override
         public void construirePK(Connection c) throws Exception{
        super.setNomTable("direction");
        this.preparePk("DIR", "getSeqDirection");
        this.setIdDir(makePK(c));
    }
}