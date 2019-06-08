import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Coupon } from '../Models/coupon';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private httpClient: HttpClient) { }

  public purchaseCoupon (couponId : number) : Observable<Coupon> {
    return this.httpClient.post<Coupon>("http://localhost:8080/rest/customer/" + couponId, {withCredentials : true});
  }

  public getPurchasedCoupon (couponId : number) : Observable<Coupon> {
    return this.httpClient.get<Coupon>("http://localhost:8080/rest/customer/" + couponId, {withCredentials:true});
  }

  public getAllPurchasedCoupons() : Observable<Coupon[]> {
    return this.httpClient.get<Coupon[]>("http://localhost:8080/rest/customer/purchasedCoupons", {withCredentials:true});
  }

  public getPurchasedCouponsByType(couponType : string) : Observable<Coupon[]> {
    return this.httpClient.get<Coupon[]>("http://localhost:8080/rest/customer/coupons/byType/" + couponType, {withCredentials:true}); 
  }

  public getPurchasedCouponsByPrice(couponPrice : number) : Observable<Coupon[]> {
    return this.httpClient.get<Coupon[]>("http://localhost:8080/rest/customer/coupons/byPrice/" + couponPrice, {withCredentials:true}); 
  }

  public getPurchasedCouponsByDate(couponEndDate : Date) : Observable<Coupon[]> {
    return this.httpClient.get<Coupon[]>("http://localhost:8080/rest/customer/coupons/byDate/" + couponEndDate, {withCredentials:true}); 
  }

  public getAllCoupons() : Observable<Coupon[]> {
    return this.httpClient.get<Coupon[]>("http://localhost:8080/rest/customer/coupons", {withCredentials:true});
  }

}
