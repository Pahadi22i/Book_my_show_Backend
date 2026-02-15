package com.cfs.book_my_show.Model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore; // 🔥 Import this

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter; // 🔥 Changed
import lombok.NoArgsConstructor;
import lombok.Setter; // 🔥 Changed

@Entity
@Table(name = "bookings")
@Getter // 🔥 @Data se Infinite Loop hota hai
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(nullable = false, unique = true)
     private String bookingNumber;

     @Column(nullable = false)
     private LocalDateTime bookingTime;

     @ManyToOne
     @JoinColumn(name = "user_id", nullable = false)
     private User user;

     @ManyToOne
     @JoinColumn(name = "show_id", nullable = false)
     private Show show;

     @Column(nullable = false)
     private String status; // confirmed , cancel, pending

     @Column(nullable = false)
     private Double totalAmount;

     // 🔥 FIX: Agar ShowSeat ke andar wapis Booking hai, to yaha bhi @JsonIgnore
     // lagana padega
     // Filhal ke liye safe side rehne ke liye laga raha hu
     @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
     @JsonIgnore
     private List<ShowSeat> showSeat;

     @OneToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "payment_id")
     private Payment payment;
}