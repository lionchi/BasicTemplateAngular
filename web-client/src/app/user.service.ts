import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/internal/Observable";
import {environment} from "../environments/environment";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class UserService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {
  }

  public getUsers(): Observable<any> {
    return this.http.get(this.apiUrl+'user/list')
  }
}
