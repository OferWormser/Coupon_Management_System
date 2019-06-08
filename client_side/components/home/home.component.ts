import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/Models/client';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public client = new Client();

  public constructor(private loginService : LoginService, private router : Router) { }

  public login() : void {
    alert(`
    Name: ${this.client.name}
    Password: ${this.client.password}
    clientType: ${this.client.clientType}
    `);
    
    this.loginService.login(this.client).subscribe( c => {
      alert( c.clientType + " has been succesfully logged in!");

      switch (c.clientType) {
        case "ADMIN":
          this.router.navigate(["rest/admin"]);
          break;
        case "COMPANY":
          this.router.navigate(["rest/company"]);          
          break;
        case "CUSTOMER":
          this.router.navigate(["rest/customer"]);
          break;
            
        default:
          break;
      }

   }, err => {
      alert("Error: " + err.message);
      console.log(err);
   });
  
  }

  


  ngOnInit() {
  }
}
