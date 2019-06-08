import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {

  constructor(private loginService : LoginService, private router : Router)  { }

  public logout() : void {
    this.loginService.logout().subscribe( () => {
      alert( "Logged out!");
      this.router.navigate(["home"]);
    }, err => {
      alert("Error: " + err.message);
      console.log(err);
   });
  
  }

  ngOnInit() {
  }

}
