import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Trabajadores } from './trabajadores';

describe('Trabajadores', () => {
  let component: Trabajadores;
  let fixture: ComponentFixture<Trabajadores>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Trabajadores]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Trabajadores);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
