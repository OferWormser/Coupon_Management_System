import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/Models/coupon';
import { CompanyService } from 'src/app/services/company.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-coupon',
  templateUrl: './create-coupon.component.html',
  styleUrls: ['./create-coupon.component.css']
})
export class CreateCouponComponent implements OnInit {

  public coupon = new Coupon();

  constructor(private router: Router, private companyService : CompanyService) { }

  public createCoupon() : void {
    alert(`
    CouponTitle: ${this.coupon.title}
    CouponPrice: ${this.coupon.price}
    CouponAmount: ${this.coupon.amount}
    CouponStartDate: ${this.coupon.startDate}
    CouponEndDate: ${this.coupon.endDate}
    CouponMessage: ${this.coupon.message}
    CouponImageUrl: ${this.coupon.image}
    CouponType: ${this.coupon.type}
    `);

    this.companyService.createCoupon(this.coupon).subscribe( c => {
       alert(c.title + " has been succesfully added!");
       this.router.navigate(["rest/company/coupons"]);
     }, err => {
       alert("Error: " + err.message);
       console.log(err.message);
     });
  }

  ngOnInit() {
  }

}
