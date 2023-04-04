import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Recepcionista } from '../model/recepcionista.model';
import { Quarto } from '../model/quarto.model';
import { Cliente } from '../model/cliente.model';

@Component({
  selector: 'app-administrador',
  templateUrl: './administrador.component.html',
  styleUrls: ['./administrador.component.scss']
})

export class AdministradorComponent {
  public listaDeObjetos: any[] = [];

  constructor(private http: HttpClient) {}

  public enviarRequisicaoRecepcionistas(): void {
    this.listaDeObjetos = [];
    this.http.get<Recepcionista[]>('http://localhost:8080/recepcionistas').subscribe((recepcionistas) => {
      const listaDeRecepcionistas: Recepcionista[] = recepcionistas.map((recepcionista) => {
        const x: Recepcionista = new Recepcionista(recepcionista.id, recepcionista.nome, recepcionista.sobrenome, recepcionista.rg, recepcionista.cpf, recepcionista.dataNascimento, recepcionista.endereco, recepcionista.usuario);
        return x;
      });
      this.listaDeObjetos = listaDeRecepcionistas
    });
  }

  public enviarRequisicaoQuartos(): void {
    this.listaDeObjetos = [];
    this.http.get<Quarto[]>('http://localhost:8080/quartos').subscribe((quartos) => {
      const listaDeQuartos: Quarto[] = quartos.map((quarto) => {
        const x: Quarto = new Quarto(quarto.id, quarto.numero, quarto.descricao, quarto.categoria, quarto.situacao, quarto.precoHora, quarto.imagens);
        return x;
      });
      this.listaDeObjetos = listaDeQuartos;
    });
  }

  public enviarRequisicaoClientes(): void {
    this.listaDeObjetos = [];
    this.http.get<Cliente[]>('http://localhost:8080/clientes').subscribe((clientes) => {
      const listaDeClientes: Cliente[] = clientes.map((cliente) => {
        const x: Cliente = new Cliente(cliente.id, cliente.nome, cliente.sobrenome, cliente.rg, cliente.cpf, cliente.dataNascimento, cliente.endereco, cliente.email, cliente.celular);
        return x;
      });
      this.listaDeObjetos = listaDeClientes;
    });
  }

}
