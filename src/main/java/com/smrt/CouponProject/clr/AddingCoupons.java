package com.smrt.CouponProject.clr;

import com.smrt.CouponProject.beans.Category;
import com.smrt.CouponProject.beans.Coupon;
import com.smrt.CouponProject.beans.Customer;
import com.smrt.CouponProject.repositories.CouponRepo;
import com.smrt.CouponProject.repositories.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Order(3)
@RequiredArgsConstructor
public class AddingCoupons implements CommandLineRunner {
    private final CouponRepo couponRepo;

    @Override
    public void run(String... args) throws Exception {
        List<Coupon> coupons = new ArrayList<>();
        Coupon coupon1 = Coupon.builder()
                .title("TV coupon")
                .description("An amazing TV")
                .companyID(1)
                .category(Category.ELECTRICITY)
                .amount(20)
                .price(55)
                .startDate(Date.from(Instant.now()))
                .endDate(Date.from(Instant.now().plus(14, ChronoUnit.DAYS)))
                .image("images/image")
                .build();
        coupons.add(coupon1);
        Coupon coupon2 = Coupon.builder()
                .title("Computer coupon")
                .description("An amazing computer")
                .companyID(1)
                .category(Category.ELECTRICITY)
                .amount(30)
                .price(45)
                .startDate(Date.from(Instant.now()))
                .endDate(Date.from(Instant.now().plus(14, ChronoUnit.DAYS)))
                .image("images/image")
                .build();
        coupons.add(coupon2);
        couponRepo.saveAll(coupons);
    }
}
