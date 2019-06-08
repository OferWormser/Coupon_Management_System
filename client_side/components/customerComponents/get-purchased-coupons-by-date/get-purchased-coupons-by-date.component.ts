import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/Models/coupon';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-get-purchased-coupons-by-date',
  templateUrl: './get-purchased-coupons-by-date.component.html',
  styleUrls: ['./get-purchased-coupons-by-date.component.css']
})
export class GetPurchasedCouponsByDateComponent implements OnInit {

  public coupons : Coupon[];

  constructor(private customerService : CustomerService) { }

  public getPurchasedCouponsByDate(endDate : Date) : void {
    this.customerService.getPurchasedCouponsByDate(endDate).subscribe(coupons => {
      setTimeout(() => this.coupons = coupons, 300);
    }, err => {
      alert("Error: " + err.message);
    });

  }

  ngOnInit() {
  }

}
