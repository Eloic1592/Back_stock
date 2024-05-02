package historique;
import bean.*;
import java.sql.*;
import java.util.*;
import utilitaire.*;
import java.util.*;

/**
 * Classe representant l'utilitaire pour faciliter la manipulation des roles {@link historique.MapRoles}
 * <p>
 *  Exemple d'utilisation de l'utilitaire, on veut trouver les informations sur le role 1:
 * </p>
 * <pre>
 * MapRoles role = new RoleUtil().rechercheById(1);
 * </pre>
 * @author BICI
 */
public class RoleUtil extends GenUtil implements java.io.Serializable,java.lang.Cloneable
{
	public RoleUtil()
	{
		super("roles");
	}


    /**
     * Trouver un role à partir de son id
     * @param id identifiant numérique du role
     * @return Role si existe
     * @throws Exception si role n'existe pas
     */
	public MapRoles rechercheById(int id)
	{
		MapRoles retour=((MapRoles [])rechercher (1,String.valueOf(id)))[0];
		return retour;
	}

    /**
     * Rechercher un role à partir de sa description
     * @param desc description
     * @return role correspondant
     * @throws Exception si role n'existe pas
     */
	public MapRoles rechercheByDesc(String desc)
	{
		MapRoles retour=((MapRoles [])rechercher (2,desc))[0];
		return retour;
	}


    /**
     * @param rs pointeur de resultat brut de base de données
     * @return liste d'objets représentant des roles
     */
	public Object[] resultatGen (ResultSet rs)
	{
		try
		{
			int i = 0, k = 0;
			MapRoles temp = null;
			Vector vect = new Vector();

			while(rs.next())
			{
				temp = new MapRoles(rs.getString(1),rs.getString(2));
				vect.add(i,temp);
				i++;
			}

			MapRoles []retour = new MapRoles[i];

			while (k < i)
			{
				retour[k] = (MapRoles)(vect.elementAt(k));
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
     * @return null
     */
	public Object[] resultatLimit (int numBloc,ResultSet rs)
	{
		return null;
	}

}