import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/Models/coupon';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-get-coupons-by-date',
  templateUrl: './get-coupons-by-date.component.html',
  styleUrls: ['./get-coupons-by-date.component.css']
})
export class GetCouponsByDateComponent implements OnInit {

  public coupons : Coupon[];

  constructor(private companyService : CompanyService) { }

  public getCouponsByDate(endDate : Date) : void {
    this.companyService.getCouponsByDate(endDate).subscribe(coupons => {
      setTimeout(() => this.coupons = coupons, 300);
    }, err => {
      alert("Error: " + err.message);
    });

  }

  ngOnInit() {
  }

}
