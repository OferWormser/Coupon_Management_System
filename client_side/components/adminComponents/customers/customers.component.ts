import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/Models/customer';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {

  public customers : Customer[];

  constructor(private adminService : AdminService) { }

  ngOnInit() {
    this.adminService.getAllCustomers().subscribe(customers => {
      setTimeout(() => this.customers = customers, 300);
    }, err => {
      alert("Error: " + err.message);
    });

  }

}
