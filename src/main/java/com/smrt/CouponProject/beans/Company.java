package com.smrt.CouponProject.beans;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    // Company ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Company name
    @Column(nullable = false, length = 20)
    private String name;

    // Company email
    @Column(nullable = false, length = 50)
    private String email;

    // Company password
    @Column(nullable = false, length = 25)
    private String password;

    // Company coupon list
    @Singular
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Coupon> coupons;
}
