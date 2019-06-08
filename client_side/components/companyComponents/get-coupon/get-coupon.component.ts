import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/Models/coupon';
import { CompanyService } from 'src/app/services/company.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-get-coupon',
  templateUrl: './get-coupon.component.html',
  styleUrls: ['./get-coupon.component.css']
})
export class GetCouponComponent implements OnInit {

  public coupon = new Coupon();
  
  constructor(private router: Router, private activatedRoute: ActivatedRoute, private companyService : CompanyService) { }

  ngOnInit() {
    const id = +this.activatedRoute.snapshot.params.id;
    this.companyService.getCoupon(id).subscribe( coupon => {
      setTimeout(() => this.coupon = coupon, 300);
    }, err => {
      alert("Error: " + err.message);
    });
  }

  public removeCoupon() : void {
    this.companyService.removeCoupon(this.coupon.id).subscribe( coupon => {
      alert("Coupon has been removed!");
      this.router.navigate(["rest/company/coupons"]);
    }, err => {
      alert("Error: " + err);
    });

  }

  public updateCoupon(newPrice : number, newEndDate : Date) : void {
    alert(`
    Coupon Price: ${newPrice}
    Coupon End Date: ${newEndDate}
    `);
    this.coupon.price = newPrice;
    this.coupon.endDate = newEndDate;
    this.companyService.updateCoupon(this.coupon).subscribe( coupon => {
       alert("Coupon has been succesfully updated!");
     }, err => {
       alert("Error: " + err.message);
     });
  }


}
