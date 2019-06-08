import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/Models/coupon';
import { CustomerService } from 'src/app/services/customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-purchase-coupon',
  templateUrl: './purchase-coupon.component.html',
  styleUrls: ['./purchase-coupon.component.css']
})
export class PurchaseCouponComponent implements OnInit {

  public coupon = new Coupon();
  
  public coupons : Coupon[];

  constructor(private router : Router, private customerService : CustomerService) { }

  ngOnInit() {
    this.customerService.getAllCoupons().subscribe(coupons => {
      setTimeout(() => this.coupons = coupons, 300);
    }, err => {
      alert("Error: " + err.message);
    });

  }

  public purchaseCoupon(id : number) : void {

    this.customerService.purchaseCoupon(id).subscribe( () => {
       alert("Coupon purchased!");
       this.router.navigate(["rest/customer/getAllPurchasedCoupons"]);
     }, err => {
       alert("Error: " + err.message);
     });

  }

}
