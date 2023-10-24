import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SimcardComponent } from './simcard.component';

describe('SimcardComponent', () => {
  let component: SimcardComponent;
  let fixture: ComponentFixture<SimcardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SimcardComponent]
    });
    fixture = TestBed.createComponent(SimcardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
