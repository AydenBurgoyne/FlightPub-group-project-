package me.groupFour.data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
//Is used to store data from the login form and send to the controller. To compare against existing users and retrieve the account entity to allow the user to log in.
public class LoginQueryEntity {
    @NotBlank
    @Email
    @Pattern(regexp=".+@.+\\..+", message="This email address is not valid")
    private String emailAddress;
    @NotBlank
    private String password;

    public LoginQueryEntity() {
    }

    public LoginQueryEntity(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
