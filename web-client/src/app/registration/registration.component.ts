import {Component, OnInit} from '@angular/core';
import {UserModel} from "../user/user.model";
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registrationForm: FormGroup;

  constructor(private auth: AuthService, private router: Router, private fb: FormBuilder) {
    this.registrationForm = this.fb.group({
      login: ['', Validators.required],
      password: ['', Validators.compose([Validators.required, Validators.minLength(7)])],
      fio: ['', Validators.required],
      email: ['', Validators.compose([Validators.required, Validators.pattern("[^ @]*@[^ @]*")])]
    });
  }

  ngOnInit() {
  }

  registration(registrationUser: UserModel): void {
    registrationUser.enabled = 1;
    this.auth.registration(registrationUser).subscribe(value => {
      window.close();
    })
  }

}
