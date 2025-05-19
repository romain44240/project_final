import { TestBed } from '@angular/core/testing';

import { ReservationService } from './reservation.service';
import { provideHttpClientTesting } from '@angular/common/http/testing';

describe('ReservationService', () => {
  let service: ReservationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
        providers: [
          ReservationService,
          provideHttpClientTesting() 
        ]
    });
    service = TestBed.inject(ReservationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
