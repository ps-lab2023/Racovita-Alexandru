import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobnoapplyComponent } from './jobnoapply.component';

describe('JobnoapplyComponent', () => {
  let component: JobnoapplyComponent;
  let fixture: ComponentFixture<JobnoapplyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JobnoapplyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(JobnoapplyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
