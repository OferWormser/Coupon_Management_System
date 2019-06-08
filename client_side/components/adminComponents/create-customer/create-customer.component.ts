import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/Models/customer';
import { AdminService } from 'src/app/services/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.css']
})
export class CreateCustomerComponent implements OnInit {
 
  public customer = new Customer();

  constructor(private router: Router, private adminService : AdminService) { }

  public createCustomer() {
    alert(`
    CustomerName: ${this.customer.custName}
    CustomerPassword: ${this.customer.password}
    `);

    this.adminService.createCustomer(this.customer).subscribe( c => {
       alert(c.custName + " has been succesfully added!");
       this.router.navigate(["rest/admin/customers"]);
     }, err => {
       alert("Error: " + err.message);
     });
  }

  ngOnInit() {
  }

}
