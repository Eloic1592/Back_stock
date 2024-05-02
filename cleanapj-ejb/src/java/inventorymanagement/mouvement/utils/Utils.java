package inventorymanagement.mouvement.utils;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public class Utils {

    // Méthode pour obtenir le nom du mois en français à partir de son numéro
    public static String getNomMois(int numeroMois) {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] nomsMois = dfs.getMonths();
        return nomsMois[numeroMois - 1];
    }
    //    Annee actuelle
    public static int getCurrentYear(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        return year;
    }

    public static int getCurrentMonth(){
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH);
        return currentMonth;
    }

}
