import { Component } from '@angular/core';
import { Task } from '../../models/task';
import { TasksService } from '../../services/tasks.service';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrl: './tasks.component.css'
})
export class TasksComponent {
  selectedTask!: Task;
  tasks: Task[] = [];
  isLoading : boolean = false;

  constructor(private taskService: TasksService) {}

  ngOnInit(): void {
    this.fetchTasks();
  }

  openPopUp(task: Task){
    this.selectedTask = task
  }

  fetchTasks(): void {
    this.isLoading = true
    this.taskService.getTasks().subscribe({
      next: (data: Task[]) => {
        this.tasks = data;
        console.log(this.tasks)
        this.isLoading = false
      },
      error: (err) => {
        console.error('Error fetching tasks', err);  // Handle any errors
      }
    });
  }

}
