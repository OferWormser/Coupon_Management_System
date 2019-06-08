import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Company } from '../Models/company';
import { Observable } from 'rxjs';
import { Customer } from '../Models/customer';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private httpClient: HttpClient) { }

  public createCompany(company: Company) : Observable<Company> {
    return this.httpClient.post<Company>("http://localhost:8080/rest/admin/companies", company, { withCredentials: true});
  }

  public removeCompany(companyId: number) : Observable<Company> {
    return this.httpClient.delete<Company>("http://localhost:8080/rest/admin/companies/" + companyId, { withCredentials: true});
  }

  public updateCompany(company: Company) : Observable<Company> {
    return this.httpClient.put<Company>("http://localhost:8080/rest/admin/companies", company, { withCredentials: true});
  }

  public getCompany(companyId: number) : Observable<Company> {
    return this.httpClient.get<Company>("http://localhost:8080/rest/admin/companies/" + companyId, { withCredentials: true});
  }

  public getAllCompanies() : Observable<Company[]> {
    return this.httpClient.get<Company[]>("http://localhost:8080/rest/admin/companies", { withCredentials: true});
  }

  public createCustomer(customer: Customer) : Observable<Customer> {
    return this.httpClient.post<Customer>("http://localhost:8080/rest/admin/customers", customer, { withCredentials: true});
  }

  public removeCustomer(customerId: number) : Observable<Customer> {
    return this.httpClient.delete<Customer>("http://localhost:8080/rest/admin/customers/" + customerId, { withCredentials: true});
  }

  public updateCustomer(customer: Customer) : Observable<Customer> {
    return this.httpClient.put<Customer>("http://localhost:8080/rest/admin/customers", customer, { withCredentials: true});
  }

  public getCustomer(customerId: number) : Observable<Customer> {
    return this.httpClient.get<Customer>("http://localhost:8080/rest/admin/customers/" + customerId, { withCredentials: true});
  }

  public getAllCustomers() : Observable<Customer[]> {
    return this.httpClient.get<Customer[]>("http://localhost:8080/rest/admin/customers", { withCredentials: true});
  }


}
