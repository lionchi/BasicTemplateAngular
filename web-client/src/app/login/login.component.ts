import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from '../auth.service';
import {TokenStorage} from '../_common/token.storage';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {BaseComponentWithPopup} from "../base.component.with.popup";
import {ErrorMessageRu} from "../_common/error.message.ru";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent extends BaseComponentWithPopup implements OnInit {

  login: string;
  password: string;
  angForm: FormGroup;
  passwordRecovery: FormGroup;
  displayModalPasswordRecovery = 'none';

  constructor(private router: Router, private authService: AuthService, private token: TokenStorage, private fb: FormBuilder) {
    super();
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
      }, error => {
        if (error.error === 'error.authentication.user') {
          this.message = ErrorMessageRu.errorAuthenticationUser;
          this.initPopup(true, 'error', 'block');
          setTimeout(() => this.closePopup(), 2500);
        }
      }
    );
  }

  // Model Driven Form - login
  mdfLogin(data: any) {
    let email = data.email;
  }

  openModalDialog() {
    this.displayModalPasswordRecovery = 'block';
  }

  closeModalDialog() {
    this.displayModalPasswordRecovery = 'none';
    this.passwordRecovery.reset();
  }

}
