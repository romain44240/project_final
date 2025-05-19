import { TestBed } from '@angular/core/testing';

import { AdminService } from './admin.service';
import { provideHttpClientTesting } from '@angular/common/http/testing';

describe('AdminService', () => {
  let service: AdminService;

  beforeEach(() => {
    TestBed.configureTestingModule({
        providers: [
          AdminService,
          provideHttpClientTesting() 
        ]
    });
    service = TestBed.inject(AdminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
