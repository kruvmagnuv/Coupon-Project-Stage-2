package com.smrt.CouponProject.controllers;

import com.smrt.CouponProject.beans.Company;
import com.smrt.CouponProject.beans.Customer;
import com.smrt.CouponProject.beans.LoginDetails;
import com.smrt.CouponProject.beans.UserDetails;
import com.smrt.CouponProject.exceptions.AdministrationException;
import com.smrt.CouponProject.exceptions.JwtException;
import com.smrt.CouponProject.exceptions.LoginException;
import com.smrt.CouponProject.jwt.JWTUtils;
import com.smrt.CouponProject.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
    private final String role = "Admin";
    private final AdminService adminService;
    private final JWTUtils jwtUtils;


    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDetails loginDetails) throws LoginException {
        if (!adminService.login(loginDetails.getEmail(),loginDetails.getPassword())) {
            throw new LoginException("invalid user");

        }
        return new ResponseEntity<>(jwtUtils.generateToken(new UserDetails(loginDetails.getEmail(),loginDetails.getPassword(),role,0)), HttpStatus.OK);
    }



    @PostMapping("addCompany")
    public ResponseEntity<?> addCompany(@RequestHeader(name = "Authorization") String token, @RequestBody Company company) throws LoginException, JwtException {
        UserDetails userDetails=jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new LoginException("Invalid User");
        }
        adminService.addCompany(company);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("updateCompany")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void updateCompany(@RequestHeader(name = "Authorization") String token, @RequestBody Company company) throws AdministrationException, LoginException, JwtException {
         UserDetails userDetails=jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new LoginException("Invalid User");
        }
        adminService.updateCompany(company);
    }

    @DeleteMapping("deleteCompany/{id}")
    public ResponseEntity<?> deleteCompany(@RequestHeader(name = "Authorization") String token, @PathVariable int companyID) throws AdministrationException, LoginException, JwtException {
         UserDetails userDetails=jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new LoginException("Invalid User");
        }
        adminService.deleteCompany(companyID);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("allCompanies")
    public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "Authorization") String token) throws LoginException, JwtException {
         UserDetails userDetails=jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new LoginException("Invalid User");
        }
        return new ResponseEntity<>(adminService.getAllCompanies(), HttpStatus.OK);
    }

    @GetMapping("getCompany/{id}")
    public ResponseEntity<?> getOneCompany(@RequestHeader(name = "Authorization") String token, @PathVariable int companyID) throws AdministrationException, LoginException, JwtException {
         UserDetails userDetails=jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new LoginException("Invalid User");
        }
        return new ResponseEntity<>(adminService.getOneCompany(companyID), HttpStatus.OK);
    }

    @PostMapping("addCustomer")
    public ResponseEntity<?> addCustomer(@RequestHeader(name = "Authorization") String token, @RequestBody Customer customer) throws LoginException, JwtException {
         UserDetails userDetails=jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new LoginException("Invalid User");
        }
        adminService.addCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("updateCustomer")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void updateCompany(@RequestHeader(name = "Authorization") String token, @RequestBody Customer customer) throws AdministrationException, LoginException, JwtException {
         UserDetails userDetails=jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new LoginException("Invalid User");
        }
        adminService.updateCustomer(customer);
    }

    @DeleteMapping("deleteCustomer/{id}")
    public ResponseEntity<?> deleteCustomer(@RequestHeader(name = "Authorization") String token, @PathVariable int customerID) throws AdministrationException, LoginException, JwtException {
         UserDetails userDetails=jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new LoginException("Invalid User");
        }
        adminService.deleteCustomer(customerID);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("allCustomers")
    public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "Authorization") String token) throws  LoginException, JwtException {
         UserDetails userDetails=jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new LoginException("Invalid User");
        }
        return new ResponseEntity<>(adminService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("getCustomer/{id}")
    public ResponseEntity<?> getOneCustomer(@RequestHeader(name = "Authorization") String token, @PathVariable int customerID) throws AdministrationException, LoginException, JwtException {
         UserDetails userDetails=jwtUtils.validateToken(token);
        if (!userDetails.getRole().equals(role)) {
            throw new LoginException("Invalid User");
        }
        return new ResponseEntity<>(adminService.getOneCustomer(customerID), HttpStatus.OK);
    }
}
