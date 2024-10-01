import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarSearchPageComponent } from './car-search-page.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('CarSearchPageComponent', () => {
  let component: CarSearchPageComponent;
  let fixture: ComponentFixture<CarSearchPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CarSearchPageComponent, HttpClientTestingModule]
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
