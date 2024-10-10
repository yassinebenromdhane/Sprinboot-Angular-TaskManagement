import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { TasksComponent } from './tasks/tasks.component';
import { HeaderComponent } from './header/header.component';
import { TaskModalComponent } from './tasks/task-modal/task-modal.component';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [HomeComponent,TasksComponent,HeaderComponent, TaskModalComponent],
  imports: [
    CommonModule,
    HomeRoutingModule,
    FormsModule
  ]
})
export class HomeModule { }
