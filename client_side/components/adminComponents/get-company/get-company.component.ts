import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/Models/company';
import { AdminService } from 'src/app/services/admin.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-get-company',
  templateUrl: './get-company.component.html',
  styleUrls: ['./get-company.component.css']
})
export class GetCompanyComponent implements OnInit {

  public company = new Company();
  
  
  constructor(private router: Router, private activatedRoute: ActivatedRoute, private adminService : AdminService) { }
  
  ngOnInit() {
    const id = +this.activatedRoute.snapshot.params.id;
    this.adminService.getCompany(id).subscribe( company => {
      setTimeout(() => this.company = company, 300);
    }, err => {
      alert("Error: " + err.message);
    });
  }

  public removeCompany() {
    this.adminService.removeCompany(this.company.id).subscribe( company => {
      alert("Company has been removed!");
      this.router.navigate(["rest/admin/companies"]);
    }, err => {
      alert("Error: " + err.message);
    });
  }

  public updateCompany(newPassword : string, newEmail : string) {

    alert(`
    CompanyPassword: ${newPassword}
    CompanyEmail: ${newEmail}
    `);
    this.company.password = newPassword;
    this.company.email = newEmail;
    this.adminService.updateCompany(this.company).subscribe( company => {
    alert("Company has been succesfully updated!");
     }, err => {
       alert("Error: " + err.message);
     });
  }



}
