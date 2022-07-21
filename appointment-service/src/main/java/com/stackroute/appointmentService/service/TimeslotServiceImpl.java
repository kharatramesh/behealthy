package com.stackroute.appointmentService.service;


import com.stackroute.appointmentService.exception.CustomisedExceptions;
import com.stackroute.appointmentService.model.Timeslot;
import com.stackroute.appointmentService.repository.TimeslotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TimeslotServiceImpl implements TimeslotService {



    @Autowired
    private TimeslotRepository timeslotRepository;

    @Autowired
    public TimeslotServiceImpl(TimeslotRepository timeslotRepository){
        this.timeslotRepository= timeslotRepository;
    }

    @Override
    public Timeslot createSlot(Timeslot timeslot) {

        return timeslotRepository.save(timeslot);
    }

    @Override
    public Timeslot updateSlotAvailabilityBySlotId(int slotId) {
      Optional<Timeslot> slot = timeslotRepository.findById(slotId);
      Timeslot timeslot = slot.get();
      timeslot.setSlotAvailability(false);
      return timeslotRepository.save(timeslot);
    }



    @Override
    public List<Timeslot> getSlotByDateAndDoctorEmail(LocalDate slotDate, String doctorEmail) {
        List<Timeslot> list = timeslotRepository.findBySlotDateAndDoctorEmail(slotDate,doctorEmail);
        if(list.size()==0){
            throw new CustomisedExceptions("No available slot for the given date..!!");
        }
        return list;

    }



}
