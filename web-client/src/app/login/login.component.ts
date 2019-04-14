import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from '../auth.service';
import {TokenStorage} from '../_common/token.storage';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {BaseComponentWithPopup} from "../base.component.with.popup";
import {ErrorMessageRu} from "../_common/error.message.ru";
import {UserService} from "../user.service";
import {SuccessMessageRu} from "../_common/success.message.ru";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent extends BaseComponentWithPopup implements OnInit {

  login: string;
  password: string;
  authorizationForm: FormGroup;
  passwordRecoveryForm: FormGroup;
  displayModalPasswordRecovery = 'none';
  isSubmittedAuthorizationForm: boolean = false;
  isSubmittedPasswordRecoveryForm: boolean = false;
  isLoading: boolean = false;

  constructor(private router: Router, private authService: AuthService, private userService: UserService,
              private token: TokenStorage, private fb: FormBuilder) {
    super();
    if (this.authService.isAuth()) {
      this.router.navigate(['user']);
    } else {
      this.authorizationForm = this.fb.group({
        login: ['', Validators.required],
        password: ['', Validators.compose([Validators.required, Validators.minLength(7)])]
      });
      this.passwordRecoveryForm = this.fb.group({
        email: ['', Validators.compose([Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")])]
      });
    }
  }

  ngOnInit() {
  }

  authorization(): void {
    this.isLoading = true;
    if (this.authorizationForm.invalid) {
      this.isSubmittedAuthorizationForm = true;
      this.isLoading = false
    } else {
      this.isSubmittedAuthorizationForm = false;
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
          this.isLoading = false;
        }, () => {
          this.isLoading = false
        }
      )
      ;
    }
  }

  recoverPass(data: any) {
    this.isLoading = true;
    if (this.passwordRecoveryForm.invalid) {
      this.isSubmittedPasswordRecoveryForm = true;
      this.isLoading = false
    } else {
      let email = data.email;
      this.isSubmittedPasswordRecoveryForm = false;
      this.userService.validationEmail(email).subscribe(value => {
        this.message = SuccessMessageRu.successSendNewEmail;
        this.initPopup(true, 'success', 'block');
        setTimeout(() => {
          this.closePopup();
          window.close();
        }, 2500);
      }, error => {
        if (error.error === 'error.user.validation.email') {
          this.message = ErrorMessageRu.errorValidationEmail;
          this.initPopup(true, 'error', 'block');
          setTimeout(() => this.closePopup(), 2500);
        }
        this.isLoading = false;
      }, () => {
        this.isLoading = false;
      });
      this.closeModalDialog();
    }
  }

  openModalDialog() {
    this.displayModalPasswordRecovery = 'block';
  }

  closeModalDialog() {
    this.displayModalPasswordRecovery = 'none';
    this.passwordRecoveryForm.reset();
  }

}
