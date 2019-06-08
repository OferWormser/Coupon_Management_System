import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/Models/company';
import { AdminService } from 'src/app/services/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.css']
})
export class CreateCompanyComponent implements OnInit {

  public company = new Company();

  constructor(private router: Router, private adminService : AdminService) { }

  public createCompany() : void {
    alert(`
    CompanyName: ${this.company.compName}
    CompanyPassword: ${this.company.password}
    CompanyEmail: ${this.company.email}
    `);

    this.adminService.createCompany(this.company).subscribe( c => {
       alert(c.compName + " has been succesfully added!");
       this.router.navigate(["rest/admin/companies"]);
     }, err => {
       alert("Error: " + err.message);
     });
  }


  ngOnInit() {
  }

}
