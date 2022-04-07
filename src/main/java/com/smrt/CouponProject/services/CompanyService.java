package com.smrt.CouponProject.services;

import com.smrt.CouponProject.beans.Category;
import com.smrt.CouponProject.beans.Company;
import com.smrt.CouponProject.beans.Coupon;
import com.smrt.CouponProject.exceptions.CompanyException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class CompanyService extends ClientService {



    /**
     * checks if the login arguments are correct.
     *
     * @param email    company email.
     * @param password company password.
     * @return whether arguments are correct.
     */
    public int login(String email, String password) {
        // First, we check if there is a company with those email and password
        if (companyRepo.existsCompanyByEmailAndPassword(email, password)) {
            return companyRepo.getByEmail(email).getId();
        }
        // If there isn't a matching company, the login failed and the function returns false
        return 0;
    }

    public void addCoupon(int companyID,Coupon coupon)  {
        coupon.setCompanyID(companyID);
        couponRepo.save(coupon);
    }

    public void updateCoupon(int companyID,Coupon coupon) throws CompanyException {
        Optional<Coupon> coupon1 = couponRepo.findById(coupon.getId());
        if (coupon1.isEmpty()) {
            throw new CompanyException(COUPON_NOT_EXIST_EXCEPTION+UPDATE_EXCEPTION);
        }
        if (coupon1.get().getCompanyID()!=companyID){
            throw new CompanyException("Not your coupon.");
        }
        couponRepo.saveAndFlush(coupon);
    }

    public void deleteCoupon(int companyID,int couponID) throws CompanyException {
        Optional<Coupon> coupon1 = couponRepo.findById(couponID);
        if (coupon1.isEmpty()) {
            throw new CompanyException(COUPON_NOT_EXIST_EXCEPTION+UPDATE_EXCEPTION);
        }
        if (coupon1.get().getCompanyID()!=companyID){
            throw new CompanyException("Not your coupon.");
        }
        couponRepo.deleteById(couponID);
    }

    public List<Coupon> getCompanyCoupons(int companyID)  {
        return couponRepo.findByCompanyID(companyID);
    }

    public List<Coupon> getCompanyCouponsByCategory(int companyID,Category category) {
        return couponRepo.findByCompanyIDAndCategory(companyID, category);
    }

    public List<Coupon> getCompanyCouponsTillMaxPrice(int companyID,double maxPrice)  {
        return couponRepo.findByCompanyIDAndPriceLessThanEqual(companyID, maxPrice);
    }

    public Company getCompanyDetails(int companyID)  {
        return companyRepo.getById(companyID);
    }
}