package com.usa.ciclo3.retociclo3.service;

import com.usa.ciclo3.retociclo3.model.Doctor;
import com.usa.ciclo3.retociclo3.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAll() {
        return doctorRepository.getAll();
    }

    public Optional<Doctor> getDoctor(int id) {
        return doctorRepository.getDoctor(id);
    }

    public Doctor save(Doctor doctor) {
        if (doctor.getId() == null) {
            return doctorRepository.save(doctor);
        } else {
            Optional<Doctor> tmpDoctor = doctorRepository.getDoctor(doctor.getId());
            if (tmpDoctor.isEmpty()) {
                return doctorRepository.save(doctor);
            } else {
                return doctor;
            }
        }
    }

    public Doctor update(Doctor doctor) {
        if(doctor.getId()!=null){
            Optional<Doctor> e= doctorRepository.getDoctor(doctor.getId());
            if(!e.isEmpty()){
                if(doctor.getName()!=null){
                    e.get().setName(doctor.getName());
                }
                if(doctor.getDepartment()!=null){
                    e.get().setDepartment(doctor.getDepartment()) ;
                }
                if(doctor.getYear() !=null){
                    e.get().setYear(doctor.getYear());
                }
                if(doctor.getDescription()!=null){
                    e.get().setDescription(doctor.getDescription());
                }
                if(doctor.getSpecialty()!=null){
                    e.get().setSpecialty(doctor.getSpecialty());
                }
                doctorRepository.save(e.get());
                return e.get();
            }else{
                return doctor;
            }
        }else{
            return doctor;
        }
    }

    public boolean deleteDoctor(int id) {
        Boolean aBoolean = getDoctor(id).map(cabin -> {
            doctorRepository.delete(cabin);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
