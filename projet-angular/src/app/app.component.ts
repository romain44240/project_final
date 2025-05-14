import { Component, HostListener, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-root',
  standalone: false,
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'projet-angular';

  constructor(public authService: AuthService, private router: Router) { }

  logout() {
    this.authService.logout();
    this.router.navigate(['/connexion']);
  }


  ngOnInit(): void {
    console.log("Initialisation !");
  }
  sidebarOpen = true;
  isMobile = false;

  toggleSidebar() {
    this.sidebarOpen = !this.sidebarOpen;
  }

  @HostListener('window:resize', []) onResize() {
    this.isMobile = window.innerWidth < 768;
    if (this.isMobile) this.sidebarOpen = false;
    else this.sidebarOpen = true;
  }

}


