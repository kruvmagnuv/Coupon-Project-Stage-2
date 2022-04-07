package com.smrt.CouponProject.controllers;


import com.smrt.CouponProject.beans.Category;
import com.smrt.CouponProject.beans.Coupon;
import com.smrt.CouponProject.beans.LoginDetails;
import com.smrt.CouponProject.beans.UserDetails;
import com.smrt.CouponProject.exceptions.CompanyException;
import com.smrt.CouponProject.exceptions.JwtException;
import com.smrt.CouponProject.exceptions.LoginException;
import com.smrt.CouponProject.services.AdminService;
import com.smrt.CouponProject.services.ClientService;
import com.smrt.CouponProject.services.CompanyService;
import com.smrt.CouponProject.jwt.JWTUtils;
import com.smrt.CouponProject.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private final JWTUtils jwtUtils;

    private String role = "Company";

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDetails loginDetails) throws LoginException {
        int companyID = companyService.login(loginDetails.getEmail(), loginDetails.getPassword());
        if (companyID == 0) {
            throw new LoginException("invalid user");
        }
        return new ResponseEntity<>(jwtUtils.generateToken(new UserDetails(loginDetails.getEmail(), loginDetails.getPassword(), this.role, companyID)), HttpStatus.OK);
    }


    @PostMapping("addCoupon")
    public ResponseEntity<?> addCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws LoginException, CompanyException, JwtException {
        UserDetails userDetails = jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new JwtException("Invalid User");
        }
        companyService.addCoupon(userDetails.getId(), coupon);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("updateCoupon")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void updateCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws LoginException, CompanyException, JwtException {
        UserDetails userDetails = jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new JwtException("Invalid User");
        }
        companyService.updateCoupon(userDetails.getId(), coupon);
    }

    @DeleteMapping("deleteCoupon/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteCoupon(@RequestHeader(name = "Authorization") String token, @PathVariable int couponId) throws LoginException, CompanyException, JwtException {
        UserDetails userDetails = jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new JwtException("Invalid User");
        }
        companyService.deleteCoupon(userDetails.getId(), couponId);
    }

    @GetMapping("allCoupons")
    public ResponseEntity<?> getAllCoupons(@RequestHeader(name = "Authorization") String token) throws LoginException, CompanyException, JwtException {
        UserDetails userDetails = jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new JwtException("Invalid User");
        }
        return new ResponseEntity<>(companyService.getCompanyCoupons(userDetails.getId()), HttpStatus.OK);

    }

    @GetMapping("CouponsByCategory/{categoryId}")
    public ResponseEntity<?> getCouponsByCategory(@RequestHeader(name = "Authorization") String token, @PathVariable Category categoryId) throws LoginException, CompanyException, JwtException {
        UserDetails userDetails = jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new JwtException("Invalid User");
        }
        return new ResponseEntity<>(companyService.getCompanyCouponsByCategory(userDetails.getId(), categoryId), HttpStatus.OK);
    }

    @GetMapping("couponsTillMaxPrice/{maxPrice}")
    public ResponseEntity<?> getCouponsTillMaxPrice(@RequestHeader(name = "Authorization") String token, @PathVariable double maxPrice) throws LoginException, CompanyException, JwtException {
        UserDetails userDetails = jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new JwtException("Invalid User");
        }
        return new ResponseEntity<>(companyService.getCompanyCouponsTillMaxPrice(userDetails.getId(), maxPrice), HttpStatus.OK);
    }

    @GetMapping("companyDetails")
    public ResponseEntity<?> companyDetails(@RequestHeader(name = "Authorization") String token) throws LoginException, CompanyException, JwtException {
        UserDetails userDetails = jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new JwtException("Invalid User");
        }
        return new ResponseEntity<>(companyService.getCompanyDetails(userDetails.getId()), HttpStatus.OK);
    }
}
