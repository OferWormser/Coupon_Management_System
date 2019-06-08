import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/Models/coupon';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-get-purchased-coupons-by-type',
  templateUrl: './get-purchased-coupons-by-type.component.html',
  styleUrls: ['./get-purchased-coupons-by-type.component.css']
})
export class GetPurchasedCouponsByTypeComponent implements OnInit {

  public coupons : Coupon[];

  constructor(private customerService : CustomerService) { }

  public getPurchasedCouponsByType(type : string) : void {
    this.customerService.getPurchasedCouponsByType(type).subscribe(coupons => {
      setTimeout(() => this.coupons = coupons, 300);
    }, err => {
      alert("Error: " + err.message);
    });

  }

  ngOnInit() {
  }

}
