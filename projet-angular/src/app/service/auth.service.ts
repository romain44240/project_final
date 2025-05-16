import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { environment } from '../environment';
import { AuthRequest } from '../auth-request';
import { AuthResponse } from '../auth-response';
import { Client } from '../models';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public token: string | null = null;
  public id: number | null = null;
  public role$: BehaviorSubject<string | null> = new BehaviorSubject<string | null>(null);

  private API_URL: string = `${environment.API_URL}`;

  constructor(private http: HttpClient) {
    this.token = sessionStorage.getItem('token');
    if (this.token) {
      const decoded = this.decodeToken(this.token);
      this.role$.next(decoded?.roles?.[0] ?? null);
    }
  }

  public authenticate(authRequest: AuthRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(this.API_URL + "/connexion", {
      login: authRequest.login,
      password: authRequest.password
    }).pipe(
      tap(resp => {
        this.token = resp.token;
        this.id = resp.id;
        sessionStorage.setItem('token', this.token);
        sessionStorage.setItem('id',this.id.toString());

        const decoded = this.decodeToken(resp.token);
        const role = decoded?.roles?.[0] ?? null;
        this.role$.next(role);

      })
    );
  }

  public subscribe(authRequest: AuthRequest): void {
    this.http.post<AuthResponse>(this.API_URL + "/inscription", {
      login: authRequest.login,
      password: authRequest.password
    }).subscribe(() => {
      this.authenticate(authRequest).subscribe(); 
    });
  }

  public isLoggedIn(): boolean {
    return sessionStorage.getItem('token') != null;
  }

  public logout(): void {
    this.token = null;
    sessionStorage.removeItem('token');
    this.role$.next(null);
  }

  public getRole(): string | null {
    return this.role$.value;
  }

  public getUserId(): number | null {
    const id = sessionStorage.getItem('id');
    return id ? +id : null;
  }

  private decodeToken(token: string): JwtPayload | null {
    try {
      return jwtDecode<JwtPayload>(token);
    } catch (error) {
      console.error('Erreur lors du décodage du token :', error);
      return null;
    }
  }

  public getNomUtilisateur(): string | null {
    if (this.token) {
      const decoded = this.decodeToken(this.token);
      return decoded?.sub || null; // Supposons que le nom de l'utilisateur est dans la "sub" du token
    }
    return null;
  }
}

export interface JwtPayload {
  sub: string;
  email: string;
  roles: string[];
  exp?: number;
}
