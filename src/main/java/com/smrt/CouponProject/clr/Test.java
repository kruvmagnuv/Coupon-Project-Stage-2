package com.smrt.CouponProject.clr;

import com.smrt.CouponProject.beans.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.smrt.CouponProject.utils.HttpUtils.getRequest;

//@Component
@Order(4)
@RequiredArgsConstructor
public class Test implements CommandLineRunner {
    private final RestTemplate myRest;
    private Map<String, Object> map;
    private Map<String, Object> pathVariables;
    private HttpEntity<String> myRequest;
    private String myJWT;

    @Override
    public void run(String... args) throws Exception {
        testAll();
    }

    private void testAll() {
        adminFunctions();
        companyFunctions();
        customerFunctions();
    }

    private void adminFunctions() {
//        Failed login
//        Failed JWT

//        Adding existing company (by email)
//        Adding existing company (by name)
//        Update company (regular)
//        Update company (doesn't exist)
//        Delete company (doesn't exist)
//        Delete company (regular)
//        Get all companies (regular)
//        Get one company (regular)
//        Get one company (doesn't exist)
//        Adding existing customer (by email)
//        Update Customer (regular)
//        Update customer (doesn't exist)
//        Delete customer (regular)
//        Delete customer (doesn't exist)
//        Get all customer (regular)
//        Get one customer (regular)
//        Get one customer (doesn't exist)
    }

    private void companyFunctions() {
//        Failed login
//        Failed JWT

//        Adding existing coupon (by name)
//        Adding existing coupon
//        Update coupon (regular)
//        Update coupon (doesn't exist)
//        Delete coupon (regular)
//        Delete coupon (doesn't exist)
//        Get company coupons (regular)
//        Get company coupons by category (regular)
//        Get company coupons by max price (regular)
//        Get company details (regular)
    }

    private void customerFunctions() {
        String CustomerLoginURL = "http://localhost:8080/customer/login";
        String CustomerPurchaseURL = "http://localhost:8080/customer/purchaseCoupon/{couponId}";
        String CustomerCouponsURL = "http://localhost:8080/customer/customerCoupons";
        String CustomerCouponsCategoryURL = "http://localhost:8080/customer/customerCouponsByCategory/{categoryId}";
        String CustomerCouponsMaxPriceURL = "http://localhost:8080/customer/customerCouponsTillMaxPrice/{maxPrice}";
        String CustomerDetailsURL = "http://localhost:8080/customer/customerDetails";

//      Failed login
        System.out.println("\nFailed Login:");
        map = new HashMap<>();
        map.put("email", "tal");
        map.put("password", "talking");
        System.out.println("Login Details:\n" + map);
        myRequest = getRequest(map);
        myJWT = myRest.postForObject(CustomerLoginURL, myRequest, String.class);
        System.out.println("JWT: " + myJWT);

//      Failed JWT
        System.out.println("\nFailed JWT:");
        myJWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        System.out.println("JWT: " + myJWT);
        Customer customer = myRest.getForObject(CustomerDetailsURL, Customer.class);
        System.out.println("Customer:\n" + customer);

//      Login
        System.out.println("\nLogin:");
        map = new HashMap<>();
        map.put("email", "TalHarel@smrt.com");
        map.put("password", "tal_the_king");
        System.out.println("Login Details:\n" + map);
        myRequest = getRequest(map);
        myJWT = myRest.postForObject(CustomerLoginURL, myRequest, String.class);
        System.out.println("JWT: " + myJWT);

//      Purchase coupon (regular)
        System.out.println("\nPurchase Coupon:");
        pathVariables = new HashMap<>();
        pathVariables.put("couponId",2);
        myRest.pos

//      Purchase coupon (doesn't exist)
//      Purchase coupon (already bought)
//      Purchase coupon (expired)
//      Purchase coupon (out of stock)
//      Get customer coupon (regular)
//      Get customer coupon by category (regular)
//      Get customer coupon by max price (regular)
//      Get customer details (regular)
    }
}
