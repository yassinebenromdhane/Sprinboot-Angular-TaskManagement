import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class TasksService {

  private apiUrl = environment.apiURL; 

  constructor(private http: HttpClient, private authService: AuthService) {}

  getTasks(): Observable<any> {
    const token = this.authService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json' 
    });
    console.log(headers)
    return this.http.get(`${this.apiUrl}/tasks/`, { headers} );
  }


}
