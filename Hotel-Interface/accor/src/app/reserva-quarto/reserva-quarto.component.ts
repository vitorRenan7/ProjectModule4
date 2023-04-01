import { Component, TemplateRef } from '@angular/core';

import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';


@Component({
  selector: 'app-reserva-quarto',
  templateUrl: './reserva-quarto.component.html',
  styleUrls: ['./reserva-quarto.component.scss']
})
export class ReservaQuartoComponent {

  modalRef?: BsModalRef;
  config = {
    animated: true
  };
  constructor(private modalService: BsModalService) {}
 
  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, this.config);
  }

}