import {Component, OnInit} from '@angular/core';
import {UserModel} from "../user/user.model";
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BaseComponentWithPopup} from "../base.component.with.popup";
import {ErrorMessageRu} from "../_common/error.message.ru";
import {SuccessMessageRu} from "../_common/success.message.ru";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent extends BaseComponentWithPopup implements OnInit {

  registrationForm: FormGroup;
  isSubmittedRegistrationForm: boolean = false;
  isLoading: boolean = false;

  constructor(private auth: AuthService, private router: Router, private fb: FormBuilder) {
    super();
    this.registrationForm = this.fb.group({
      login: ['', Validators.required],
      password: ['', Validators.compose([Validators.required, Validators.minLength(7)])],
      fio: ['', Validators.required],
      email: ['', Validators.compose([Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")])]
    });
  }

  ngOnInit() {
  }

  registration(registrationUser: UserModel): void {
    this.isLoading = true;
    if (this.registrationForm.invalid) {
      this.isSubmittedRegistrationForm = true;
      this.isLoading = false;
    } else {
      this.isSubmittedRegistrationForm = false;
      this.auth.registration(registrationUser).subscribe(value => {
        this.registrationForm.reset();
        this.message = SuccessMessageRu.successCreateNewUser;
        this.initPopup(true, 'success', 'block');
        setTimeout(() => {
          this.closePopup();
          window.close();
        }, 2500);
      }, error => {
        this.registrationForm.reset();
        if (error.error === 'error.user.validation') {
          this.message = ErrorMessageRu.errorCreateValidationUser;
          this.initPopup(true, 'error', 'block');
          setTimeout(() => this.closePopup(), 2500);
        }
        this.isLoading = false;
      }, () => {
        this.isLoading = false;
      });
    }
  }
}
