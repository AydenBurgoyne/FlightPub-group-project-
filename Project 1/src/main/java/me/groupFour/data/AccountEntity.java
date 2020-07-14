package me.groupFour.data;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

//java bean for storing account. This is the model that will be stored using hibernate.
@Entity
@Table(name="account")
    public class AccountEntity{
        //variables that will be stored

            @Column(name="first_name")
            @NotEmpty(message = "Please enter your first name")
                private String firstName;
            @Column(name="last_name")
            @NotEmpty(message = "Please enter your last name")
                private String lastName;
            @Id
            @Column(name="email")
            @Email
            @Pattern(regexp=".+@.+\\..+", message="This email address is not valid")
                private String email;
            @NotEmpty(message = "Please enter your address")
            @Column(name="address")
                private String address;
            @NotNull(message = "Please enter your date of birth")
            @Column(name="DateOfBirth")
            @Past
            @DateTimeFormat(pattern = "yyyy-MM-dd")
                private Date dateOfBirth;
            @NotNull(message = "Please select an option")
            @Column(name="aType")
                private Boolean type; //if true == business if false == recreational
            @Column(name="pword")
            @NotNull(message = "Please enter a password")
            @Pattern(regexp="^(?=.*\\d)(?=.*[A-Z]).{6,10}$", message="Please provide a valid password")
                private String pword;
            @ManyToMany
            @JoinTable(
                    name="wish_relation",
                    joinColumns= @JoinColumn(name="accountID"),
                    inverseJoinColumns = @JoinColumn(name="CountryCode3fk")
            )
            private List<CountryEntity> WishList;


            private String dummyWishList;


            @OneToMany(mappedBy = "AccountID")
            private List<BookingEntity> BookingList;

    public AccountEntity(String firstName, String lastName, String email, String address, Date dateOfBirth, Boolean type, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.type = type;
        this.pword = password;
        //WishList = wishList;
        //BookingList = bookingList;
    }

    public AccountEntity(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getPword() { return pword; }

    public void setPword(String password) { this.pword = password; }

    public String encryptPassword(String password) {
        int strength = 10;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        String encoded = encoder.encode(password);
        return encoded;
    }

    public List<CountryEntity> getWishList() {
        return WishList;
    }

    public void setWishList(List<CountryEntity> wishList) {
        WishList = wishList;
    }

    public List<BookingEntity> getBookingList() {
        return BookingList;
    }

    public void setBookingList(List<BookingEntity> bookingList) {
        BookingList = bookingList;
    }

    public String getDummyWishList() {
        return dummyWishList;
    }

    public void setDummyWishList(String dummyWishList) {
        this.dummyWishList = dummyWishList;
    }

}

