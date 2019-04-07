import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from '../auth.service';
import {TokenStorage} from '../_common/token.storage';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: string;
  password: string;
  angForm: FormGroup;

  constructor(private router: Router, private authService: AuthService, private token: TokenStorage, private fb: FormBuilder) {
    if (this.token.getToken() != null) {
      this.router.navigate(['user']);
    }
    this.angForm = this.fb.group({
      login: ['', Validators.required ], password: ['', Validators.required]
    });
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
