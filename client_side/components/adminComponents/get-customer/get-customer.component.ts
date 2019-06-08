import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/Models/customer';
import { AdminService } from 'src/app/services/admin.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-get-customer',
  templateUrl: './get-customer.component.html',
  styleUrls: ['./get-customer.component.css']
})
export class GetCustomerComponent implements OnInit {

  public customer = new Customer();
  
  constructor(private router: Router, private activatedRoute: ActivatedRoute, private adminService : AdminService) { }

  ngOnInit() {
    const id = +this.activatedRoute.snapshot.params.id;
    this.adminService.getCustomer(id).subscribe( customer => {
      setTimeout(() => this.customer = customer, 300);
    }, err => {
      alert("Error: " + err.message);
    });
  }

  public removeCustomer() {
     this.adminService.removeCustomer(this.customer.id).subscribe( customer => {
      alert("Customer has been succesfully updated!");
      this.router.navigate(["rest/admin/customers"]);
    }, err => {
      alert("Error: " + err.message);
    });
  }

  public updateCustomer(newPassword : string) {
    alert(`
    CustomerPassword: ${newPassword}
    `);
    this.customer.password = newPassword;
    this.adminService.updateCustomer(this.customer).subscribe( customer => {
       alert("Customer has been succesfully updated!");
     }, err => {
       alert("Error: " + err.message);
     });
  }


}
