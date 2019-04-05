import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {TokenStorage} from './_common/token.storage';

@Injectable()
export class Interceptor implements HttpInterceptor {

  constructor(private token: TokenStorage, private router: Router) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.token.getToken()) {
      req = req.clone({
        setHeaders: {Authorization: `Bearer ${this.token.getToken()}`,}
      });
    }

    return next.handle(req);
  }
}
