package com.smrt.CouponProject.services;

import com.smrt.CouponProject.beans.Company;
import com.smrt.CouponProject.beans.Customer;
import com.smrt.CouponProject.exceptions.AdministrationException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class AdminService extends ClientService {
    // There is only one admin, so we can write his email and password here hardcoded
    // Admin email    - HARDCODED!!!
    private static final String ADMIN_USERNAME = "admin@admin.com";
    // Admin password - HARDCODED!!!
    private static final String ADMIN_PASSWORD = "admin";


    /**
     * checks if the login arguments are correct.
     *
     * @param email    Admins email.
     * @param password admins password.
     * @return whether credentials are correct.
     */
    public boolean login(String email, String password) {
        // Checking if the given email and password matching the hardcoded email and password above
        return email.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD);
    }

    public void addCompany(Company company){

        companyRepo.save(company);
    }

    public void updateCompany(Company company) throws AdministrationException {
        if (!companyRepo.existsById(company.getId())) {
            throw new AdministrationException(COMPANY_NOT_EXIST_EXCEPTION + UPDATE_EXCEPTION);
        }
        companyRepo.save(company);
    }

    public void deleteCompany(int companyID) throws AdministrationException {
        if (!companyRepo.existsById(companyID)) {
            throw new AdministrationException(COMPANY_NOT_EXIST_EXCEPTION + DELETE_EXCEPTION);
        }
        companyRepo.deleteById(companyID);
    }

    public List<Company> getAllCompanies(){
        return companyRepo.findAll();
    }

    public Company getOneCompany(int companyID) throws AdministrationException {
        if (!companyRepo.existsById(companyID)) {
            throw new AdministrationException(COMPANY_NOT_EXIST_EXCEPTION);
        }
        return companyRepo.getById(companyID);
    }

    public void addCustomer(Customer customer) {
        customerRepo.save(customer);
    }

    public void updateCustomer(Customer customer) throws AdministrationException {
        if (!customerRepo.existsById(customer.getId())) {
            throw new AdministrationException(CUSTOMER_NOT_EXIST_EXCEPTION + UPDATE_EXCEPTION);
        }
        customerRepo.saveAndFlush(customer);
    }

    public void deleteCustomer(int customerID) throws AdministrationException {
        if (!customerRepo.existsById(customerID)) {
            throw new AdministrationException(CUSTOMER_NOT_EXIST_EXCEPTION + DELETE_EXCEPTION);
        }
        customerRepo.deleteById(customerID);
    }

    public List<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }

    public Customer getOneCustomer(int customerID) throws AdministrationException {
        if (!customerRepo.existsById(customerID)) {
            throw new AdministrationException(CUSTOMER_NOT_EXIST_EXCEPTION);
        }
        return customerRepo.getById(customerID);
    }
}
