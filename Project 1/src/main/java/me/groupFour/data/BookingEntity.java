package me.groupFour.data;

import javax.persistence.*;
import java.util.List;
//Booking Entity is used to store bookings from the user. It associates a user account with the journey.
@Entity
@Table(name="Booking")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int BookingID;
    @JoinColumn(name="AccountID")
    @ManyToOne()
    private AccountEntity AccountID;
    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="JourneyID")
    private BookedJourneyEntity JourneyID;

    public BookingEntity() {
    }

    public BookingEntity(int bookingID, AccountEntity accountID,BookedJourneyEntity journeyID) {
        BookingID = bookingID;
        AccountID = accountID;
        JourneyID = journeyID;
    }

    public int getBookingID() {
        return BookingID;
    }

    public void setBookingID(int bookingID) {
        BookingID = bookingID;
    }

    public AccountEntity getAccountID() {
        return AccountID;
    }

    public void setAccountID(AccountEntity accountID) {
        AccountID = accountID;
    }

    public BookedJourneyEntity getJourneyID() {
        return JourneyID;
    }

    public void setJourneyID(BookedJourneyEntity journeyID) {
        JourneyID = journeyID;
    }
}

//@ManyToMany(targetEntity=FlightEntity.class,cascade =CascadeType.PERSIST, mappedBy="BookingEntityList")
    /*
@Query(
        value = "SELECT * FROM price x where x.AirlineCode =:AirlineCode and x.FlightNumber =:FlightNumber and x.ClassCode = :classCode and x.TicketCode =:ticketcode and CURDATE() between x.StartDate and x.EndDate",
        nativeQuery = true)
List<PriceEntity> findPrice(@Param("AirlineCode") String AirlineCode,("FlightNumber") String FlightNumber)
*/