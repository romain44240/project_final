import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-home',
  standalone: false,
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  role: string | null | undefined;

  constructor(public authService: AuthService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.role = this.authService.getRole();
  }

}

