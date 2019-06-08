import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Coupon } from '../Models/coupon';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private httpClient: HttpClient) { }

  public createCoupon(coupon : Coupon) : Observable<Coupon> {
    return this.httpClient.post<Coupon>("http://localhost:8080/rest/company", coupon, { withCredentials: true})
  }

  public removeCoupon(couponId: number) : Observable<Coupon> {
    return this.httpClient.delete<Coupon>("http://localhost:8080/rest/company/coupons/" + couponId, { withCredentials: true});
  }

  public updateCoupon(coupon: Coupon) : Observable<Coupon> {
    return this.httpClient.put<Coupon>("http://localhost:8080/rest/company/coupons", coupon, { withCredentials: true});
  }

  public getCoupon(couponId: number) : Observable<Coupon> {
    return this.httpClient.get<Coupon>("http://localhost:8080/rest/company/coupons/" + couponId, { withCredentials: true});
  }

  public getAllCoupons() : Observable<Coupon[]> {
    return this.httpClient.get<Coupon[]>("http://localhost:8080/rest/company/coupons", { withCredentials: true});
  }

  public getCouponsByType(couponType : string) : Observable<Coupon[]> {
    return this.httpClient.get<Coupon[]>("http://localhost:8080/rest/company/coupons/byType/" + couponType, { withCredentials: true});
  }
  public getCouponsByPrice(couponPrice : number) : Observable<Coupon[]> {
    return this.httpClient.get<Coupon[]>("http://localhost:8080/rest/company/coupons/byPrice/" + couponPrice, { withCredentials: true});
  }
  public getCouponsByDate(couponEndDate : Date) : Observable<Coupon[]> {
    return this.httpClient.get<Coupon[]>("http://localhost:8080/rest/company/coupons/byDate/" + couponEndDate, { withCredentials: true});
  }


}
