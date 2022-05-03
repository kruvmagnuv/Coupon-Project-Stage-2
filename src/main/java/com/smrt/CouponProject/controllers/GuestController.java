package com.smrt.CouponProject.controllers;

import com.smrt.CouponProject.repositories.CouponRepo;
import com.smrt.CouponProject.services.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin
public class GuestController {
    @Autowired
    private GuestService guestService;

    @GetMapping("allCoupons")
    public ResponseEntity<?> getAllCoupons() {
        return new ResponseEntity<>(guestService.getAllCoupons(),HttpStatus.OK);
    }

    @GetMapping("oneCoupon/{id}")
    public ResponseEntity<?> getOneCoupon(@PathVariable int id) {
        return new ResponseEntity<>(guestService.getOneCoupon(id),HttpStatus.OK);
    }
}
