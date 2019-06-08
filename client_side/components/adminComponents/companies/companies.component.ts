import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/services/admin.service';
import { Company } from 'src/app/Models/company';

@Component({
  selector: 'app-companies',
  templateUrl: './companies.component.html',
  styleUrls: ['./companies.component.css']
})
export class CompaniesComponent implements OnInit {

  public companies : Company[];

  constructor(private adminService : AdminService) { }

  ngOnInit() {
    this.adminService.getAllCompanies().subscribe(companies => {
      setTimeout(() => this.companies = companies, 300);
    }, err => {
      alert("Error: " + err.message);
    });

  }

}
