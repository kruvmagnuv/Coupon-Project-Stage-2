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

    /**
     * this function creates a new company and adds her to the database
     * @param company a company.
     */
    public void addCompany(Company company){

        companyRepo.save(company);
    }

    /**
     * this function updates an existing company
     * @param company a company
     * @throws AdministrationException if the company's companyID doesnt exist.
     */
    public void updateCompany(Company company) throws AdministrationException {
        if (!companyRepo.existsById(company.getId())) {
            throw new AdministrationException(COMPANY_NOT_EXIST_EXCEPTION + UPDATE_EXCEPTION);
        }
        companyRepo.save(company);
    }

    /**
     * this function  deletes a company by id
     * @param companyID company's id
     * @throws AdministrationException if companyID doesnt exist
     */
    public void deleteCompany(int companyID) throws AdministrationException {
        if (!companyRepo.existsById(companyID)) {
            throw new AdministrationException(COMPANY_NOT_EXIST_EXCEPTION + DELETE_EXCEPTION);
        }
        companyRepo.deleteById(companyID);
    }

    /**
     *this function shows all companies that exist in the database.
     * @return all companies that exist in the database.
     */
    public List<Company> getAllCompanies(){
        return companyRepo.findAll();
    }

    /**
     * this function shows one company from database by id
     * @param companyID company's id.
     * @return a company.
     * @throws AdministrationException if companyID doesnt exist.
     */
    public Company getOneCompany(int companyID) throws AdministrationException {
        if (!companyRepo.existsById(companyID)) {
            throw new AdministrationException(COMPANY_NOT_EXIST_EXCEPTION);
        }
        return companyRepo.getById(companyID);
    }

    /**
     * this function creates a customer and adds him to the database
     * @param customer a customer
     */
    public void addCustomer(Customer customer) {
        customerRepo.save(customer);
    }

    /**
     * this function updates customer details
     * @param customer a customer
     * @throws AdministrationException
     */
    public void updateCustomer(Customer customer) throws AdministrationException {
        if (!customerRepo.existsById(customer.getId())) {
            throw new AdministrationException(CUSTOMER_NOT_EXIST_EXCEPTION + UPDATE_EXCEPTION);
        }
        customerRepo.saveAndFlush(customer);
    }

    /**
     * this function deletes a customer from database
     * @param customerID customer's id
     * @throws AdministrationException if customerID doesnt exist.
     */
    public void deleteCustomer(int customerID) throws AdministrationException {
        if (!customerRepo.existsById(customerID)) {
            throw new AdministrationException(CUSTOMER_NOT_EXIST_EXCEPTION + DELETE_EXCEPTION);
        }
        customerRepo.deleteById(customerID);
    }

    /**
     * this function shows all customer that in the database
     * @return all customer that in the database
     */
    public List<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }

    /**
     * this function shows one customer from database by id
     * @param customerID customer is
     * @return one customer from database by id
     * @throws AdministrationException if customerID doesnt exist
     */
    public Customer getOneCustomer(int customerID) throws AdministrationException {
        if (!customerRepo.existsById(customerID)) {
            throw new AdministrationException(CUSTOMER_NOT_EXIST_EXCEPTION);
        }
        return customerRepo.getById(customerID);
    }
}
