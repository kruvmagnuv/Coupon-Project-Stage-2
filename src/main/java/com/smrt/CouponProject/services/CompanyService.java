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

    /**
     * this function creates a coupon and adds it to the database
     * @param companyID ID of company using this method
     * @param coupon the coupon you want to create.
     */
    public void addCoupon(int companyID,Coupon coupon)  {
        coupon.setCompanyID(companyID);
        couponRepo.save(coupon);
    }

    /**
     * this function updates an existing company
     * @param companyID a company id
     * @param coupon specific coupon
     * @throws CompanyException if Coupon doesn't exist, or the coupon doesn't belong to your company.
     */
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

    /**
     * deletes an existing coupon, and all it's purchases.
     * @param companyID ID of company requesting the deletion.
     * @param couponID ID of coupon to be deleted.
     * @throws CompanyException if the coupon doesnt belong to your company.
     */
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

    /**
     * get all company coupons.
     * @param companyID company's ID.
     * @return all coupons owned by this company.
     */
    public List<Coupon> getCompanyCoupons(int companyID)  {
        return couponRepo.findByCompanyID(companyID);
    }

    /**
     * get company coupons filtered by category
     * @param companyID company's ID.
     * @param category coupon's category.
     * @return list of company coupons, that have the given category
     */
    public List<Coupon> getCompanyCouponsByCategory(int companyID,Category category) {
        return couponRepo.findByCompanyIDAndCategory(companyID, category);
    }

    /**
     * get company coupons with a specific price ceiling.
     * @param companyID company's ID.
     * @param maxPrice price ceiling.
     * @return list of coupons.
     */
    public List<Coupon> getCompanyCouponsTillMaxPrice(int companyID,double maxPrice)  {
        return couponRepo.findByCompanyIDAndPriceLessThanEqual(companyID, maxPrice);
    }

    /**
     *  get company details
     * @param companyID company's ID.
     * @return company details.
     */
    public Company getCompanyDetails(int companyID)  {
        return companyRepo.getById(companyID);
    }
}