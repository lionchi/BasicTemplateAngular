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
  passwordRecovery: FormGroup;
  display = 'none';

  constructor(private router: Router, private authService: AuthService, private token: TokenStorage, private fb: FormBuilder) {
    if (this.token.getToken() != null) {
      this.router.navigate(['user']);
    }
    /*    this.angForm = new FormGroup({
          login: new FormControl("YourLogin", Validators.required),
          password: new FormControl("YourPassword", Validators.required)
        });*/
    this.angForm = this.fb.group({
      login: ['', Validators.required], password: ['', Validators.required]
    });
    this.passwordRecovery = this.fb.group({
      email: ['', Validators.compose([Validators.required, Validators.pattern("[^ @]*@[^ @]*")])]
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

  // Model Driven Form - login
  mdfLogin(data: any) {
    let email = data.email;
  }

  openModalDialog() {
    this.display = 'block';
  }

  closeModalDialog() {
    this.display = 'none';
    this.passwordRecovery.reset();
  }

}
