import { Component, TemplateRef } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { ReservaQuartoService } from './ReservaQuarto.service';

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

  cadastro: FormGroup;


  constructor(private modalService: BsModalService, private reservaQuartoService: ReservaQuartoService) {
    this.cadastro = new FormGroup({
      nome: new FormControl(''),
      sobrenome: new FormControl(''),
      rg: new FormControl(''),
      cpf: new FormControl(''),
      dataNascimento: new FormControl(''),
      email: new FormControl(''),
      celular: new FormControl('')      
    });

    this.searchText = '';
    this.items1 = Array(1).fill(0);
    this.items2 = Array(1).fill(0);
  }

  dadosCadastro(cadastro: FormGroup){
    this.reservaQuartoService.cadastrar(this.cadastro.value).subscribe(res => {
      console.log(res);
    }
    );
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

  searchText: string;

  performSearch() {
    console.log('Buscando por', this.searchText);
  }

}