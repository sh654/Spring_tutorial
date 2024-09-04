package com.techlabs.dbConnect.controller;

import com.techlabs.dbConnect.AddressResponse;
import com.techlabs.dbConnect.PageResponse;
import com.techlabs.dbConnect.StudentPageResponse;

import com.techlabs.dbConnect.entity.Student;
import com.techlabs.dbConnect.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/studentapp")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<PageResponse<StudentPageResponse>> getStudents(
            @RequestParam(required = false) String studentName,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {

        PageResponse<StudentPageResponse> response = studentService.getStudent(studentName, pageNo, pageSize);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> addNewStudent(@RequestBody Student student) {
        Student savedStudent = studentService.addStudents(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping("/students/{rollNumber}")
    public ResponseEntity<?> getStudentAddress(@PathVariable int rollNumber) {
        AddressResponse addressResponse = studentService.getAddressByRollNumber(rollNumber);
        if (addressResponse != null) {
            return new ResponseEntity<>(addressResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Student or Address not found for roll number: " + rollNumber, HttpStatus.NOT_FOUND);
        }
    }
    
//    @PutMapping("/students/{rollNumber}/address")
    @PutMapping("/students/address")
    public ResponseEntity<?> updateStudentAddress(
            @RequestParam int rollNumber,
            @RequestBody(required = false) String cityName,
            @RequestBody(required = false) String buildingName,
            @RequestBody(required = false) Integer pinCode
            ){
    	 //@RequestBody(required = false) Address studentAddress
        try {
            AddressResponse updatedAddress = studentService.updateAddressByRollNumber(rollNumber, cityName, buildingName, pinCode);
            return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
