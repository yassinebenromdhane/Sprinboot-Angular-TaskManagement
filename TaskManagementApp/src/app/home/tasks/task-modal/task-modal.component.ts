import { Component, Input, ViewChild } from '@angular/core';
import { Task } from '../../../models/task';
import { formatDate } from '@angular/common';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-task-modal',
  templateUrl: './task-modal.component.html',
  styleUrl: './task-modal.component.css'
})
export class TaskModalComponent {
  @Input() task! : Task;
  @Input() tasks! : Task[];
  taskId! : number;
  @ViewChild('taskForm') ngForm: NgForm | null;

  constructor() {
    this.ngForm = null; 
  }


  ngOnInit(){
    this.taskId = this.task.id;
  }

  updateTask = () => {
    this.tasks.map(task =>
      task.id === this.taskId ? task === this.task : task
    )
    console.log('updated')
  }

  onSubmit(){
    this.updateTask()
    console.warn(this.task)
  }


}
