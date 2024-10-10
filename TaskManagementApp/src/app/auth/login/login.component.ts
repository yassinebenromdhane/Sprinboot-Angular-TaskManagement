import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  property: string = 'password';
  isPasswordVisible: boolean = false
  email : string = '';
  password : string = '';
  isLoading : boolean = false;

  constructor(private authService: AuthService, private router: Router) {}

  togglePasswordVisibility() {
    this.isPasswordVisible = !this.isPasswordVisible;
    this.isPasswordVisible ? this.property = 'text' : this.property = 'password'
  }

  onSubmit() {
    this.isLoading = true
    this.authService.login(this.email, this.password).subscribe(
      (response) => {
        this.authService.storeToken(response.token);
        this.isLoading = false
        this.router.navigate(['/home']);
      },
      (error) => {
        console.log(error)
        alert('Login failed!!!!')
      }
    );
  }
}
