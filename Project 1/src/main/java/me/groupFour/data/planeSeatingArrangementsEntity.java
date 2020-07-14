package me.groupFour.data;

import javax.persistence.*;
//Plane seating arragnements entity maps to the corrosponding table in the database. It's purpose is to store seating arrangements for plane types.
@Entity
@Table(name="planeseatingarragnements")
public class planeSeatingArrangementsEntity {
    @Id
    int id;
    String FC_arrangment;
    @ManyToOne()
    @JoinColumn(name="planeType")
    PlaneEntity planeType;
    int FC_row_count;
    String FC_row_range;
    int FC_seat_count;
    int FC_num_excluded;
    String FC_seats_excluded;
    String BC_arrangment;
    int BC_row_count;
    String BC_row_range;
    int BC_seat_count;
    int BC_num_excluded;
    String BC_seats_excluded;
    String PE_arrangement;
    int PE_row_count;
    String PE_row_range;
    int PE_seat_count;
    int PE_num_excluded;
    String PE_seats_excluded;
    String EE_arrangment;
    int EE_row_count;
    String EE_row_range;
    int EE_seat_count;
    int EE_num_excluded;
    String EE_seats_excluded;

    public planeSeatingArrangementsEntity() {
    }

    public planeSeatingArrangementsEntity(int id, String FC_arrangement, PlaneEntity planeCode, int FC_row_count, String FC_row_range, int FC_seat_count, int FC_num_excluded, String FC_seats_excluded, String BC_arrangement, int BC_row_count, String BC_row_range, int BC_seat_count, int BC_num_excluded, String BC_seats_excluded, String PE_arrangement, int PE_row_count, String PE_row_range, int PE_seat_count, int PE_num_excluded, String PE_seats_excluded, String EE_arrangment, int EE_row_count, String EE_row_range, int EE_seat_count, int EE_num_excluded, String EE_seats_excluded) {
        this.id = id;
        this.FC_arrangment = FC_arrangement;
        this.planeType = planeCode;
        this.FC_row_count = FC_row_count;
        this.FC_row_range = FC_row_range;
        this.FC_seat_count = FC_seat_count;
        this.FC_num_excluded = FC_num_excluded;
        this.FC_seats_excluded = FC_seats_excluded;
        this.BC_arrangment = BC_arrangement;
        this.BC_row_count = BC_row_count;
        this.BC_row_range = BC_row_range;
        this.BC_seat_count = BC_seat_count;
        this.BC_num_excluded = BC_num_excluded;
        this.BC_seats_excluded = BC_seats_excluded;
        this.PE_arrangement = PE_arrangement;
        this.PE_row_count = PE_row_count;
        this.PE_row_range = PE_row_range;
        this.PE_seat_count = PE_seat_count;
        this.PE_num_excluded = PE_num_excluded;
        this.PE_seats_excluded = PE_seats_excluded;
        this.EE_arrangment = EE_arrangment;
        this.EE_row_count = EE_row_count;
        this.EE_row_range = EE_row_range;
        this.EE_seat_count = EE_seat_count;
        this.EE_num_excluded = EE_num_excluded;
        this.EE_seats_excluded = EE_seats_excluded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFC_arrangement() {
        return FC_arrangment;
    }

    public void setFC_arrangement(String FC_arrangement) {
        this.FC_arrangment = FC_arrangement;
    }

    public PlaneEntity getPlaneCode() {
        return planeType;
    }

    public void setPlaneCode(PlaneEntity planeCode) {
        this.planeType = planeCode;
    }

    public int getFC_row_count() {
        return FC_row_count;
    }

    public void setFC_row_count(int FC_row_count) {
        this.FC_row_count = FC_row_count;
    }

    public String getFC_row_range() {
        return FC_row_range;
    }

    public void setFC_row_range(String FC_row_range) {
        this.FC_row_range = FC_row_range;
    }

    public int getFC_seat_count() {
        return FC_seat_count;
    }

    public void setFC_seat_count(int FC_seat_count) {
        this.FC_seat_count = FC_seat_count;
    }

    public int getFC_num_excluded() {
        return FC_num_excluded;
    }

    public void setFC_num_excluded(int FC_num_excluded) {
        this.FC_num_excluded = FC_num_excluded;
    }

    public String getFC_seats_excluded() {
        return FC_seats_excluded;
    }

    public void setFC_seats_excluded(String FC_seats_excluded) {
        this.FC_seats_excluded = FC_seats_excluded;
    }

    public String getBC_arrangement() {
        return BC_arrangment;
    }

    public void setBC_arrangement(String BC_arrangement) {
        this.BC_arrangment = BC_arrangement;
    }

    public int getBC_row_count() {
        return BC_row_count;
    }

    public void setBC_row_count(int BC_row_count) {
        this.BC_row_count = BC_row_count;
    }

    public String getBC_row_range() {
        return BC_row_range;
    }

    public void setBC_row_range(String BC_row_range) {
        this.BC_row_range = BC_row_range;
    }

    public int getBC_seat_count() {
        return BC_seat_count;
    }

    public void setBC_seat_count(int BC_seat_count) {
        this.BC_seat_count = BC_seat_count;
    }

    public int getBC_num_excluded() {
        return BC_num_excluded;
    }

    public void setBC_num_excluded(int BC_num_excluded) {
        this.BC_num_excluded = BC_num_excluded;
    }

    public String getBC_seats_excluded() {
        return BC_seats_excluded;
    }

    public void setBC_seats_excluded(String BC_seats_excluded) {
        this.BC_seats_excluded = BC_seats_excluded;
    }

    public String getPE_arrangement() {
        return PE_arrangement;
    }

    public void setPE_arrangement(String PE_arrangement) {
        this.PE_arrangement = PE_arrangement;
    }

    public int getPE_row_count() {
        return PE_row_count;
    }

    public void setPE_row_count(int PE_row_count) {
        this.PE_row_count = PE_row_count;
    }

    public String getPE_row_range() {
        return PE_row_range;
    }

    public void setPE_row_range(String PE_row_range) {
        this.PE_row_range = PE_row_range;
    }

    public int getPE_seat_count() {
        return PE_seat_count;
    }

    public void setPE_seat_count(int PE_seat_count) {
        this.PE_seat_count = PE_seat_count;
    }

    public int getPE_num_excluded() {
        return PE_num_excluded;
    }

    public void setPE_num_excluded(int PE_num_excluded) {
        this.PE_num_excluded = PE_num_excluded;
    }

    public String getPE_seats_excluded() {
        return PE_seats_excluded;
    }

    public void setPE_seats_excluded(String PE_seats_excluded) {
        this.PE_seats_excluded = PE_seats_excluded;
    }

    public String getEE_arrangment() {
        return EE_arrangment;
    }

    public void setEE_arrangment(String EE_arrangment) {
        this.EE_arrangment = EE_arrangment;
    }

    public int getEE_row_count() {
        return EE_row_count;
    }

    public void setEE_row_count(int EE_row_count) {
        this.EE_row_count = EE_row_count;
    }

    public String getEE_row_range() {
        return EE_row_range;
    }

    public void setEE_row_range(String EE_row_range) {
        this.EE_row_range = EE_row_range;
    }

    public int getEE_seat_count() {
        return EE_seat_count;
    }

    public void setEE_seat_count(int EE_seat_count) {
        this.EE_seat_count = EE_seat_count;
    }

    public int getEE_num_excluded() {
        return EE_num_excluded;
    }

    public void setEE_num_excluded(int EE_num_excluded) {
        this.EE_num_excluded = EE_num_excluded;
    }

    public String getEE_seats_excluded() {
        return EE_seats_excluded;
    }

    public void setEE_seats_excluded(String EE_seats_excluded) {
        this.EE_seats_excluded = EE_seats_excluded;
    }
}
