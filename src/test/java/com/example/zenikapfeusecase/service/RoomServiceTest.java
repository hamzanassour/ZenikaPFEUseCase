package com.example.zenikapfeusecase.service;

import com.example.zenikapfeusecase.entity.Equipment;
import com.example.zenikapfeusecase.entity.Reservation;
import com.example.zenikapfeusecase.entity.Room;
import com.example.zenikapfeusecase.enumerations.Equip;
import com.example.zenikapfeusecase.enumerations.MType;
import com.example.zenikapfeusecase.repository.EquipmentRepository;
import com.example.zenikapfeusecase.repository.ReservationRepository;
import com.example.zenikapfeusecase.repository.RoomRepository;
import com.example.zenikapfeusecase.service.implementation.RoomServiceImpl;
import org.apache.catalina.LifecycleState;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith (SpringRunner.class)
public class RoomServiceTest {

    private RoomService roomService ;

    @Autowired
    RoomRepository roomRepository ;
    @Autowired
    ReservationRepository reservationRepository ;
    @Autowired
    EquipmentRepository equipmentRepository ;

    @Before
    public void setUp(){
        roomService = new RoomServiceImpl (roomRepository , reservationRepository);
    }

    @Test
    public void should_return_true_if_the_room_have_the_needed_capacity(){
        Room r1 =  new Room ( 1l,"c001" , 18L , null,null);
        boolean result =  roomService.checkCapacity (10, r1);
        assertEquals (true , result);

    }

    @Test
    public void should_return_rooms_candidate_for_reservation(){

        Room r1 =  new Room ( 1l,"c001" , 12L , null,null);
        Room r2 =new Room ( 2l,"c002" , 23L , null,null);
        Room r3 = new Room (3l,"c003" , 17L , null,null);
        Room r4 = new Room ( 4l,"c004" , 39L , null, null);
        Room r5 = new Room (5l,"c005" , 27L , null,null);
        Room r6 = new Room (6l,"c006" , 24L , null,null);
        Reservation reservation1 = new Reservation (1L, LocalDateTime.of (2022 , 11, 23 , 8 ,00) ,10 , r1 , MType.VC  );
        Reservation reservation2 = new Reservation (2L, LocalDateTime.of (2022 , 11, 23 , 9 ,00) ,16 , r2 , MType.RC  );
        Reservation reservation3 = new Reservation (3L, LocalDateTime.of (2022 , 11, 23 , 10 ,00) ,6 , r3 , MType.SPEC  );
        Reservation reservation4 = new Reservation (4L, LocalDateTime.of (2022 , 11, 23 , 11 ,00) ,10 , r4 , MType.RC  );
        Reservation reservation5 = new Reservation (5L, LocalDateTime.of (2022 , 11, 23 , 12 ,00) ,5 , r5 , MType.RS  );
        Reservation reservation6 = new Reservation (6L, LocalDateTime.of (2022 , 11, 23 , 14 ,00) ,3 , r2 , MType.SPEC  );
        roomRepository.save (r1);
        roomRepository.save (r2);
        roomRepository.save (r3);
        roomRepository.save (r4);
        roomRepository.save (r5);
        roomRepository.save (r6);
        reservationRepository.save (reservation1);
        reservationRepository.save (reservation2);
        reservationRepository.save (reservation3);
        reservationRepository.save (reservation4);
        reservationRepository.save (reservation5);
        reservationRepository.save (reservation6);

        // reservation test
        Reservation reservation = new Reservation ();
        reservation.setNbrOfPearson (10);
        reservation.setMeetMType (MType.VC);
        reservation.setDate (LocalDateTime.of (2022 , 11, 23 , 10 ,00));

       List<Room> roomList = roomService.getRoomsEligibleForReservation (reservation.getDate ().minusHours (1), reservation.getDate ().plusHours (1));

        assertEquals(3 , roomList.size ());
    }
    @Test
    public void should_return_rooms_that_contain_demanded_equipment(){

        Equipment equipment1 = new Equipment (1L , Equip.ECRAN);
        Equipment equipment2 = new Equipment (2L , Equip.NEANT);
        Equipment equipment3 = new Equipment (3L , Equip.PIEUVRE);
        Equipment equipment4 = new Equipment (4L , Equip.TABLEAU);
        Equipment equipment5 = new Equipment (5L , Equip.WEBCAM);

        equipmentRepository.save (equipment1);
        equipmentRepository.save (equipment2);
        equipmentRepository.save (equipment3);
        equipmentRepository.save (equipment4);
        equipmentRepository.save (equipment5);

        List<Equipment> equipmentForRoom5 = new ArrayList<> ();
        equipmentForRoom5.add (equipment1);
        equipmentForRoom5.add (equipment3);
        equipmentForRoom5.add (equipment5);

        Room r1 =  new Room ( 1l,"c001" , 12L , null,null);
        Room r2 =new Room ( 2l,"c002" , 23L , null,null);
        Room r3 = new Room (3l,"c003" , 17L , null,null);
        Room r4 = new Room ( 4l,"c004" , 39L , null, null);
        Room r5 = new Room (5l,"c005" , 27L , null,equipmentForRoom5);
        Room r6 = new Room (6l,"c006" , 24L , null,null);
        Reservation reservation1 = new Reservation (1L, LocalDateTime.of (2022 , 11, 23 , 8 ,00) ,10 , r1 , MType.VC  );
        Reservation reservation2 = new Reservation (2L, LocalDateTime.of (2022 , 11, 23 , 9 ,00) ,16 , r2 , MType.RC  );
        Reservation reservation3 = new Reservation (3L, LocalDateTime.of (2022 , 11, 23 , 10 ,00) ,6 , r3 , MType.SPEC  );
        Reservation reservation4 = new Reservation (4L, LocalDateTime.of (2022 , 11, 23 , 11 ,00) ,10 , r4 , MType.RC  );
        Reservation reservation5 = new Reservation (5L, LocalDateTime.of (2022 , 11, 23 , 12 ,00) ,5 , r5 , MType.RS  );
        Reservation reservation6 = new Reservation (6L, LocalDateTime.of (2022 , 11, 23 , 14 ,00) ,3 , r2 , MType.SPEC  );
        roomRepository.save (r1);
        roomRepository.save (r2);
        roomRepository.save (r3);
        roomRepository.save (r4);
        roomRepository.save (r5);
        roomRepository.save (r6);
        reservationRepository.save (reservation1);
        reservationRepository.save (reservation2);
        reservationRepository.save (reservation3);
        reservationRepository.save (reservation4);
        reservationRepository.save (reservation5);
        reservationRepository.save (reservation6);

        // reservation test
        Reservation reservation = new Reservation ();
        reservation.setNbrOfPearson (10);
        reservation.setMeetMType (MType.VC);
        reservation.setDate (LocalDateTime.of (2022 , 11, 23 , 10 ,00));

        List<Room> roomList = roomService.getRoomsThatContainDemandedEquipment (equipmentForRoom5 , roomService.getRoomsEligibleForReservation (reservation.getDate ().minusHours (1), reservation.getDate ().plusHours (1)));

        assertEquals(1 , roomList.size ());
    }
    @Test
    public void should_return_rooms_that_can_handle_specific_meeting(){

        Equipment equipment1 = new Equipment (1L , Equip.ECRAN);
        Equipment equipment2 = new Equipment (2L , Equip.NEANT);
        Equipment equipment3 = new Equipment (3L , Equip.PIEUVRE);
        Equipment equipment4 = new Equipment (4L , Equip.TABLEAU);
        Equipment equipment5 = new Equipment (5L , Equip.WEBCAM);

        equipmentRepository.save (equipment1);
        equipmentRepository.save (equipment2);
        equipmentRepository.save (equipment3);
        equipmentRepository.save (equipment4);
        equipmentRepository.save (equipment5);

        List<Equipment> equipmentForRoom5 = new ArrayList<> ();
        equipmentForRoom5.add (equipment1);
        equipmentForRoom5.add (equipment3);
        equipmentForRoom5.add (equipment5);

        Room r1 =  new Room ( 1l,"c001" , 12L , null,null);
        Room r2 =new Room ( 2l,"c002" , 23L , null,null);
        Room r3 = new Room (3l,"c003" , 17L , null,null);
        Room r4 = new Room ( 4l,"c004" , 39L , null, null);
        Room r5 = new Room (5l,"c005" , 27L , null,equipmentForRoom5);
        Room r6 = new Room (6l,"c006" , 24L , null,null);
        Reservation reservation1 = new Reservation (1L, LocalDateTime.of (2022 , 11, 23 , 8 ,00) ,10 , r1 , MType.VC  );
        Reservation reservation2 = new Reservation (2L, LocalDateTime.of (2022 , 11, 23 , 9 ,00) ,16 , r2 , MType.RC  );
        Reservation reservation3 = new Reservation (3L, LocalDateTime.of (2022 , 11, 23 , 10 ,00) ,6 , r3 , MType.SPEC  );
        Reservation reservation4 = new Reservation (4L, LocalDateTime.of (2022 , 11, 23 , 11 ,00) ,10 , r4 , MType.RC  );
        Reservation reservation5 = new Reservation (5L, LocalDateTime.of (2022 , 11, 23 , 12 ,00) ,5 , r5 , MType.RS  );
        Reservation reservation6 = new Reservation (6L, LocalDateTime.of (2022 , 11, 23 , 14 ,00) ,3 , r2 , MType.SPEC  );
        roomRepository.save (r1);
        roomRepository.save (r2);
        roomRepository.save (r3);
        roomRepository.save (r4);
        roomRepository.save (r5);
        roomRepository.save (r6);
        reservationRepository.save (reservation1);
        reservationRepository.save (reservation2);
        reservationRepository.save (reservation3);
        reservationRepository.save (reservation4);
        reservationRepository.save (reservation5);
        reservationRepository.save (reservation6);

        // reservation test
        Reservation reservation = new Reservation ();
        reservation.setNbrOfPearson (10);
        reservation.setMeetMType (MType.VC);
        reservation.setDate (LocalDateTime.of (2022 , 11, 23 , 10 ,00));

        List<Room> roomList = roomService.getRoomsThatCanHandleSpecificMeeting (reservation.getMeetMType () , Arrays.asList (r1 , r3 , r5));

        assertEquals(1 , roomList.size ());
    }








}
