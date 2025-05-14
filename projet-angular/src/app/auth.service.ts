import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthResponse } from './auth-response';
import { AuthRequest } from './auth-request';
import { environment } from './environment';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public token: string = "";

  private API_URL: string = `${environment.API_URL}/connexion`;

  constructor(private http: HttpClient) { 
    this.token = sessionStorage.getItem('token') as string;
  }

  public authenticate(authRequest: AuthRequest) {
    this.http.post<AuthResponse>(this.API_URL, {
      login: authRequest.login,
      password: authRequest.password
    }).subscribe(resp => {
      this.token = resp.token;
      sessionStorage.setItem('token',this.token);
    });
  }
  
  public isLoggedIn(): boolean {
    console.log(sessionStorage.getItem('token'));
    return sessionStorage.getItem('token') != null;
  }

}
