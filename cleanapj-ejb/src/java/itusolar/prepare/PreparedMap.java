package itusolar.prepare;

import affichage.PageRecherche;
import bean.ClassMAPTable;
import bean.ListeColonneTable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public abstract class PreparedMap extends ClassMAPTable {

    public PageRecherche hprepare(HttpServletRequest request, HttpSession session) throws Exception {
        Field[] fields = ListeColonneTable.getFieldListeHeritage(this);
        ArrayList<Field> intervals = this.hprepareIntervals(fields);
        String[] intervalParams = this.toStr(intervals);
        ArrayList<Field> simples = this.hprepareSimple(fields);
        String[] simplesParams = this.toStr(simples);
//        ArrayList<Field> libele = this.hprepareLibele(fields);
        String[] libeleParams = this.extractLibeleParams();
        for (String inter:intervalParams) {
            System.out.println("inter : "+inter);
        }
        for (String inter:simplesParams) {
            System.out.println("simple : "+inter);
        }
        for (String inter:libeleParams) {
            System.out.println("libele : "+inter);
        }

        PageRecherche pr = new PageRecherche(this, request, simplesParams, intervalParams, 3, libeleParams, libeleParams.length);
        pr.setUtilisateur((user.UserEJB) session.getValue("u"));
        pr.setLien((String) session.getValue("lien"));
        this.prepareForm(intervals,simples,pr);
        String[] colSomme = {};
        pr.creerObjetPage(libeleParams, colSomme);
        return pr;
    }

    public String[] extractLibeleParams() {
        return new String[]{};
    }

    public void prepareForm(ArrayList<Field> intervals, ArrayList<Field> simples, PageRecherche pr) {
        HashMap<String,Boolean> histPoint = new HashMap<>();
        for (Field field : intervals) {
            if (histPoint.get(field.getName()) == null) {
                Intervalle rLibele = field.getAnnotation(Intervalle.class);
                if (rLibele != null) {
                    String fieldName =field.getName()+"1";
                    pr.getFormu().getChamp(fieldName).setLibelleAffiche(rLibele.minLabel());
                    this.configType(pr,fieldName, field);
                    fieldName =field.getName()+"2";
                    pr.getFormu().getChamp(fieldName).setLibelleAffiche(rLibele.maxLabel());
                    this.configType(pr, fieldName, field);
                }
                histPoint.put(field.getName(),true);
            }
        }
        for (Field field : simples) {
            if (histPoint.get(field.getName()) == null) {
                System.out.println(field.getName());
                this.configType(pr, field.getName(), field);
                ReplaceLibele rLibele = field.getAnnotation(ReplaceLibele.class);
                if (rLibele != null) {
                    pr.getFormu().getChamp(field.getName()).setLibelleAffiche(rLibele.champ());
                }
                histPoint.put(field.getName(),true);
            }
        }
    }

    public void configType(PageRecherche pr, String fieldName, Field field) {
        String type = "text";
        if (field.getType().equals(Date.class) || field.getType().equals(java.sql.Date.class)) {
            type = "date";
        } else if (field.getType().equals(Timestamp.class)) {
            type = "datetime-local";
        } else if (field.getType().equals(Time.class)) {
            type = "time";
        }
        System.out.println("field: " + fieldName);
        System.out.println("type: " + type);
        pr.getFormu().getChamp(fieldName).setType(type);
    }

    private ArrayList<Field> hprepareLibele(Field[] fields) {
        ArrayList<Field> intervals = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            HIngore ingore = field.getAnnotation(HIngore.class);
            if ( ingore == null) {
                intervals.add(field);
                continue;
            }
            if ( !ingore.libele())
                intervals.add(field);

        }
        return intervals;
    }

    public String[] toStr(ArrayList<Field> fields) {
        String[] results = new String[fields.size()];
        int i = 0;
        for (Field field:fields){
            results[i] = field.getName();
            i++;
        }
        return results;
    }

    public ArrayList<Field> hprepareSimple(Field[] fields) {
        ArrayList<Field> intervals = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            HIngore ingore = field.getAnnotation(HIngore.class);
            if ( ingore == null) {
                intervals.add(field);
                continue;
            }
            if ( !ingore.critere())
                intervals.add(field);

        }
        return intervals;
    }

    public ArrayList<Field> hprepareIntervals(Field[] fields) {
        ArrayList<Field> intervals = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getAnnotation(Intervalle.class) != null)
                intervals.add(field);
        }
        return intervals;
    }
}
