import { Endereco } from "./endereco.model";

export class Cliente {
    id: string;
    nome: string;
    sobrenome: string;
    rg: string;
    cpf: string;
    dataNascimento: string;
    endereco: Endereco;
    email: string;
    celular: string;

    constructor(id: string, nome: string, sobrenome: string, rg: string, cpf: string, dataNascimento: string, endereco: Endereco, email: string, celular: string) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.email = email;
        this.celular = celular;
    }
}
