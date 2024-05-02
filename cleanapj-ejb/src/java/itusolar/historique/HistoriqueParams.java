package itusolar.historique;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class HistoriqueParams {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    Timestamp start;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    Timestamp end;
    int page;
    int rows;

    @Override
    public String toString() {
        return "HistoriqueParams{" +
                "start=" + start +
                ", end=" + end +
                ", page=" + page +
                ", rows=" + rows +
                '}';
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }
}
