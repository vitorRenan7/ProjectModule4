import { LoginComponent } from './login/login.component';
import { ReservaQuartoComponent } from './reserva-quarto/reserva-quarto.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'reserva-quarto', component: ReservaQuartoComponent },
  { path: '', component: LoginComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
