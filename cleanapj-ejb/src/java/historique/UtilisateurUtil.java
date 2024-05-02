/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package historique;

import bean.CGenUtil;
import bean.GenUtil;
import net.sf.jasperreports.engine.util.JsonUtil;
import utilitaire.UtilDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import utilitaire.Utilitaire;

/**
 * Utilitaire permettant de manipuler efficacement les utilisateurs {@link historique.MapUtilisateur}
 * <p>
 * Pour valider si un essai de connexion correspond à un utilisateur:
 * </p>
 * <pre>
 *  try{
 *    MapUtilisateur utilisateur = new UtilisateurUtil().testeValide("nom","mot de passe");
 *  }
 *  catch(Exception e){
 *    System.out.println("Utilisateur non valide :"+e.getMessage());
 *  }
 * </pre>
 * @author BICI
 */

public class UtilisateurUtil extends GenUtil implements java.io.Serializable,java.lang.Cloneable
{
  public UtilisateurUtil()
  {
    super("Utilisateur");
  }
  /**
   * 
   * @param user nom d'utilisateur identifiant
   * @param passe mot de passe non crypté de l'utilisateur
   * @param interim devrait être différent de 1
   * @param service service où on essaie de se connecter(inutile ici)
   * @return instance de l'utilisateur si existe
   * @throws Exception message d'erreur selon problème
   */
  public MapUtilisateurServiceDirection testeValide(String user, String passe, String interim, String service) throws Exception {
    /*Connection c = null;
    try {
        c = new UtilDB().GetConn();

        this.setNomTable("UtilisateurValide");
        MapUtilisateurServiceDirection[] temp = null;
        int[] a = {2};
        String[] valeur = new String[a.length];
        if ((user.compareToIgnoreCase("") == 0) || (passe.compareToIgnoreCase("")) == 0 || (passe.compareToIgnoreCase("%")) == 0) {
            throw new Exception("Erreur de Login ");
        }
        valeur[0] = user;
        MapUtilisateurServiceDirection rech = new MapUtilisateurServiceDirection();

        temp = (MapUtilisateurServiceDirection[]) CGenUtil.rechercher(rech, null, null, c, String.format(" and loginuser like '%s'", user));
        if (temp.length > 0) {

            //if(temp[0].getLoginuser().compareToIgnoreCase("dg")==0)return temp[0];
            //System.out.println("Nisy le izy !!!!!!!!!!!!!!!!!!!! "+temp[0].getLoginuser());
            System.out.println("user==" + temp[0].getRefuser());
s
            ParamCrypt crt = new ParamCrypt();
            crt.setIdUtilisateur(String.valueOf(temp[0].getRefuser()));
            ParamCrypt[] pc = (ParamCrypt[]) CGenUtil.rechercher(crt, null, null, c, "");
            if (pc.length == 0) {
                throw new Exception("Mot de passe invalide");
            }

            String passCrypt = Utilitaire.cryptWord(passe, pc[0].getNiveau(), pc[0].getCroissante());

            int u = temp[0].getPwduser().compareTo(passCrypt);
            System.out.println("mot decrypte = " + passCrypt + "temp 0=" + temp[0].getPwduser() + " et u===" + u);
            if (u == 0) {
                if (interim != null && interim.compareToIgnoreCase("1") == 0) {
                    
                } else {
                    return temp[0];
                }
            } else {
                throw new Exception("Login ou mot de passe erronné");
            }
        } else {
            throw new Exception("Login ou mot de passe erronné");
        }
    } catch (Exception ex) {
        throw ex;
    } finally {
        if (c != null) {
            c.close();
        }
    }*/
    return null;
}
  /**
     * @param user nom d'utilisateur(identifiant) 
     * @param pass mot de passe non crypté de l'utilisateur
     * @return l'instance de l'utilisateur qui essaie de se connecter si credentials correct
     */
//  public MapUtilisateur testeValide(String user,String passe) throws Exception
//  {
//    Connection c=null;
//    try {
//      c=new UtilDB().GetConn();
//
//      this.setNomTable("UtilisateurValide");
//      MapUtilisateur[] temp = null;
//      int []a={1};
//      String [] valeur=new String [a.length];
//      if((user.compareToIgnoreCase("")==0)||(passe.compareToIgnoreCase(""))==0||(passe.compareToIgnoreCase("%"))==0) throw new Exception("Erreur de Login ");
//      valeur[0]=user;
//      //valeur[1]=passe;
//
//      //temp=((MapUtilisateur [])rechercher(a,valeur,"",c));
//      MapUtilisateur rech=new MapUtilisateur();
//      rech.setNomTable("UtilisateurValide");
////      rech.setLoginuser(user);
//      //temp=(MapUtilisateur [])CGenUtil.rechercher(rech, null,null, c, " and loginuser ='"+user+"' ");
////      System.out.println(temp);
//
//      if (temp.length>0)
//      {
//
//        //if(temp[0].getLoginuser().compareToIgnoreCase("dg")==0)return temp[0];
//        //System.out.println("Nisy le izy !!!!!!!!!!!!!!!!!!!! "+temp[0].getLoginuser());
//
//        ParamCrypt[] pc = (ParamCrypt[])new ParamCryptUtil().rechercher(4,temp[0].getTuppleID(),c);
//
//        if(pc.length==0) {
//         System.out.println("Tsy Nisy le izy !!!!!!!!!!!!!!!!!!!!");
//        throw new Exception("Pas de cryptage associe");
//        }
//
//        String passCrypt=Utilitaire.cryptWord(passe, pc[0].getNiveau(),pc[0].getCroissante());
//
//        int u=temp[0].getPwduser().compareTo(passCrypt);
//        System.out.println("mot decryptes = "+passCrypt+ " temp 0="+temp[0].getPwduser()+" et u==="+u);
//        if(u==0) return temp[0];
//        else throw new Exception("Erreur de Login ");
//      }
//      else{
//
//        // System.out.println("Tsy hita le izy !!!!!!!!!!!!!!!!!!!!");
//        throw new Exception("Erreur de Login ");
//      }
//    }
//    catch (Exception ex) {
//      throw new Exception(ex.getMessage());
//    }
//    finally{
//        if(c!=null){
//            c.close();
//        }
//    }
//  }
  public MapUtilisateur testeValide(String user,String passe) throws Exception
  {
    Connection c=null;
    try {
      c=new UtilDB().GetConn();
      this.setNomTable("UtilisateurValide");
      MapUtilisateur[] temp = null;
      int []a={1};
      String [] valeur=new String [a.length];
      System.out.println(user);
      System.out.println(passe);
      if((user.compareToIgnoreCase("")==0)||(passe.compareToIgnoreCase(""))==0||(passe.compareToIgnoreCase("%"))==0) throw new Exception("Erreur de Login ");
      valeur[0]=user;
      //valeur[1]=passe;

      //temp=((MapUtilisateur [])rechercher(a,valeur,"",c));
      MapUtilisateur rech=new MapUtilisateur();
      rech.setNomTable("UtilisateurValide");
//      rech.setLoginuser(user);
      temp=(MapUtilisateur [])CGenUtil.rechercher(rech, null,null, c, " and loginuser ='"+user+"' ");

      if (temp.length>0)
      {

        //if(temp[0].getLoginuser().compareToIgnoreCase("dg")==0)return temp[0];
        //System.out.println("Nisy le izy !!!!!!!!!!!!!!!!!!!! "+temp[0].getLoginuser());
        ParamCrypt[] pc = (ParamCrypt[])new ParamCryptUtil().rechercher(4,temp[0].getTuppleID(),c);

        if(pc.length==0) {
          System.out.println("Tsy Nisy le izy !!!!!!!!!!!!!!!!!!!!");
          throw new Exception("Pas de cryptage associe");
        }

        String passCrypt=Utilitaire.cryptWord(passe, pc[0].getNiveau(),pc[0].getCroissante());

        int u=temp[0].getPwduser().compareTo(passe);
        System.out.println("mot decryptes = "+passe+ " temp 0="+temp[0].getPwduser()+" et u==="+u);
        if(u==0) return temp[0];
        else throw new Exception("Erreur de Login ");
      }
      else{
        // System.out.println("Tsy hita le izy !!!!!!!!!!!!!!!!!!!!");
        throw new Exception("Erreur de Login ");
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw new Exception(ex.getMessage());
    }
    finally{
      if(c!=null){
        c.close();
      }
    }
  }

  /**
   * tester si un nom d'utilisateur et un mot de passe correspondent à un utilisateur en base
   * @param nomTable table de l'utilisateur
   * @param user nom d'utilisateur
   * @param passe mot de passe non crypté de l'utilisateur
   * @return utilisateur correspondant au nom d'utilisateur et mot de passe
   * @throws Exception
   */
  public MapUtilisateur testeValide(String nomTable,String user,String passe) throws Exception
 {
   Connection c=null;
   try {
     c=new UtilDB().GetConn();
     this.setNomTable(nomTable);
     MapUtilisateur[] temp = null;
     int []a={2};
     String [] valeur=new String [a.length];
     if((user.compareToIgnoreCase("")==0)||(passe.compareToIgnoreCase(""))==0||(passe.compareToIgnoreCase("%"))==0) throw new Exception("Erreur de Login ");
     valeur[0]=user;
     //valeur[1]=passe;
     temp=((MapUtilisateur[])rechercher(a,valeur));
     if (temp.length>0)
     {
       //if(temp[0].getLoginuser().compareToIgnoreCase("dg")==0)return temp[0];
       ParamCrypt[] pc = (ParamCrypt[])new ParamCryptUtil().rechercher(4,temp[0].getTuppleID(),c);
       if(pc.length==0) throw new Exception("Pas de cryptage associe");
       String passCrypt= Utilitaire.cryptWord(passe, pc[0].getNiveau(),pc[0].getCroissante());
       System.out.println(temp[0].getLoginuser() +" : " +passCrypt);
       if(temp[0].getPwduser().compareTo(passCrypt)==0)
         return temp[0];
       else throw new Exception("Erreur de Login ");
     }

     else
       throw new Exception("Erreur de Login ");
   }
   catch (Exception ex) {
       ex.printStackTrace();
     throw new Exception(ex.getMessage());
   }
   finally
   {
     if(c!=null)c.close();
   }
  }
 /**
  * rechercher un utilisateur par identifiant
  * @param refUser identifiant de l'utilisateur
  * @return utilisateur portant l'identifiant
  * @throws Exception si aucun utilisateur correspondant
  */
  public MapUtilisateur rechercheByRef(String refUser)
  {
    MapUtilisateur  retour=((MapUtilisateur [])rechercher (1,refUser))[0];
    return retour;
  }
  /**
   * 
   * @return tous les utilisateurs
   */
  public MapUtilisateur[] recherche()
  {
    MapUtilisateur[]  retour=(MapUtilisateur [])rechercher (1,"%");
    return retour;
  }
  /**
   * 
   * @param role id numérique du role du role
   * @return liste des utilisateurs qui correspondent au role
   */
  public MapUtilisateur[] rechercheByRole(int role)
  {
    try {
      MapUtilisateur[]  retour=null;
      int []a={7};
      Object [] valeur=new Object [a.length];
      if (role==0)
      {
        retour=(MapUtilisateur [])rechercher (1,"%");
      }
      else
      {
        valeur[0]=String.valueOf(role);
        retour=(MapUtilisateur [])rechercher (a,valeur);
      }
      return retour;
    }
    catch (Exception ex) {
      return null;
    }
  }

  /**
   * Génerer une liste d'utilisateur à partir d'un result set
   * @param rs pointeur de résultat vers la base de données
   * @return liste d'utilisateur générés
   */
  public Object[] resultatGen (ResultSet rs)
  {
    try
    {
      int i = 0, k = 0;
      MapUtilisateur temp = null;
      Vector vect = new Vector();

      while(rs.next())
      {
        temp = new MapUtilisateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
        vect.add(i,temp);
        i++;
      }

      MapUtilisateur []retour = new MapUtilisateur[i];

      while (k < i)
      {
        retour[k] = (MapUtilisateur)(vect.elementAt(k));
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
  public Object[] resultatLimit (int numBloc,ResultSet rs)
  {
    return null;
  }

}