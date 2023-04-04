import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http"

//
@Injectable({
  providedIn: "root"
})

//

export class ReservaQuartoService {

  urlApi = 'http://localhost:8080/clientes'

  //Guardo na variavel http, tudo que tem no Client
  constructor(private http: HttpClient) { }

  // ' :any ', pode ser qualquer coisa
  cadastrar(dados: any){
    return this.http.post(this.urlApi, dados)
  }
}