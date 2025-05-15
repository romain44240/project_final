import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { AuthRequest } from '../auth-request';

@Component({
  selector: 'app-inscription',
  standalone: false,
  templateUrl: './inscription.component.html',
  styleUrl: './inscription.component.css'
})
export class InscriptionComponent implements OnInit{
  public authForm!: FormGroup;

  public loginCtrl!: FormControl;
  public passwordCtrl!: FormControl;
  public emailCtrl!: FormControl;
  public nomCtrl!: FormControl;
  public prenomCtrl!: FormControl;
  public telephoneCtrl!: FormControl

  submitted = false;

  constructor(private service: AuthService, private router: Router, private formBuilder: FormBuilder) { 
  }

  ngOnInit(): void {
    this.emailCtrl = this.formBuilder.control('', [Validators.required, Validators.email]);
    this.loginCtrl = this.formBuilder.control('', Validators.required);
    this.passwordCtrl = this.formBuilder.control('', [ Validators.required, Validators.minLength(6) ]);
    this.nomCtrl = this.formBuilder.control('', Validators.required);
    this.prenomCtrl = this.formBuilder.control('', Validators.required);
    this.telephoneCtrl = this.formBuilder.control('', [Validators.required, Validators.pattern('^(\\+?\\d{1,3})?[\\s.-]?(\\d{1,4}[\\s.-]?){1,4}\\d{1,4}$')]);

    this.authForm = this.formBuilder.group({
      login: this.loginCtrl,
      password: this.passwordCtrl,
      nom: this.nomCtrl,
      prenom: this.prenomCtrl,
      telephone: this.telephoneCtrl,
      email: this.emailCtrl
    });
  }

  public authenticate() {
    this.submitted = true;
    if(this.authForm.invalid){
      return;
    }
    this.service.subscribe(new AuthRequest(this.authForm.value.login, this.authForm.value.password));

    // FIXME : Si l'auth échoue, on est quand même redirigé
    this.router.navigate([ '/home' ]);
  }
}
