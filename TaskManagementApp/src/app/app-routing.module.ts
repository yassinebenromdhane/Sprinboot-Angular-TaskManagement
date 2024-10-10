import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { HomeComponent } from './home/home.component';
import { TasksComponent } from './home/tasks/tasks.component';

const routes: Routes = [
  {path:'', loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)},
  {path:'home', loadChildren: () => import('./home/home.module').then(m => m.HomeModule)},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
