import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../environments/environment';

@Injectable()
export class AuthService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {
  }

  attemptAuth(login: string, password: string): Observable<any> {
    const credentials = {login: login, password: password};
    console.log('attempAuth ::');
    return this.http.post(this.apiUrl + 'token/generate-token', credentials);
  }
}
