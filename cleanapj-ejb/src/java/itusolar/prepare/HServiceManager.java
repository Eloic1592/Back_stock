package itusolar.prepare;

import utilitaire.UtilDB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.function.Consumer;

public class HServiceManager {

    public void readFile(String path, Consumer<BufferedReader> action) throws IOException, NoSuchFileException {
        File file = new File(path);
        if (!file.exists()) {
            throw new NoSuchFileException();
        }
        FileReader reader = new FileReader(path);
        BufferedReader br = new BufferedReader(reader);
        action.accept(br);
        br.close();
        reader.close();
    }

    public Connection getConnection(Connection connection) throws SQLException {
        return (connection != null && !connection.isClosed())? connection :(new UtilDB()).GetConn();
    }

    public String toTimestamp(String str) {
        return String.format("to_timestamp('%s','YYYY-MM-DD HH24:MI:SS.FF3')",str);
    }

    public String toDate(Date date) {
        LocalDate temp = date.toLocalDate();
        String value = String.format("%s-%s-%s",temp.getYear(),temp.getMonth().getValue(),temp.getDayOfMonth());
        return this.toDate(value);
    }

    public String toDate(String date) {
        return String.format("to_date('%s','YYYY-MM-DD')",date);
    }
}
