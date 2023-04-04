import { Endereco } from "./endereco.model";

export class Recepcionista {
    id: string;
    nome: string;
    sobrenome: string;
    rg: string;
    cpf: string;
    dataNascimento: string;
    endereco: Endereco;
    usuario: string;

    constructor(id: string, nome: string, sobrenome: string, rg: string, cpf: string, dataNascimento: string, endereco: Endereco, usuario: string) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.usuario = usuario;
    }
}
