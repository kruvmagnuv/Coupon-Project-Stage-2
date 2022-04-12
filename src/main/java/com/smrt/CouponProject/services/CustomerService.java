package com.smrt.CouponProject.services;

import com.smrt.CouponProject.beans.Category;
import com.smrt.CouponProject.beans.Coupon;
import com.smrt.CouponProject.beans.Customer;
import com.smrt.CouponProject.exceptions.PurchaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService extends ClientService {

    /**
     * checks if the login arguments are correct.
     * @param email    company email.
     * @param password company password.
     * @return customer's ID.
     */
    public int login(String email, String password) {
        // First, we check if there is a customer with those email and password
        if (customerRepo.existsCustomerByEmailAndPassword(email, password)) {
            // And the function returns true
            return customerRepo.getCustomerByEmail(email).getId();
        }
        // If there isn't a matching customer, the login failed and the function returns false
        return 0;
    }


    /**
     * purchase a coupon.
     * @param customerID Customer's ID.
     * @param couponID Coupon's ID
     * @throws PurchaseException if purchase failed for any reason.
     */
    public void purchaseCoupon(int customerID,int couponID) throws PurchaseException {

        // First, if the coupon ID doesn't exist (=coupon doesn't exists), you can't buy that coupon
        if (!customerRepo.existsById(couponID)) {
            // An exception is thrown
            throw new PurchaseException(COUPON_NOT_EXIST_EXCEPTION+PURCHASE_EXCEPTION);
        }
        // Next, if the customer already bought that coupon, you can't buy that coupon
        if (customerRepo.getById(customerID).getCoupons().contains(couponRepo.getById(couponID))) {
            // An exception is thrown
            throw new PurchaseException(COUPON_PURCHASED_EXCEPTION+PURCHASE_EXCEPTION);
        }
        // Next, if the coupon amount is 0, you can't buy that coupon
        if (!(couponRepo.getById(couponID).getAmount() > 0)) {
            // An exception is thrown
            throw new PurchaseException(COUPON_OUT_EXCEPTION+PURCHASE_EXCEPTION);
        }
        // Next, if the coupon end date already passed, you can't buy that coupon
        if (couponRepo.getById(couponID).getEndDate().before(Date.valueOf(LocalDate.now()))) {
            // An exception is thrown
            throw new PurchaseException(COUPON_EXPIRED_EXCEPTION+PURCHASE_EXCEPTION);
        }
        // Now, we decrease the purchased coupon amount by 1
        Coupon coupon = couponRepo.getById(couponID);
        coupon.setAmount(coupon.getAmount() - 1);
        couponRepo.saveAndFlush(coupon);
        // And finally purchasing the coupon
        Customer customer = customerRepo.getById(customerID);
        customer.getCoupons().add(coupon);
        customerRepo.saveAndFlush(customer);

    }

    /**
     * get all purchases made by a customer.
     * @param customerID customer's ID.
     * @return list of coupons.
     */
    public List<Coupon> getCustomerCoupons(int customerID) {

        return customerRepo.getById(customerID).getCoupons();
    }

    /**
     * returns all coupons of a certain category purchased by customer.
     * @param customerID customer's ID.
     * @param category coupon's category.
     * @return list of coupons.
     */
    public List<Coupon> getCustomerCouponsByCategory(int customerID,Category category)  {

        return customerRepo.getById(customerID).getCoupons().stream().filter(coupon -> coupon.getCategory().equals(category)).collect(Collectors.toList());
    }

    /**
     * get all coupons under a specified price, purchased by a specified customer.
     * @param customerID customer's ID
     * @param maxPrice price ceiling.
     * @return list of coupons.
     */
    public List<Coupon> getCustomerCouponsTillMaxPrice(int customerID,double maxPrice)  {

        return customerRepo.getById(customerID).getCoupons().stream().filter(coupon -> coupon.getPrice()<=maxPrice).collect(Collectors.toList());
    }

    /**
     * get customer details.
     * @param customerID customer's ID.
     * @return customer details.
     */
    public Customer getCustomerDetails(int customerID) {

        return customerRepo.getById(customerID);
    }
}

