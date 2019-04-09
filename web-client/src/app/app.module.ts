import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {UserComponent} from './user/user.component';
import {UserService} from './user.service';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthService} from './auth.service';
import {Interceptor} from './_common/app.interceptor';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TokenStorage} from './_common/token.storage';
import {RefreshTokenInterceptor} from "./_common/refresh.token.inspector";
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { RegistrationComponent } from './registration/registration.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserComponent,
    HeaderComponent,
    FooterComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [UserService, AuthService, TokenStorage,
    {provide: HTTP_INTERCEPTORS, useClass: Interceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: RefreshTokenInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
