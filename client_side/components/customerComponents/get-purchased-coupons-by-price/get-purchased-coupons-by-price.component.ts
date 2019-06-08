import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/Models/coupon';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-get-purchased-coupons-by-price',
  templateUrl: './get-purchased-coupons-by-price.component.html',
  styleUrls: ['./get-purchased-coupons-by-price.component.css']
})
export class GetPurchasedCouponsByPriceComponent implements OnInit {

  public coupons : Coupon[];

  constructor(private customerService : CustomerService) { }

  public getPurchasedCouponsByPrice(price : number) : void {
    this.customerService.getPurchasedCouponsByPrice(price).subscribe(coupons => {
      setTimeout(() => this.coupons = coupons, 300);
    }, err => {
      alert("Error: " + err.message);
    });

  }

  ngOnInit() {
  }

}
