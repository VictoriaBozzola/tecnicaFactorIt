import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './Components/login/login.component';
import { PortalComponent } from './Components/portal/portal.component';
import { CardsComponent } from './Components/cards/cards.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PortalComponent,
    CardsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
