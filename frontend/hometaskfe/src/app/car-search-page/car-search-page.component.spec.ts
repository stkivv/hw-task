import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarSearchPageComponent } from './car-search-page.component';

describe('CarSearchPageComponent', () => {
  let component: CarSearchPageComponent;
  let fixture: ComponentFixture<CarSearchPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CarSearchPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CarSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
