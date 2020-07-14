package me.groupFour.data;

import javax.persistence.*;
import java.util.List;

//TicketType
//maps to the tickettype table in the database using hiberate
@Entity
@Table(name="tickettype")
public class TicketEntity {
    @Id
    private String TicketCode;
    private String Name;
    private Integer Transferrable;
    private Integer Refundable;
    private Integer Exchangeable;
    private Integer FrequentFlyerPoints;
    @OneToMany(mappedBy = "tickettype")
    private List<LegEntity> LegEntityList;
    @OneToMany(mappedBy="TicketCode")
    private List<PriceEntity> ticketlist;

    public TicketEntity() {
    }

    public TicketEntity(String ticketCode, String name, Integer transferrable, Integer refundable, Integer exchangeable, Integer frequentFlyerPoints, List<LegEntity> legEntityList, List<PriceEntity> ticketlist) {
        TicketCode = ticketCode;
        Name = name;
        Transferrable = transferrable;
        Refundable = refundable;
        Exchangeable = exchangeable;
        FrequentFlyerPoints = frequentFlyerPoints;
        LegEntityList = legEntityList;
        this.ticketlist = ticketlist;
    }


    public String getTicketCode() {
        return TicketCode;
    }

    public void setTicketCode(String TicketCode) {
        TicketCode = TicketCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        Name = Name;
    }

    public Integer getTransferrable() {
        return Transferrable;
    }

    public void setTransferrable(Integer Transferrable) {
        Transferrable = Transferrable;
    }

    public Integer getRefundable() {
        return Refundable;
    }

    public void setRefundable(Integer Refundable) {
        Refundable = Refundable;
    }

    public Integer getExchangeable() {
        return Exchangeable;
    }

    public void setExchangeable(Integer Exchangeable) {
        Exchangeable = Exchangeable;
    }

    public Integer getFrequentFlyerPoints() {
        return FrequentFlyerPoints;
    }

    public void setFrequentFlyerPoints(Integer FrequentFlyerPoints) {
        FrequentFlyerPoints = FrequentFlyerPoints;
    }

    public List<LegEntity> getLegEntityList() {
        return LegEntityList;
    }

    public void setLegEntityList(List<LegEntity> legEntityList) {
        LegEntityList = legEntityList;
    }
}
