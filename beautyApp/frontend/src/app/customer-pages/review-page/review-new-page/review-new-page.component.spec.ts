import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewReviewPageComponent } from './review-new-page.component';

describe('NewReviewPageComponent', () => {
  let component: NewReviewPageComponent;
  let fixture: ComponentFixture<NewReviewPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NewReviewPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NewReviewPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
