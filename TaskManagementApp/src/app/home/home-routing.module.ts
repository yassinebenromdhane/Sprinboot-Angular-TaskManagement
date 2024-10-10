import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TasksComponent } from './tasks/tasks.component';
import { HomeComponent } from './home.component';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { UsersComponent } from '../users/users.component';
import { authGuard } from '../guards/auth.guard';

const routes: Routes = [
  {path:'', component: HomeComponent,
    children:[
        {path:'tasks', component:TasksComponent },
        {path:'dashboard', component:DashboardComponent , canActivate : [authGuard]},
        {path: 'users', component:UsersComponent , canActivate : [authGuard] }
    ]
  },
 
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
