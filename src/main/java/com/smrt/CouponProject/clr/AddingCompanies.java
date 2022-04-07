package com.smrt.CouponProject.clr;

import com.smrt.CouponProject.beans.Company;
import com.smrt.CouponProject.repositories.CompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(1)
@RequiredArgsConstructor
public class AddingCompanies implements CommandLineRunner {
    private final CompanyRepo companyRepo;

    @Override
    public void run(String... args) throws Exception {
        List<Company> companies = new ArrayList<>();
        Company tal = Company.builder()
                .email("tal@tal.com")
                .password("tal")
                .name("Tal")
                .build();
        companies.add(tal);
        Company roei = Company.builder()
                .email("roei@roei.com")
                .password("roei")
                .name("Roei")
                .build();
        companies.add(roei);
        Company shiri = Company.builder()
                .email("shiri@shiri.com")
                .password("shiri")
                .name("Shiri")
                .build();
        companies.add(shiri);
        companyRepo.saveAll(companies);
    }
}
