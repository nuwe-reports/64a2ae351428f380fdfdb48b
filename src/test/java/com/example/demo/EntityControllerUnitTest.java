
package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import java.time.LocalDateTime;
import java.time.format.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.demo.controllers.*;
import com.example.demo.repositories.*;
import com.example.demo.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DoctorController.class)
class DoctorControllerUnitTest{

    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetNoDoctors() throws Exception {
        mockMvc.perform(get("/api/doctors"))
            .andExpect(status().isNoContent());

    }

    @Test
    void shouldGetDoctorById() throws Exception {
        Doctor d1 = new Doctor("Lorenzo", "Llabres", 28, "lorenzo@llabres.com");
        d1.setId(1);

        Optional<Doctor> opt = Optional.of(d1);
        
        assertThat(opt)
            .isPresent()
            .contains(d1);

        assertThat(d1.getId()).isEqualTo(1);

        when(doctorRepository.findById(d1.getId())).thenReturn(opt);
        mockMvc.perform(get("/api/doctors/" + d1.getId()))
                .andExpect(status().isOk())
                ;

    }

    @Test 
    void shouldNotGetDoctorById() throws Exception{
        long id = 31;
        mockMvc.perform(get("/api/doctors/" + id))
                .andExpect(status().isNotFound())
                ;
    }

    @Test
    void shouldGetThreeDoctors() throws Exception {
        Doctor d1 = new Doctor("Lorenzo", "Llabres", 28, "lorenzo@llabres.com");
        Doctor d2 = new Doctor("Alberto", "Roldan", 38, "alberto@roldan.com");
        Doctor d3 = new Doctor("Lucas", "Barrena", 39, "lucas@barrena.com");

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(d1);
        doctors.add(d2);
        doctors.add(d3);

        when(doctorRepository.findAll()).thenReturn(doctors);
        mockMvc.perform(get("/api/doctors"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldPostDoctor() throws Exception {
        Doctor d1 = new Doctor("Lorenzo", "Llabres", 28, "lorenzo@llabres.com");

        mockMvc.perform(post("/api/doctor").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(d1)))
                .andExpect(status().isCreated())
                ;

    }
    
    @Test
    void shouldNotDeleteDoctor() throws Exception{
        long id = 31;
        mockMvc.perform(delete("/api/doctors/" + id))
                .andExpect(status().isNotFound())
                ;
    }

    @Test
    void shouldDeleteDoctorById() throws Exception {
        Doctor d1 = new Doctor("Lorenzo", "Llabres", 28, "lorenzo@llabres.com");
        d1.setId(1);

        Optional<Doctor> opt = Optional.of(d1);
        
        assertThat(opt)
            .isPresent()
            .contains(d1);
        assertThat(d1.getId()).isEqualTo(1);

        when(doctorRepository.findById(d1.getId())).thenReturn(opt);
        mockMvc.perform(delete("/api/doctors/" + d1.getId()))
                .andExpect(status().isOk())
                ;

    }
    
    @Test
    void shouldDeleteAllDoctors() throws Exception{
        mockMvc.perform(delete("/api/doctors"))
                .andExpect(status().isOk())
                ;
    }
}


@WebMvcTest(PatientController.class)
class PatientControllerUnitTest{

    @MockBean
    private PatientRepository patientRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetNoPatient() throws Exception {
        mockMvc.perform(get("/api/patients"))
            .andExpect(status().isNoContent());

    }

    @Test
    void shouldGetPatientById() throws Exception {
        Patient p1 = new Patient("Lorenzo", "Llabres", 28, "lorenzo@llabres.com");
        p1.setId(1);

        Optional<Patient> opt = Optional.of(p1);
        
        assertThat(opt)
            .isPresent()
            .contains(p1);

        assertThat(p1.getId()).isEqualTo(1);

        when(patientRepository.findById(p1.getId())).thenReturn(opt);
        mockMvc.perform(get("/api/patients/" + p1.getId()))
                .andExpect(status().isOk())
                ;

    }

    @Test 
    void shouldNotGetPatientById() throws Exception{
        long id = 31;
        mockMvc.perform(get("/api/patients/" + id))
                .andExpect(status().isNotFound())
                ;
    }

    @Test
    void shouldGetThreePatients() throws Exception {
        Patient p1 = new Patient("Lorenzo", "Llabres", 28, "lorenzo@llabres.com");
        Patient p2 = new Patient("Alberto", "Roldan", 38, "alberto@roldan.com");
        Patient p3 = new Patient("Lucas", "Barrena", 39, "lucas@barrena.com");

        List<Patient> patients = new ArrayList<>();
        patients.add(p1);
        patients.add(p2);
        patients.add(p3);

        when(patientRepository.findAll()).thenReturn(patients);
        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldPostPatient() throws Exception {
        Patient p1 = new Patient("Lorenzo", "Llabres", 28, "lorenzo@llabres.com");

        mockMvc.perform(post("/api/patient").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(p1)))
                .andExpect(status().isCreated())
                ;

    }

    @Test
    void shouldNotDeletePatient() throws Exception{
        long id = 31;
        mockMvc.perform(delete("/api/patients/" + id))
                .andExpect(status().isNotFound())
                ;
    }

    @Test
    void shouldDeletePatientById() throws Exception {
        Patient p1 = new Patient("Lorenzo", "Llabres", 28, "lorenzo@llabres.com");
        p1.setId(1);

        Optional<Patient> opt = Optional.of(p1);
        
        assertThat(opt)
            .isPresent()
            .contains(p1);

        assertThat(p1.getId()).isEqualTo(1);

        when(patientRepository.findById(p1.getId())).thenReturn(opt);
        mockMvc.perform(delete("/api/patients/" + p1.getId()))
                .andExpect(status().isOk())
                ;

    }

    @Test
    void shouldDeleteAllPatients() throws Exception{
        mockMvc.perform(delete("/api/patients"))
                .andExpect(status().isOk())
                ;
    }

}


@WebMvcTest(RoomController.class)
class RoomControllerUnitTest{

    @MockBean
    private RoomRepository roomRepository;

    @Autowired 
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetNoRoom() throws Exception {
        mockMvc.perform(get("/api/rooms"))
            .andExpect(status().isNoContent());

    }

    @Test
    void shouldGetRoomById() throws Exception {
        Room r1 = new Room("Dermatology");

        Optional<Room> opt = Optional.of(r1);
        
        assertThat(opt)
            .isPresent()
            .contains(r1);

        assertThat(r1.getRoomName()).isEqualTo("Dermatology");

        when(roomRepository.findByRoomName(r1.getRoomName())).thenReturn(opt);
        mockMvc.perform(get("/api/rooms/" + r1.getRoomName()))
                .andExpect(status().isOk())
                ;

    }

    @Test 
    void shouldNotGetRoomById() throws Exception{
        String roomName = "id";
        mockMvc.perform(get("/api/rooms/" + roomName))
                .andExpect(status().isNotFound())
                ;
    }

    @Test
    void shouldGetThreeRooms() throws Exception {
        Room r1 = new Room("Dermatology");
        Room r2 = new Room("Ginecology");
        Room r3 = new Room("Oncology");

        List<Room> rooms = new ArrayList<>();
        rooms.add(r1);
        rooms.add(r2);
        rooms.add(r3);

        when(roomRepository.findAll()).thenReturn(rooms);
        mockMvc.perform(get("/api/rooms"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldPostRoom() throws Exception {
        Room r1 = new Room("Dermatology");

        mockMvc.perform(post("/api/room").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(r1)))
                .andExpect(status().isCreated())
                ;

    }

    @Test
    void shouldNotDeleteRoom() throws Exception{
        String roomName = "id";
        mockMvc.perform(delete("/api/rooms/" + roomName))
                .andExpect(status().isNotFound())
                ;
    }

    @Test
    void shouldDeleteRoomById() throws Exception {
        Room r1 = new Room("Dermatology");

        Optional<Room> opt = Optional.of(r1);
        
        assertThat(opt)
            .isPresent()
            .contains(r1);

        assertThat(r1.getRoomName()).isEqualTo("Dermatology");

        when(roomRepository.findByRoomName(r1.getRoomName())).thenReturn(opt);
        mockMvc.perform(delete("/api/rooms/" + r1.getRoomName()))
                .andExpect(status().isOk())
                ;

    }

    @Test
    void shouldDeleteAllRooms() throws Exception{
        mockMvc.perform(delete("/api/rooms"))
                .andExpect(status().isOk())
                ;
    }

}
