import { TestBed } from '@angular/core/testing';

import { OrderdetatilsService } from './orderdetatils.service';

describe('OrderdetatilsService', () => {
  let service: OrderdetatilsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrderdetatilsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
