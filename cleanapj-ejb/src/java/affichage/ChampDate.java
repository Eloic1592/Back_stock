package affichage;

import utilitaire.Utilitaire;
/**
 * Champ spécial pour les attributs de date.
 * Ceffe classe a besoin du fichier javascript champdate.js pour fonctionner.
 * 
 * @author BICI
 */

public class ChampDate extends Champ{
    
    private final String MODEUPDATE = "UPDATE";
    private String mode = "";
    /**
     * Constructeur par défaut
     */
    public ChampDate(){
        super();
    }
    /**
     * Constructeur
     * @param nom nom du champs concerné
     */
    public ChampDate(String nom){
        super(nom);
    }
    
    /***
     * Constructeur
     * @param nom nom du champs concerné
     * @param mode mode
     */
    public ChampDate(String nom, String mode){
        super(nom);
        this.mode = mode;
    }
    
    /**
     * Surchargement de makeHTML
     * génerer le html d'input pour les numériques
     */
    public void makeHtml() throws Exception{
        String temp = "";
        String val = getValeur();
        if(mode.compareToIgnoreCase(MODEUPDATE) == 0){
            val = Utilitaire.convertDatyFormtoRealDatyFormat(getValeur());
        }else{
            val = Utilitaire.dateDuJour();
        }
        if(getVisible()==false)
        {
            temp = "<input name=" + getNom() + " type='hidden' class=" + getCss() + "  id=" + getNom() + " value='" + val + "' onmouseover=\"datepicker('"+getNom()+"')\" " + getAutre() + ">";
        }
        else {
            temp = "<input name=" + getNom() + " type=" + getType() + " class=" + getCss() + "  id=" + getNom() + " value='" + val + "' onmouseover=\"datepicker('"+getNom()+"')\" " + getAutre() + ">";
        }
        setHtml(temp);
    }
}
