package com.contractormanagemet.Contractor.Magement.Controller;


import com.contractormanagemet.Contractor.Magement.Entity.RelievingLatter;
import com.contractormanagemet.Contractor.Magement.Service.RelievingLatterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RelievingLatterController {

    @Autowired
    private RelievingLatterService relievingLatterService;

    @PostMapping("/createRelievinglatter")
    @PreAuthorize("hasRole('Admin')")
    public RelievingLatter createRelievinglatter(@RequestBody RelievingLatter relievingLatter){
        return relievingLatterService.createRelievinglatter(relievingLatter);
    }

    @GetMapping("/getAllRelievingLatter")
    @PreAuthorize("hasRole('Admin')")
    public List<RelievingLatter> getAllRelievingLatter(){
        return relievingLatterService.getAllRelievingLatter();
    }

    @GetMapping("/getAllRelievingLatterbyid/{id}")
    @PreAuthorize("hasRole('Admin')")
    public RelievingLatter getAllRelievingLatterbyid(@PathVariable("id") long id){
        return  relievingLatterService.getAllRelievingLatterbyid(id);
    }

    @PutMapping("/updateRelievingLatter/{id}")
    @PreAuthorize("hasRole('Admin')")
    public RelievingLatter updateRelievingLatter(@PathVariable("id") Long id, @RequestBody RelievingLatter relievingLatter) {
        relievingLatter.setId(id);
        return relievingLatterService.updateRelievingLatter(relievingLatter);
    }
    @DeleteMapping("/deleteRelievingLatter/{id}")
    @PreAuthorize("hasRole('Admin')")

    public ResponseEntity<String> deleteRelievingLatter(@PathVariable("id") Long id) {
        relievingLatterService.deleteRelievingLatter(id);
        return ResponseEntity.ok("Salary slip deleted successfully");
    }
}