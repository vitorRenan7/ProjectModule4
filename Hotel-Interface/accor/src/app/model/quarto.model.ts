export class Quarto {
    id: string;
    numero: string;
    descricao: string;
    categoria: string;
    situacao: string;
    precoHora: string;
    imagens: string[];

    constructor (id: string, numero: string, descricao: string, categoria: string, situacao: string, precoHora: string, imagens: string[]) {
        this.id = id;
        this.numero = numero;
        this.descricao = descricao;
        this.categoria = categoria;
        this.situacao = situacao;
        this.precoHora = precoHora;
        this.imagens = imagens;
    }
}
