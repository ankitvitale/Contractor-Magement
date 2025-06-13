package com.contractormanagemet.Contractor.Magement.Service;


import com.contractormanagemet.Contractor.Magement.Entity.RelievingLatter;
import com.contractormanagemet.Contractor.Magement.Repository.RelievingLatterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RelievingLatterService {
    @Autowired
    private RelievingLatterRepository relievingLatterRepository;
    public RelievingLatter createRelievinglatter(RelievingLatter relievingLatter) {
        return relievingLatterRepository.save(relievingLatter);
    }

    public List<RelievingLatter> getAllRelievingLatter() {
        return relievingLatterRepository.findAll();
    }

    public RelievingLatter getAllRelievingLatterbyid(long id) {
        return relievingLatterRepository.findById(id).orElseThrow(()-> new RuntimeException("Id is not found "+id));
    }

    public RelievingLatter updateRelievingLatter(RelievingLatter relievingLatter) {
        RelievingLatter existingRelievingLatter = relievingLatterRepository.findById(relievingLatter.getId())
                .orElseThrow(() -> new RuntimeException("ID not found: " + relievingLatter.getId()));

        // Update fields of the existing entity
        existingRelievingLatter.setEmpId(relievingLatter.getEmpId());
        existingRelievingLatter.setEmployeeName(relievingLatter.getEmployeeName());
        existingRelievingLatter.setCurrentDate(relievingLatter.getCurrentDate());
        existingRelievingLatter.setResignationDate(relievingLatter.getResignationDate());
        existingRelievingLatter.setDateOfjoing(relievingLatter.getDateOfjoing());
        existingRelievingLatter.setLastworkingdate(relievingLatter.getLastworkingdate()); // Fix: Update lastworkingdate
        existingRelievingLatter.setDesignation(relievingLatter.getDesignation());
        existingRelievingLatter.setDepartment(relievingLatter.getDepartment());
        existingRelievingLatter.setLocation(relievingLatter.getLocation());

        // Save and return the updated entity
        return relievingLatterRepository.save(existingRelievingLatter);
    }

    public void deleteRelievingLatter(Long id) {
        RelievingLatter relievingLatter1=relievingLatterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salary slip not found with ID: " + id));
        relievingLatterRepository.delete(relievingLatter1);
    }
}