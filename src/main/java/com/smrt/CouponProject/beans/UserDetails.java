package com.smrt.CouponProject.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDetails {
    private String email;
    private String password;
    private String role;
    private int id;
}
