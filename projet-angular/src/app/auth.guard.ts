import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const authService: AuthService = inject(AuthService);
  const router: Router = inject(Router);

  const token = authService.token;
  const role = authService.getRole(); 

  if (!token) {
    router.navigate(['/connexion']);
    return false;
  }

  // employe = admin ici
  const allowedRoles = route.data['roles'] as string[];

  if(role){
    if (allowedRoles && !allowedRoles.includes(role)) {
        router.navigate(['/unauthorized']); // créer une route pour quand on n'est pas autorisé
        return false;
      }
  }
  
  return true;
};