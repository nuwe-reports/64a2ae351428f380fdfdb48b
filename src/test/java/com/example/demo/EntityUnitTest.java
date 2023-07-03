package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.example.demo.entities.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
class EntityUnitTest {

	@Autowired
	private TestEntityManager entityManager;

	private Doctor d1;

	private Patient p1;

    private Room r1;

    private Appointment a1;
    private Appointment a2;
    private Appointment a3;

    @BeforeAll
    void setup(){
        d1 = new Doctor("Hector", "Alarcon", 18, "hector@alarcon.com");

        p1 = new Patient("Juan", "Rodriguez", 28, "juan@rodriguez.com");

        r1 = new Room("Dermatology");
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        LocalDateTime startsAt = LocalDateTime.parse("19:30 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("20:30 24/04/2023", formatter);

        LocalDateTime startsAt2 = LocalDateTime.parse("20:30 24/04/2023", formatter);
        LocalDateTime finishesAt2 = LocalDateTime.parse("21:30 24/04/2023", formatter);

        LocalDateTime startsAt3 = LocalDateTime.parse("20:00 24/04/2023", formatter);
        LocalDateTime finishesAt3 = LocalDateTime.parse("20:30 24/04/2023", formatter);

        a1 = new Appointment(p1, d1, r1, startsAt, finishesAt);
        a2 = new Appointment(p1, d1, r1, startsAt2, finishesAt2);
        a3 = new Appointment(p1, d1, r1, startsAt3, finishesAt3);
    }
    @Test
    void saveDoctor(){
        Doctor savedDoctor = this.entityManager.merge(d1);
        assertThat(savedDoctor.getFirstName()).isEqualTo("Hector");
        assertThat(savedDoctor.getLastName()).isEqualTo("Alarcon");
        assertThat(savedDoctor.getAge()).isEqualTo(18);
        assertThat(savedDoctor.getEmail()).isEqualTo("hector@alarcon.com");
    }

    @Test
    void savePatient(){
        Patient savedPatient = this.entityManager.merge(p1);
        assertThat(savedPatient.getFirstName()).isEqualTo("Juan");
        assertThat(savedPatient.getLastName()).isEqualTo("Rodriguez");
        assertThat(savedPatient.getAge()).isEqualTo(28);
        assertThat(savedPatient.getEmail()).isEqualTo("juan@rodriguez.com");
    }

    @Test
    void saveRoom(){
        Room savedRoom = this.entityManager.persistAndFlush(r1);
        assertThat(savedRoom.getRoomName()).isEqualTo("Dermatology");
    }

    @Test
    void saveAppointment(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        LocalDateTime startsAt = LocalDateTime.parse("19:30 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("20:30 24/04/2023", formatter);

        Appointment savedAppointment = this.entityManager.persistAndFlush(a1);
        assertThat(savedAppointment.getPatient().getFirstName()).isEqualTo("Juan");
        assertThat(savedAppointment.getDoctor().getFirstName()).isEqualTo("Hector");
        assertThat(savedAppointment.getRoom().getRoomName()).isEqualTo("Dermatology");
        assertThat(savedAppointment.getStartsAt()).isEqualTo(startsAt);
        assertThat(savedAppointment.getFinishesAt()).isEqualTo(finishesAt);
    }

    @Test
    void overlappingAppointments(){
        //("19:30 24/04/2023", formatter);
        //("20:30 24/04/2023", formatter);
        //("20:30 24/04/2023", formatter);
        //("21:30 24/04/2023", formatter);
        //("20:00 24/04/2023", formatter);
        //("20:30 24/04/2023", formatter);
        
        assertThat(a1.overlaps(a2)).isFalse();
        assertThat(a1.overlaps(a3)).isTrue();
        assertThat(a2.overlaps(a3)).isFalse();
    }
}
