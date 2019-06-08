import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/Models/coupon';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-get-all-purchased-coupons',
  templateUrl: './get-all-purchased-coupons.component.html',
  styleUrls: ['./get-all-purchased-coupons.component.css']
})
export class GetAllPurchasedCouponsComponent implements OnInit {

  public coupons : Coupon[];

  constructor(private customerService : CustomerService) { }

  ngOnInit() {
    this.customerService.getAllPurchasedCoupons().subscribe(coupons => {
      setTimeout(() => this.coupons = coupons, 300);
    }, err => {
      alert("Error: " + err.message);
    });

  }
}
