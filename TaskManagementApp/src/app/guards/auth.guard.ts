import { inject } from '@angular/core';
import { CanActivateFn, Route, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService)
  const router = inject(Router)
  if (authService.isAuthenticated()) {
    if (state.url === '/login') {
      router.navigate(['/home']);
      return false;
    }
    return true
  }
  router.navigate(['/login'])
  return false;
};
