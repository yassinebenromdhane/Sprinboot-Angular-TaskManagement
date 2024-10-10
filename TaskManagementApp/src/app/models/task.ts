export interface Task {
    id: number;
    taskName: string;
    taskDescription: string;
    taskStatus: 'PENDING' | 'IN_PROGRESS' | 'Completed' | 'NEW';
    dueDate: Date;
  }