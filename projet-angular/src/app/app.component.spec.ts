import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { AuthService } from './service/auth.service';
import { Router } from '@angular/router';
import { of } from 'rxjs';

describe('AppComponent', () => {
  let component: AppComponent;
  let fixture: ComponentFixture<AppComponent>;
  let mockAuthService: jasmine.SpyObj<AuthService>;
  let mockRouter: jasmine.SpyObj<Router>;

  beforeEach(async () => {
    mockAuthService = jasmine.createSpyObj('AuthService', ['logout'], { role$: of('admin') });
    mockRouter = jasmine.createSpyObj('Router', ['navigate']);

    await TestBed.configureTestingModule({
      declarations: [AppComponent],
      providers: [
        { provide: AuthService, useValue: mockAuthService },
        { provide: Router, useValue: mockRouter }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the app', () => {
    expect(component).toBeTruthy();
  });

  it('should subscribe to role$ on init and set role', () => {
    component.ngOnInit();
    expect(component.role).toBe('admin');
  });

  it('should call logout and navigate to /connexion', () => {
    component.logout();
    expect(mockAuthService.logout).toHaveBeenCalled();
    expect(mockRouter.navigate).toHaveBeenCalledWith(['/connexion']);
  });

  it('should toggle sidebar', () => {
    const initial = component.sidebarOpen;
    component.toggleSidebar();
    expect(component.sidebarOpen).toBe(!initial);
  });

  it('should respond to window resize (mobile)', () => {
    spyOnProperty(window, 'innerWidth').and.returnValue(500); // Mobile size
    component.onResize();
    expect(component.isMobile).toBeTrue();
    expect(component.sidebarOpen).toBeFalse();
  });

  it('should respond to window resize (desktop)', () => {
    spyOnProperty(window, 'innerWidth').and.returnValue(1024); // Desktop size
    component.onResize();
    expect(component.isMobile).toBeFalse();
    expect(component.sidebarOpen).toBeTrue();
  });
});
