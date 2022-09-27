package com.example.propertyservice.controller;

import com.example.propertyservice.models.UserModel;
import com.example.propertyservice.repositories.UserRepository;
import com.example.propertyservice.services.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    PropertyService propertyService;

    @Mock
    private static UserRepository repository;
    @Mock
    private Clock clock;
    private static ZonedDateTime NOW = ZonedDateTime.of(2022,9,26,10,00,00,00, ZoneId.of("GMT"));

    private UserModel userModel;

    @BeforeEach
    void setUp() {
        propertyService = new PropertyService(repository, clock);
        userModel = UserModel
                .builder()
                .id(UUID.fromString("9e6586be-3d8f-11ed-b878-0242ac120002"))
                .nome("kaduzada")
                .cpf("12345678911")
                .email("kaduzada@email.com")
                .dataNascimento(LocalDate.of(2002, 11, 10))
                .build();
    }

    void setUpTime() {
        when(clock.getZone())
                .thenReturn(NOW.getZone());
        when(clock.instant())
                .thenReturn(NOW.toInstant());
    }

    @Test
    public void deveRetornarCreatedQuandoChamarSave() {
        setUpTime();
        when(repository.existsByCpf(userModel.getCpf()))
                .thenReturn(false);
        when(repository.existsByEmail(userModel.getEmail()))
                .thenReturn(false);
        when(repository.save(userModel))
                .thenReturn(userModel);

        ResponseEntity<Object> response = propertyService.save(userModel);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }
    @Test
    public void deveRetornarCONFLITQuandoExistirCpf() {
        when(repository.existsByCpf(userModel.getCpf()))
                .thenReturn(true);

        ResponseEntity<Object> response = propertyService.save(userModel);
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }

    @Test
    public void deveRetornarCONFLITQuandoExistirEmail() {
        when(repository.existsByCpf(userModel.getCpf()))
                .thenReturn(false);
        when(repository.existsByEmail(userModel.getEmail()))
                .thenReturn(true);

        ResponseEntity<Object> response = propertyService.save(userModel);
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }
    @Test
    public void deveRetornarCONFLITQuandoTamanhoCpfMenorQue11() {
        userModel.setCpf("1234567891");

        when(repository.existsByCpf(userModel.getCpf()))
                .thenReturn(false);
        when(repository.existsByEmail(userModel.getEmail()))
                .thenReturn(false);

        ResponseEntity<Object> response = propertyService.save(userModel);
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }

    @Test
    public void deveRetornarCONFLITQuandoDatanascimentoForDepoisOuIgualADataAtual() {
        setUpTime();

        userModel.setDataNascimento(LocalDate.of(2022,9,27));

        when(repository.existsByCpf(userModel.getCpf()))
                .thenReturn(false);
        when(repository.existsByEmail(userModel.getEmail()))
                .thenReturn(false);

        ResponseEntity<Object> response = propertyService.save(userModel);
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }
    @Test
    public void deveRetornarNOT_FOUNDQuandoListaUsarModelForVazia() {
        List<UserModel> listEmpty = new ArrayList<UserModel>();
        when(repository.findAll())
                .thenReturn(listEmpty);
       ResponseEntity<Object> response = propertyService.findAll();
       assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void deveRetornarOKQuandoHouverUsuariosNaLista() {
        List<UserModel> list = new ArrayList<UserModel>();
        list.add(userModel);
        when(repository.findAll())
                .thenReturn(list);
        ResponseEntity<Object> response = propertyService.findAll();
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    public void deveRetornarEntidadAoChamarFindById() {
        UUID codigo = UUID.fromString("9e6586be-3d8f-11ed-b878-0242ac120002");
        when(repository.findById(codigo))
                .thenReturn();



    }

}
