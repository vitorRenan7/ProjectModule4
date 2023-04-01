import { Component, TemplateRef } from '@angular/core';

import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';


@Component({
  selector: 'app-reserva-quarto',
  templateUrl: './reserva-quarto.component.html',
  styleUrls: ['./reserva-quarto.component.scss']
})
export class ReservaQuartoComponent {

  modalRef?: BsModalRef;
  items: any[];
  constructor(private modalService: BsModalService) {
    this.items = Array(1).fill(0);
  }
 
  openModalCadastroCliente(customModalTemplate: TemplateRef<any>) {
    this.modalRef = this.modalService.show(
      customModalTemplate,
      Object.assign({}, { class: 'gray modal-lg' })
    );
  }
}