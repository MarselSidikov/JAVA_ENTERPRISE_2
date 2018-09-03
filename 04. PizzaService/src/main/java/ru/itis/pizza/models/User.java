package ru.itis.pizza.models;

import lombok.*;

import java.util.List;
import java.util.Objects;

/**
 * 03.09.2018
 * User
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String hashPassword;

    private List<Order> orders;
//
//    private User(Builder builder) {
//        this.id = builder.id;
//        this.firstName = builder.firstName;
//        this.lastName = builder.lastName;
//        this.email = builder.email;
//        this.hashPassword = builder.hashPassword;
//        this.orders = builder.orders;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getHashPassword() {
//        return hashPassword;
//    }
//
//    public void setHashPassword(String hashPassword) {
//        this.hashPassword = hashPassword;
//    }
//
//    public List<Order> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(List<Order> orders) {
//        this.orders = orders;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return Objects.equals(id, user.id) &&
//                Objects.equals(firstName, user.firstName) &&
//                Objects.equals(lastName, user.lastName) &&
//                Objects.equals(email, user.email) &&
//                Objects.equals(hashPassword, user.hashPassword) &&
//                Objects.equals(orders, user.orders);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, firstName, lastName, email, hashPassword, orders);
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", email='" + email + '\'' +
//                ", hashPassword='" + hashPassword + '\'' +
//                ", orders=" + orders +
//                '}';
//    }
//
//    public static class Builder {
//        private Long id;
//        private String firstName;
//        private String lastName;
//        private String email;
//        private String hashPassword;
//
//        private List<Order> orders;
//
//        public Builder firstName(String firstName) {
//            this.firstName = firstName;
//            return this;
//        }
//
//        public Builder lastName(String lastName) {
//            this.lastName = lastName;
//            return this;
//        }
//
//        public Builder email(String email) {
//            this.email = email;
//            return this;
//        }
//
//        public Builder hashPassword(String hashPassword) {
//            this.hashPassword = hashPassword;
//            return this;
//        }
//
//        public Builder orders(List<Order> orders) {
//            this.orders = orders;
//            return this;
//        }
//
//        public User build() {
//            return new User(this);
//        }
//    }
//
//    public static Builder builder() {
//        return new Builder();
//    }

}
