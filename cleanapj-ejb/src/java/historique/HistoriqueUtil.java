package historique;
import bean.*;
import java.sql.*;
import java.util.*;
import utilitaire.*;
import java.util.*;

/**
 * Utilité pour accéder aux historiques en base de données.
 * <p>
 * On veut par exemple voir toutes les modifications réalisées par un utilisateur:
 * <pre>
 * MapHistorique[] historique = new HistoriqueUtil().rechercheActeur(idUser);
 * </pre>
 * </p>
 * 
 * @author BICI
 */

public class HistoriqueUtil extends GenUtil implements java.io.Serializable,java.lang.Cloneable
{
	public HistoriqueUtil()
	{
	super("Historique","historique.MapHistorique");
        System.out.println("ca y ets");
          //super("HISTORIQUE");
		//nomTable = "HISTORIQUE_ORD";
		//makeChamp();
	}

  //Permet de retourner une exception pour test

        public int testException() throws Exception
        {
          try {
            if(1>2)
            return 0;
            throw new Exception("HistoriqueUtil");
          }
          catch (Exception ex) {
            throw new Exception("Exception aty @ "+ex.getMessage());
          }
	}


    /**
     * Rechercher les historiques sur un objet par son id
     * @param ref id de l'objet
     * @return liste des historiques correspondant
     */
	public MapHistorique[] rechercheById(String ref)
	{
		MapHistorique [] retour=(MapHistorique [])rechercher (4,ref);
		return retour;
	}
    /**
     * Rechercher les historiques des modifications réalisées par un utilisateur donné
     * @param user id de l'utilisateur qui a modifié
     * @return liste des historiques correspondant
     */
	public MapHistorique[] rechercheActeur(String user)
	{
		MapHistorique [] retour=(MapHistorique [])rechercher (6,user);
		return retour;
	}
    /**
     * Rechercher les historiques selon l'action et l'objet concerné
     * @param action action réalisée
     * @param ref id de l'objet concerné
     * @return liste des historiques correspondant
     */
	public MapHistorique[] rechercheHeureAction(String action,String ref)
	{
                try {
                  int []a={4,5};
                  String []val=new String[a.length];
                  val[0]=new String(ref);
                  val[1]=new String(action);
                  MapHistorique [] retour=(MapHistorique [])rechercher (a,val);
                  return retour;
                }
                catch (Exception ex) {
                  return null;
                }
	}
	/**
     * Rechercher les historiques d'un utilisateur à une date donnée
     * @param refUser id de l'utilisateur
     * @param daty date des modifications
     * @return liste des historiques correspondant
     */
	public MapHistorique[] rechercheByRefDate(String refUser,String daty)
	{
                try {
                  int []a={2,6};
                  String []val=new String[a.length];
                  val[0]=daty;
                  val[1]=refUser;
                  MapHistorique[] retour=(MapHistorique[])rechercher (a,val);
                  return retour;
                }
                catch (Exception ex) {
                  return null;
                }
	}
    /**
     * Rechercher les historiques avec critère multiple
     * @param refUser id de l'utilisateur ayant realisé l'action
     * @param objet nom de la table modifiée
     * @param action action de l'utilisateur(insert,annul, modif)
     * @param daty date de modification
     * @return liste des historiques correspondant
     */
        public MapHistorique[] rechercheHisto(String refUser,String objet,String action,String daty)
        {
                try {
                  int []a={2,4,5,6};
                  String []val=new String[a.length];
                  val[0]=new String(daty);
                  val[1]=new String(objet);
                  val[2]=new String(action);
                  val[3]=new String(refUser);
                  MapHistorique [] retour=(MapHistorique [])rechercher (a,val);
                  return retour;
                }
                catch (Exception ex) {
                  return null;
                }
	}
    /**
     * 
     * @param numChamp numéro des champs de critère(selon leur ordre de déclaration dans la classe MapHistorique)
     * @param valeur valeurs de ces numéro des champs
     * @return
     */
	public MapHistorique[] recherche(int [] numChamp,Object[] valeur)
	{
                try {
                  MapHistorique[] retour=(MapHistorique[])rechercher (numChamp,valeur);
                  return retour;
                }
                catch (Exception ex) {
                  return null;
                }
	}
    /**
     * @deprecated
     * Inutile dans le contexte
     */
	public Object[] resultatGen (ResultSet rs)
	{
		try
		{
			int i = 0, k = 0;
			MapHistorique temp = null;
			Vector vect = new Vector();

			while(rs.next())
			{
				temp = new MapHistorique(rs.getString(1),rs.getDate(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
				vect.add(i,temp);
				i++;
			}

			MapHistorique []retour = new MapHistorique[i];

			while (k < i)
			{
				retour[k] = (MapHistorique)(vect.elementAt(k));
				k++;
			}
			return retour;
		}
		catch (Exception s)
		{
			System.out.println("Resultat "+s.getMessage());
			return null;
		}
                finally
                {
                  try
                  {
                        if (rs!=null) rs.close();
                  }
                  catch(SQLException e)
                  {
                    System.out.println("Erreur Fermeture SQL HistoriqueOrdUtil "+ e.getMessage());
                  }
                }
	}
    /**
     * @deprecated
     * retourne null
     */
	public Object[] resultatLimit (int numBloc,ResultSet rs)
	{
		return null;
	}

}