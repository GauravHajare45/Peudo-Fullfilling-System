import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MobilePlansComponent } from './mobile-plans.component';

describe('MobilePlansComponent', () => {
  let component: MobilePlansComponent;
  let fixture: ComponentFixture<MobilePlansComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MobilePlansComponent]
    });
    fixture = TestBed.createComponent(MobilePlansComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
