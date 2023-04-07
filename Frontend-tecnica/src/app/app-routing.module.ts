import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './Components/login/login.component';
import { PortalComponent } from './Components/portal/portal.component';


const routes: Routes = [{
  path: "**", component: PortalComponent
}, {
  path:"portal", component: PortalComponent
}, {
  path:"login", component: LoginComponent 
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
