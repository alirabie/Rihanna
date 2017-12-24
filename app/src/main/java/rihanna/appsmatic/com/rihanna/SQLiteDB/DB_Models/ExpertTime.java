package rihanna.appsmatic.com.rihanna.SQLiteDB.DB_Models;

/**
 * Created by Eng Ali on 12/23/2017.
 */
public class ExpertTime {
    private int day;
    private String from;
    private String to;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
