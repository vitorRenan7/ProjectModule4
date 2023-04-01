import { Component, TemplateRef } from '@angular/core';

import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';


@Component({
  selector: 'app-reserva-quarto',
  templateUrl: './reserva-quarto.component.html',
  styleUrls: ['./reserva-quarto.component.scss']
})
export class ReservaQuartoComponent {

  modalRef1?: BsModalRef;
  modalRef2?: BsModalRef;

  items1: any[];
  items2: any[];

  constructor(private modalService: BsModalService) {
    this.items1 = Array(1).fill(0);
    this.items2 = Array(1).fill(0);
  }
 
  openModalCadastroCliente(customModalTemplate: TemplateRef<any>) {
    this.modalRef1 = this.modalService.show(
      customModalTemplate,
      Object.assign({}, { class: 'gray modal-lg' })
    );
  }

  openBuscarCliente(customModalTemplate: TemplateRef<any>) {
    this.modalRef2 = this.modalService.show(
      customModalTemplate, { class: 'my-custom-modal' });
  }
}