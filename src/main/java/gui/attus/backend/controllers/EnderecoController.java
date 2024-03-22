package gui.attus.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gui.attus.backend.entities.Endereco;
import gui.attus.backend.services.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    
    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/consultar/{idPessoa}")
    public ResponseEntity<List<Endereco>> consultarEnderecosPorPessoa(@PathVariable Long idPessoa) {
        List<Endereco> enderecos = enderecoService.consultarEnderecosPorPessoa(idPessoa);
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/consultar-principal/{idPessoa}")
    public ResponseEntity<Endereco> consultarEnderecoPrincipal(@PathVariable Long idPessoa) {
        Endereco enderecoPrincipal = enderecoService.consultarEnderecoPrincipal(idPessoa);
        return ResponseEntity.ok(enderecoPrincipal);
    }

    @PostMapping("/criar/{idPessoa}")
    public ResponseEntity<Endereco> criarEnderecoParaPessoa(@PathVariable Long idPessoa, @RequestBody Endereco endereco) {
        Endereco novoEndereco = enderecoService.criarEnderecoParaPessoa(idPessoa, endereco);
        return ResponseEntity.ok(novoEndereco);
    }

    @PutMapping("/definir-principal/{idEndereco}")
    public ResponseEntity<String> definirEnderecoPrincipal(@PathVariable Long idEndereco) {
        enderecoService.definirEnderecoPrincipal(idEndereco);
        return ResponseEntity.ok("Sucesso!");
    }

    @PutMapping("/editar/{idEndereco}")
    public ResponseEntity<Endereco> editarEndereco(@PathVariable Long idEndereco, @RequestBody Endereco enderecoEditado) {
        Endereco enderecoAtualizado = enderecoService.editarEndereco(idEndereco, enderecoEditado);
        return ResponseEntity.ok(enderecoAtualizado);
    }
}
