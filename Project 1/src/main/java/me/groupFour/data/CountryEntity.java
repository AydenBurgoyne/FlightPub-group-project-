package me.groupFour.data;

import javax.persistence.*;
import java.util.List;

@Entity
//Used to map the country table using hibernate.
@Table(name="country")
public class CountryEntity {
    @Id
    @Basic(optional = false)
    private String countryCode3;

    @Basic(optional = false)
    private String countryCode2;
    private String countryName;
    private String alternateName1;
    private String alternateName2;
    private String motherCountryCode3;
    private String motherCountryComment;

    @ManyToMany(mappedBy="WishList")
    List<AccountEntity> AccountEntityList;

    @OneToMany(mappedBy="CountryCode3")
    List<DestinationEntity> DestinationEntityList;
    public CountryEntity(){
    }
    @OneToMany(mappedBy="CountryCode3")
    List<AirlineEntity> AirlineEntityList;


    public CountryEntity(String countryCode3, String countryCode2, String countryName, String alternateName1, String alternateName2, String motherCountryCode3, String motherCountryComment, List<AccountEntity> accountEntityList) {
        this.countryCode3 = countryCode3;
        this.countryCode2 = countryCode2;
        this.countryName = countryName;
        this.alternateName1 = alternateName1;
        this.alternateName2 = alternateName2;
        this.motherCountryCode3 = motherCountryCode3;
        this.motherCountryComment = motherCountryComment;
        AccountEntityList = accountEntityList;
    }

    public String getCountryCode3() {
        return countryCode3;
    }

    public void setCountryCode3(String countryCode3) {
        this.countryCode3 = countryCode3;
    }

    public String getCountryCode2() {
        return countryCode2;
    }

    public void setCountryCode2(String countryCode2) {
        this.countryCode2 = countryCode2;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getAlternateName1() {
        return alternateName1;
    }

    public void setAlternateName1(String alternateName1) {
        this.alternateName1 = alternateName1;
    }

    public String getAlternateName2() {
        return alternateName2;
    }

    public void setAlternateName2(String alternateName2) {
        this.alternateName2 = alternateName2;
    }

    public String getMotherCountryCode3() {
        return motherCountryCode3;
    }

    public void setMotherCountryCode3(String motherCountryCode3) {
        this.motherCountryCode3 = motherCountryCode3;
    }

    public String getMotherCountryComment() {
        return motherCountryComment;
    }

    public void setMotherCountryComment(String motherCountryComment) {
        this.motherCountryComment = motherCountryComment;
    }

    public List<AccountEntity> getAccountEntityList() {
        return AccountEntityList;
    }

    public void setAccountEntityList(List<AccountEntity> accountEntityList) {
        AccountEntityList = accountEntityList;
    }

}
