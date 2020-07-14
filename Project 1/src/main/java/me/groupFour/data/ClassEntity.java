package me.groupFour.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

//ticketclass Entity is used to map data from the database using hibernate related to the ticketclass table.
@Entity
@Table(name="ticketclass")
public class ClassEntity {
    @Id
    private String ClassCode;
    private String Details;
    @OneToMany(mappedBy = "ticketclass")
    private List<LegEntity> LegEntityList;
    @OneToMany(mappedBy="ClassCode")
    private List<PriceEntity> ClassList;


    public ClassEntity(String classCode, String details, List<LegEntity> legEntityList, List<PriceEntity> classList) {
        ClassCode = classCode;
        Details = details;
        LegEntityList = legEntityList;
        ClassList = classList;
    }

    public ClassEntity() {
    }

    public String getClassCode() {
        return ClassCode;
    }

    public void setClassCode(String classCode) {
        ClassCode = classCode;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}
