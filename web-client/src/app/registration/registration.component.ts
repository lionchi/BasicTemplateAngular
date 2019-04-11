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

  constructor(private auth: AuthService, private router: Router, private fb: FormBuilder) {
    super();
    this.registrationForm = this.fb.group({
      login: ['', Validators.required],
      password: ['', Validators.compose([Validators.required])],
      fio: ['', Validators.required],
      email: ['', Validators.compose([Validators.required, Validators.pattern("[^ @]*@[^ @]*")])]
    });
  }

  ngOnInit() {
  }

  registration(registrationUser: UserModel): void {
    this.auth.registration(registrationUser).subscribe(value => {
      this.registrationForm.reset();
      this.message = SuccessMessageRu.successCreateNewUser;
      this.initPopup(true, 'success', 'block');
      setTimeout(() => {
        this.closePopup();
        window.close();
      }, 2500);
    }, error => {
      if (error.error === 'error.user.validation') {
        this.message = ErrorMessageRu.errorCreateValidationUser;
        this.initPopup(true, 'error', 'block');
        setTimeout(() => this.closePopup(), 2500);
      }
    });
  }
}
