import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservaQuartoComponent } from './reserva-quarto.component';

describe('ReservaQuartoComponent', () => {
  let component: ReservaQuartoComponent;
  let fixture: ComponentFixture<ReservaQuartoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReservaQuartoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReservaQuartoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
