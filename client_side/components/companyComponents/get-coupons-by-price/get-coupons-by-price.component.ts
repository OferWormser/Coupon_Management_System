import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/Models/coupon';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-get-coupons-by-price',
  templateUrl: './get-coupons-by-price.component.html',
  styleUrls: ['./get-coupons-by-price.component.css']
})
export class GetCouponsByPriceComponent implements OnInit {

  public coupons : Coupon[];

  constructor(private companyService : CompanyService) { }

  public getCouponsByPrice(price : number) : void {
    this.companyService.getCouponsByPrice(price).subscribe(coupons => {
      setTimeout(() => this.coupons = coupons, 300);
    }, err => {
      alert("Error: " + err.message);
    });

  }

  ngOnInit() {
  }

}
