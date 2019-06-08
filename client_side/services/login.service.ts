import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Client } from '../Models/client';
import { LoggedClient } from '../Models/loggedClient';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient) { }

  public login(client : Client): Observable<LoggedClient> {
    return this.httpClient.post<LoggedClient>("http://localhost:8080/login", client, { withCredentials: true});
  }

  public logout() : Observable<string> {
    return this.httpClient.get<string>("http://localhost:8080/login", {withCredentials: true});
  }

}
