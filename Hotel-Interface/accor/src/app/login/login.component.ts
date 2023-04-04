import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  showSpinner = false;

  constructor(private router: Router) {}

  login(){
    this.showSpinner = true;

  // Chamar a API ou executar tarefa assíncrona 

  setTimeout(() => {
    this.showSpinner = false;
    this.router.navigate(['reserva-quarto']);
  }, 2000);
}
}
