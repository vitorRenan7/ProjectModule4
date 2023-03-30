package br.com.totvs.hotel.service;

import br.com.totvs.hotel.model.EnderecoModel;
import br.com.totvs.hotel.repository.EnderecoRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    private EnderecoModel save(EnderecoModel enderecoModel) {
        return enderecoRepository.save(enderecoModel);
    }

    public EnderecoModel criarEndereco(String cep) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;
        Gson gson = new Gson();

        try {
            request = HttpRequest.newBuilder(new URI("https://viacep.com.br/ws/%s/json/".formatted(cep))).GET().build();
        } catch (URISyntaxException error) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, error.getMessage());
        }

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            EnderecoModel enderecoModel = gson.fromJson(response.body(), EnderecoModel.class);
            if (enderecoModel.getBairro() == null) {
                throw new IllegalArgumentException("cep informado não existe");
            }
            return enderecoModel;
        } catch (IOException | InterruptedException | IllegalArgumentException error) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, error.getMessage());
        }
    }

}
