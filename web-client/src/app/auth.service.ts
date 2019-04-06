import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../environments/environment';
import {TokenStorage} from "./_common/token.storage";
import {Router} from "@angular/router";

@Injectable()
export class AuthService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private router: Router, private tokenStorage: TokenStorage) {
  }

  attemptAuth(login: string, password: string): Observable<any> {
    const credentials = {login: login, password: password};
    return this.http.post(this.apiUrl + 'token/generate-token', credentials);
  }

  logout() {
    this.tokenStorage.remove();
    this.router.navigate(['login']);
  }
}
