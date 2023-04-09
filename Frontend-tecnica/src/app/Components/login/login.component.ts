import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { GeneralService } from 'src/app/config/general-service.service';
import { Usuario } from 'src/app/interfaces/usuario';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  usuario: string;

  constructor(private router: Router,
    private gralService: GeneralService) { }

  ngOnInit(): void {
    this.usuario = "";
    sessionStorage.removeItem('usuario');
  }

  submit(){
    if(this.usuario != ''){
      this.gralService.login(this.usuario).subscribe((data) => {
        sessionStorage.setItem('usuario', JSON.stringify(data));
        this.router.navigateByUrl('/portal');
      }, err => {
        console.log("Error: " + err);
      })
    }
  }

}
