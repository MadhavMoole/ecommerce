package org.example.ecommerce.database.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Long id;

        @Column(name = "username", nullable = false, unique = true)
        private String username;

        @Column(name = "password", nullable = false, length = 1000)
        private String password;

        @Column(name = "email", nullable = false, unique = true, length = 320)
        private String email;

        @Column(name = "first_name", nullable = false)
        private String firstName;

        @Column(name = "last_name", nullable = false)
        private String lastName;

        @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
        private List<Address> addresses = new ArrayList<>();

        public List<Address> getAddresses() {
                return addresses;
        }

        public void setAddresses(List<Address> addresses) {
                this.addresses = addresses;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }
}
