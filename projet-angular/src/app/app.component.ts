import { Component, HostListener, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: false,
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'projet-angular';
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


