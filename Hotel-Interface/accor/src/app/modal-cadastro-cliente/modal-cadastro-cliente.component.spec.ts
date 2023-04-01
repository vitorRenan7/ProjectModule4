import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalCadastroClienteComponent } from './modal-cadastro-cliente.component';

describe('ModalCadastroClienteComponent', () => {
  let component: ModalCadastroClienteComponent;
  let fixture: ComponentFixture<ModalCadastroClienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalCadastroClienteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalCadastroClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
