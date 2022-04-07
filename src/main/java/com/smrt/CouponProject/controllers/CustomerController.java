package com.smrt.CouponProject.controllers;

import com.smrt.CouponProject.beans.Category;
import com.smrt.CouponProject.beans.LoginDetails;
import com.smrt.CouponProject.beans.UserDetails;
import com.smrt.CouponProject.exceptions.JwtException;
import com.smrt.CouponProject.exceptions.LoginException;
import com.smrt.CouponProject.exceptions.PurchaseException;
import com.smrt.CouponProject.services.CompanyService;
import com.smrt.CouponProject.services.CustomerService;
import com.smrt.CouponProject.jwt.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerController{

    private final CustomerService customerService;
    private final JWTUtils jwtUtils;

    private String role = "Customer";

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDetails loginDetails) throws LoginException {
        int customerID = customerService.login(loginDetails.getEmail(), loginDetails.getPassword());
        if (customerID == 0) {
            throw new LoginException("invalid user");
        }
        return new ResponseEntity<>(jwtUtils.generateToken(new UserDetails(loginDetails.getEmail(), loginDetails.getPassword(), this.role, customerID)), HttpStatus.OK);
    }

    @PostMapping("purchaseCoupon/{couponId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void purchaseCoupon(@RequestHeader(name = "Authorization") String token, @PathVariable int couponId) throws PurchaseException, JwtException {
        UserDetails userDetails = jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new JwtException("Invalid user");
        }
        customerService.purchaseCoupon(userDetails.getId(),couponId);
    }

    @GetMapping("customerCoupons")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader(name = "Authorization") String token) throws JwtException {
        UserDetails userDetails = jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new JwtException("Invalid user");
        }
        return new ResponseEntity<>(customerService.getCustomerCoupons(userDetails.getId()), HttpStatus.ACCEPTED);
    }

    @GetMapping("customerCouponsByCategory/{categoryId}")
    public ResponseEntity<?> getCustomerCouponsByCategory(@RequestHeader(name = "Authorization") String token, @PathVariable Category categoryId) throws JwtException {
        UserDetails userDetails = jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new JwtException("Invalid user");
        }
        return new ResponseEntity<>(customerService.getCustomerCouponsByCategory(userDetails.getId(),categoryId), HttpStatus.OK);
    }

    @GetMapping("customerCouponsTillMaxPrice/{maxPrice}")
    public ResponseEntity<?> getCustomerCouponsTillMaxPrice(@RequestHeader(name = "Authorization") String token, @PathVariable double maxPrice) throws JwtException {
        UserDetails userDetails = jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new JwtException("Invalid user");
        }
        return new ResponseEntity<>(customerService.getCustomerCouponsTillMaxPrice(userDetails.getId(),maxPrice), HttpStatus.ACCEPTED);

    }

    @GetMapping("customerDetails")
    public ResponseEntity<?> customerDetails(@RequestHeader(name = "Authorization") String token) throws JwtException {
        UserDetails userDetails = jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new JwtException("Invalid user");
        }
        return new ResponseEntity<>(customerService.getCustomerDetails(userDetails.getId()), HttpStatus.OK);
    }
}
