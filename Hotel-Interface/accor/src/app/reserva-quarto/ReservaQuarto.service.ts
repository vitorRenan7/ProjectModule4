import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http"
import { Observable } from "rxjs";

//
@Injectable()

//

export class ReservaQuartoService {

  urlApi = 'localhost:8080/clientes'

  //Guardo na variavel http, tudo que tem no Client
  constructor(private http: HttpClient) { }

  // ' :any ', pode ser qualquer coisa
  cadastrar(dados: any): Observable<any>{
    return this.http.post(this.urlApi, dados, {withCredentials: true, responseType: 'json'})
  }
}