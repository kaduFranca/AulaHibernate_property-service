package com.example.propertyservice.services;

import com.example.propertyservice.DTOs.PropertyDto;
import com.example.propertyservice.models.UserModel;
import com.example.propertyservice.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//Controller aciona o Service e o Service aciona o Repository

@Service
public class PropertyService { //Controller aciona o service e o service aciona o Repository
    public String msgUserNotFound = "Usuário não encontrado";
    final UserRepository userRepository;
    final Clock clock;

    public PropertyService(UserRepository userRepository, Clock clock) {
        this.userRepository = userRepository;
        this.clock = clock;
    }


    @Transactional
    public ResponseEntity<Object> save(UserModel userModel) {
        if(userRepository.existsByCpf(userModel.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Aviso: Esse CPF ja está em uso.");
        }
        if(userRepository.existsByEmail(userModel.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Aviso: Esse email ja está em uso.");
        }
        long tamanhoCpf = userModel.getCpf().chars().filter(ch -> ch != ' ').count();
        if (tamanhoCpf != 11) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Aviso: CPF inválido bocó");
        }
        LocalDate dataNacimento = userModel.getDataNascimento();
        LocalDate dataAtual = LocalDate.now(clock);

        if(dataNacimento.isAfter(dataAtual) || dataNacimento.isEqual(dataAtual)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Aviso: Nasceu agr fdp?");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(userModel));
    }

    public ResponseEntity<Object> findAll() {
        List<UserModel> usuarios = userRepository.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msgUserNotFound);
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }
    public ResponseEntity<Object> findById(UUID id) {
        Optional<UserModel> userModelOptional = userRepository.findById(id);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msgUserNotFound);
        }
        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }
    public ResponseEntity<Object> findByNome(String nome) {
        Optional<UserModel> userModelOptional = userRepository.findByNome(nome);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msgUserNotFound);
        }
        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }
    @Transactional
    public ResponseEntity<Object> delete(UUID id) {

        Optional<UserModel> userModelOptional = userRepository.findById(id);

        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msgUserNotFound);
        }
        userRepository.delete(userModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso");
    }
    @Transactional
    public ResponseEntity<Object> update(UUID id, PropertyDto propertyDto) {

        Optional<UserModel> userModelOptional = userRepository.findById(id);

        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msgUserNotFound);
        }
        userModelOptional.get().setNome(propertyDto.getNome());
        userModelOptional.get().setCpf(propertyDto.getCpf());
        userModelOptional.get().setEmail(propertyDto.getEmail());
        userModelOptional.get().setDataNascimento(propertyDto.getDataNascimento());
        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }

}
