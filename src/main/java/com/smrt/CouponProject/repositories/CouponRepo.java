package com.smrt.CouponProject.repositories;

import com.smrt.CouponProject.beans.Category;
import com.smrt.CouponProject.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Integer> {
    public List<Coupon> findByCompanyID(int companyID);

    public List<Coupon> findByCompanyIDAndCategory(int companyID, Category category);

    public List<Coupon> findByCompanyIDAndPriceLessThanEqual(int companyID, double maxPrice);

    public void deleteByEndDateBefore(Date date);


}
