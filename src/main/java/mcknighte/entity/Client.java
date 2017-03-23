package mcknighte.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import mcknighte.common.Convertable;
import mcknighte.common.Security;

/**
 * Client entity class, to represent a Client within the database
 * and throughout the application
 *
 * @author Edward McKnight (UP608985)
 * @see Appointment
 * @see ClientFacade
 * @see ClientService
 * @see ClientController
 * @since 2017
 * @version 1.0
 */
@Entity
public class Client implements Serializable, Convertable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String salt;
    @NotNull
    private String password;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String address;
    @NotNull
    private String country;
    private String postcode;
    private String phone;
    @NotNull
    private String email;

    /**
     * Constructor
     */
    public Client() {
    }

    /**
     * Get the security salt for the client
     *
     * @return the security salt for the client
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Set the security salt for the client
     *
     * @param salt the security salt for the client
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Get the country for the client
     *
     * @return the country for the client
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the country for the client
     *
     * @param country the country for the client
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get the email address for the client
     *
     * @return the email address for the client
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email address for the client
     *
     * @param email the email address for the client
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the phone number for the client
     *
     * @return the phone number for the client
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set the phone number for the client
     *
     * @param phone the phone number for the client
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Get the postcode for the client
     *
     * @return the postcode for the client
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Set the postcode for the client
     *
     * @param postcode the postcode for the client
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * Get the address for the client
     *
     * @return the address for the client
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the address for the client
     *
     * @param address the address for the client
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the last name for the client
     *
     * @return the last name for the client
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the last name for the client
     *
     * @param lastName the last name for the client
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the first name for the client
     *
     * @return the first name for the client
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the first name for the client
     *
     * @param firstName the first name for the client
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the username for the client
     *
     * @return the username for the client
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username for the client
     *
     * @param username the username for the client
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the hashed and salted password for the client
     *
     * @return the hashed and salted password for the client
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password for the client, this password is then hashed and salted
     *
     * @param password the password for the client
     */
    public void setPassword(String password) {
        this.salt = Security.generateRandomSalt();
        this.password = Security.sha512(password, this.salt);
    }

    /**
     * Get the ID for the client
     *
     * @return the ID for the client
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Set the ID for the client
     *
     * @param id the ID for the client
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Hash code
     *
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Equals
     *
     * @param object the object to compare to
     * @return whether or not this equals the object being compared to
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

   /**
     * Represent this object as a string
     * 
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return this.username;
    }

}
