package com.cfs.book_my_show.Model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore; // 🔥 Import this

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter; // 🔥 Use Getter
import lombok.NoArgsConstructor;
import lombok.Setter; // 🔥 Use Setter

@Entity
@Table(name = "users")
@Getter // 🔥 @Data hataya, Getter lagaya
@Setter // 🔥 Setter lagaya
@NoArgsConstructor
@AllArgsConstructor
public class User {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(nullable = false)
     private String name;

     @Column(nullable = false, unique = true)
     private String email;

     @Column(nullable = false)
     private String password;

     @Column(nullable = false)
     private String phoneNumber;

     // 🔥 FIX: @JsonIgnore lagaya.
     // Iska matlab: "Jab User ka data bhejo, to uski Bookings ki list mat bhejo"
     // Isse Infinite Loop band ho jayega.
     @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
     @JsonIgnore
     private List<Booking> bookings;

}