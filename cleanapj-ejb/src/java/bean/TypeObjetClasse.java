package bean;
/**
 *  Objet de mapping générique pour les tables de catégorie avec hierarchie.
 *  
 * <p>
 * Prenons un exemple un cas d'utilisation on veut créer des catégories de produits avec possibilité de 
 * sous categorie
 * </p>
 * <p>
 *  On peut prendre les grandes catégories comme suit:
 * </p>
 * <pre>
 *  
 *  TypeObjetClasse critere = new TypeObjetClasse();
 *  critere.setNomTable("categorie");
 *  TypeObjetClasse[] reponses = (TypeObjet[]) CGenUtil.rechercher(critere,null,null,"idparent is null");
 * </pre>
 * <p>
 *   Pour chaque categorie pour avoir les enfants directs on peut faire comme suit:
 * </p>
 * <pre>
 *  
 *  TypeObjetClasse critere = new TypeObjetClasse();
 *  critere.setNomTable("categorie");
 *  critere.setIdParent("CAT0001");
 *  TypeObjetClasse[] reponses = (TypeObjet[]) CGenUtil.rechercher(critere,null,null,"");
 * </pre>
 * <p>
 *  On peut aussi prendre toutes les valeurs une fois et créer un arbre.
 * </p>
 * @author BICI
 */
public class TypeObjetClasse extends ClassMAPTable {


  public String val;
  public String desce;
  public String id;

  public String idParent;
/**
     * Constructeur
     * @param nomTable nom de la table en base
     * @param nomProcedure nom de la procédure pour génerer l'ID
     * @param suff suffixe à utiliser pour génerer l'ID
     * @param vale valeur de l'objet
     * @param desc description détaillée
     * @param idParent id du parent direct dans l'hierarchie
     */
  public TypeObjetClasse(String nomTable,String nomProcedure,String suff,String vale,String desc,String idParent) {
    this.setNomTable(nomTable);
    this.setNomProcedureSequence(nomProcedure);
    this.setIdParent(idParent);
    this.setIndicePk(suff);
    this.id=this.makePK();
    this.val=vale;
    this.setDesce(desc);
  }
  /**
     * Constructeur
     * @param nomTable nom de la table en base
     * @param ide id de l'objet
     * @param vale valeur de l'objet
     * @param desc description détaillée
     * @param idParent id du parent direct dans l'hierarchie
     */
  public TypeObjetClasse(String nomTable,String ide,String vale,String desc,String idParent) {
    this.setNomTable(nomTable);
    this.setId(ide);
    this.setVal(vale);
    this.setDesce(desc);
    this.setIdParent(idParent);
  }
  /**
     * 
     * @param ide id de l'objet
     * @param vale valeur de l'objet
     * @param desc description détaillée
     * @param idParent id du parent direct dans l'hierarchie 
     */
  public TypeObjetClasse(String ide,String vale,String desc,String idParent) {
    id=ide;
    val=vale;
    this.setDesce(desc);
    this.setIdParent(idParent);
  }
  /**
   * Constructeur par défaut
   */
  public TypeObjetClasse(){

  }

  /**
   * 
   * @return id id du parent direct dans l'hierarchie si aucun null
   */
  public String getIdParent() {
    return idParent;
  }
  /**
   * @param idParent id du parent direct dans l'hierarchie si aucun null
   */
  public void setIdParent(String idParent) {
    this.idParent = idParent;
  }
  public String getAttributIDName() {
    return "id";
  }
  public String getTuppleID() {
    return id;
  }
  public void setVal(String val)
    {
        this.val = val;
    }
    /**
     * @return la valeur du type/categorie
     */
    public String getVal()
    {
        return val;
    }

    public void setDesce(String desc)
    {
        if(desc == null)
            desce = "-";
        else
            desce = desc;
    }
    /**
     * 
     * @return description de la catégorie
     */
    public String getDesce()
    {
        return desce;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    /**
     * 
     * @return id de la catégorie
     */
    public String getId()
    {
        return id;
    }
}
