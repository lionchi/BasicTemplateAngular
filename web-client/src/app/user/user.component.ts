import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {UserService} from "../user.service";
import {UserModel} from "./user.model";
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  private users: UserModel[] = [];

  constructor(private router: Router, private userService: UserService, private auth: AuthService) {
  }

  ngOnInit() {
    this.userService.getUsers().subscribe(
      data => {
        this.users = data;
      }
    );
  }

  logout() {
    this.auth.logout();
  }

}
