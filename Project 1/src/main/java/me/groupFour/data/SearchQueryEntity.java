package me.groupFour.data;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
//Search query entity is used to store info from the flight search to send to the flight controller.
public class SearchQueryEntity {
    @NotNull
    String fromDest = "";
    @NotNull
    String toDest = "";
    @NotNull
    String viaDest = "";
    int maxLayoverTime = 0;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date fromTime,toTime;
    String ticketCode;
    String classCode;

    public SearchQueryEntity() {
    }

    public SearchQueryEntity(String fromDest, String toDest, String viaDest, int maxLayoverTime, Date fromTime, Date toTime, String ticketCode, String classCode) {
        this.fromDest = fromDest;
        this.toDest = toDest;
        this.viaDest = viaDest;
        this.maxLayoverTime = maxLayoverTime;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.ticketCode = ticketCode;
        this.classCode = classCode;
    }


    public int getMaxLayoverTime() {
        return maxLayoverTime;
    }

    public void setMaxLayoverTime(int maxLayoverTime) {
        this.maxLayoverTime = maxLayoverTime;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getTicketCode() {
        return this.ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getFromDest() {
        return fromDest;
    }

    public void setFromDest(String fromDest) {
        this.fromDest = fromDest;
    }

    public String getToDest() {
        return toDest;
    }

    public void setToDest(String toDest) {
        this.toDest = toDest;
    }

    public String getViaDest() {
        return viaDest;
    }

    public void setViaDest(String viaDest) {
        this.viaDest = viaDest;
    }

    public Timestamp getFromTimeSQL() {
        return new Timestamp(fromTime.getTime());
    }

    public Date getFromTime() {
        return fromTime;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Timestamp getToTimeSQL() {
        return new Timestamp(toTime.getTime());
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }

    @Override
    public String toString() {
        return "SearchQueryEntity{" +
                "fromDest='" + fromDest + '\'' +
                ", toDest='" + toDest + '\'' +
                ", viaDest='" + viaDest + '\'' +
                ", maxLayoverTime=" + maxLayoverTime +
                ", fromTime=" + fromTime +
                ", toTime=" + toTime +
                ", ticketCode='" + ticketCode + '\'' +
                ", classCode='" + classCode + '\'' +
                '}';
    }
}


