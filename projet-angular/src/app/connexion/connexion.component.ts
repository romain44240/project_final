import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';
import { AuthRequest } from '../auth-request';

@Component({
  selector: 'app-connexion',
  standalone: false,
  templateUrl: './connexion.component.html',
  styleUrl: './connexion.component.css'
})
export class ConnexionComponent implements OnInit {
  public authForm!: FormGroup;
  public loginCtrl!: FormControl;
  public passwordCtrl!: FormControl;
  submitted = false;

  constructor(private service: AuthService, private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.loginCtrl = this.formBuilder.control('', [Validators.required]);
    this.passwordCtrl = this.formBuilder.control('', [Validators.required, Validators.minLength(6)]);

    this.authForm = this.formBuilder.group({
      login: this.loginCtrl,
      password: this.passwordCtrl
    });
  }

  public authenticate() {
    this.submitted = true;
    if (this.authForm.invalid) {
      return;
    }

    const authRequest = new AuthRequest(
      this.authForm.value.login,
      this.authForm.value.password
    );

    this.service.authenticate(authRequest).subscribe({
      next: () => {
        this.router.navigate(['/home']);
      }
    });
  }


}



