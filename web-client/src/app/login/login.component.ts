import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from '../auth.service';
import {TokenStorage} from '../_common/token.storage';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: string;
  password: string;

  constructor(private router: Router, private authService: AuthService, private token: TokenStorage) {
    if (this.token.getToken() != null) {
      this.router.navigate(['user']);
    }
  }

  ngOnInit() {
  }

  invokeLogin(): void {
    this.authService.attemptAuth(this.login, this.password).subscribe(
      data => {
        this.token.saveToken(data.token);
        this.router.navigate(['user']);
      }
    );
  }

}
