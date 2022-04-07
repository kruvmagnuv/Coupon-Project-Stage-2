package com.smrt.CouponProject.clr;

import com.smrt.CouponProject.beans.Customer;
import com.smrt.CouponProject.repositories.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(2)
@RequiredArgsConstructor
public class AddingCustomers implements CommandLineRunner {
    private final CustomerRepo customerRepo;

    @Override
    public void run(String... args) throws Exception {
        List<Customer> customers = new ArrayList<>();
        Customer matan = Customer.builder()
                .email("matan@matan.com")
                .password("matan")
                .firstName("Matan")
                .lastName("Ozer")
                .build();
        customers.add(matan);
        Customer tal2 = Customer.builder()
                .email("tal@edri.com")
                .password("tal")
                .firstName("Tal")
                .lastName("Edri")
                .build();
        customers.add(tal2);
        Customer nitay = Customer.builder()
                .email("nitay@nitay.com")
                .password("nitay")
                .firstName("Nitay")
                .lastName("Ozer")
                .build();
        customers.add(nitay);
        customerRepo.saveAll(customers);
    }
}
