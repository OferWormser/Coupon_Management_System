import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/Models/coupon';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-coupons',
  templateUrl: './coupons.component.html',
  styleUrls: ['./coupons.component.css']
})
export class CouponsComponent implements OnInit {

  public coupons : Coupon[];

  constructor(private companyService : CompanyService) { }

  ngOnInit() {
    this.companyService.getAllCoupons().subscribe(coupons => {
      console.log(coupons);
      setTimeout(() => this.coupons = coupons, 300);
    }, err => {
      alert("Error: " + err.message);
    });

  }


}
