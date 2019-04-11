import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from 'rxjs';
import {AuthService} from "../auth.service";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs/internal/observable/throwError";
import {Router} from "@angular/router";

@Injectable()
export class RefreshTokenInterceptor implements HttpInterceptor {

  constructor(private auth: AuthService, private router: Router) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(catchError(err => {
      if (err.status === 401) {
        this.auth.logout();
        this.router.navigate(['login']);
        const error = err.error.message || err.statusText;
        return throwError(error);
      } else {
        return throwError(err.error);
      }
    }))
  }
}
