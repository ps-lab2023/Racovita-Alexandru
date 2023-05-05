import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobsadminComponent } from './jobsadmin.component';

describe('JobsadminComponent', () => {
  let component: JobsadminComponent;
  let fixture: ComponentFixture<JobsadminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JobsadminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(JobsadminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
