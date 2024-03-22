package gui.attus.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gui.attus.backend.entities.Endereco;
import gui.attus.backend.entities.Pessoa;
import gui.attus.backend.repositories.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaService pessoaService;

    public List<Endereco> consultarEnderecosPorPessoa(Long idPessoa) {
        return enderecoRepository.findByPessoaId(idPessoa);
    }
    
    public Endereco criarEnderecoParaPessoa(Long idPessoa, Endereco endereco) {
        try {
            Optional<Pessoa> pessoa = pessoaService.consultarPessoa(idPessoa);

            if(pessoa.isPresent()) {
                endereco.setPessoaId(idPessoa);
                return enderecoRepository.save(endereco);
            } else {
                throw new IllegalArgumentException("Pessoa não encontrada!");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Endereco consultarEnderecoPrincipal(Long idPessoa) {
        List<Endereco> enderecos = enderecoRepository.findByPessoaId(idPessoa);
        
        for (Endereco endereco : enderecos) {
            if (endereco.isPrincipal()) {
                return endereco;
            }
        }
        
        throw new IllegalArgumentException("Essa pessoa não possui endereço principal registrado!");
    }

    public void definirEnderecoPrincipal(Long idEndereco) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(idEndereco);

        if (enderecoOptional.isPresent()) {
            Endereco endereco = enderecoOptional.get();
            Long pessoaId = endereco.getPessoaId();

            // Marcar o endereço atual como principal
            endereco.setPrincipal(true);
            enderecoRepository.save(endereco);

            // Buscar todos os endereços da pessoa
            List<Endereco> enderecosDaPessoa = this.consultarEnderecosPorPessoa(pessoaId);

            // Desmarcar todos os outros endereços da mesma pessoa como não principais
            for (Endereco outroEndereco : enderecosDaPessoa) {
                if (!outroEndereco.getId().equals(idEndereco)) {
                    outroEndereco.setPrincipal(false);
                    enderecoRepository.save(outroEndereco);
                }
            }
        } else {
            throw new IllegalArgumentException("Endereço não encontrado!");
        }
    }

    public Endereco buscarEnderecoPrincipalPorPessoa(Long idPessoa) {
        List<Endereco> enderecos = enderecoRepository.findByPessoaId(idPessoa);

        Optional<Endereco> enderecoPrincipalOptional = enderecos.stream()
                .filter(Endereco::isPrincipal)
                .findFirst();

        return enderecoPrincipalOptional.orElseThrow(() -> new IllegalArgumentException("Essa pessoa não possui endereço principal registrado!"));
    }

    public Endereco editarEndereco(Long idEndereco, Endereco enderecoEditado) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(idEndereco);

        if (enderecoOptional.isPresent()) {
            Endereco endereco = enderecoOptional.get();
            endereco.setLogradouro(enderecoEditado.getLogradouro());
            endereco.setCep(enderecoEditado.getCep());
            endereco.setNumero(enderecoEditado.getNumero());
            endereco.setCidade(enderecoEditado.getCidade());
            endereco.setEstado(enderecoEditado.getEstado());
            return enderecoRepository.save(endereco);
        } else {
            throw new IllegalArgumentException("Endereço não encontrado!");
        }
    }
}
