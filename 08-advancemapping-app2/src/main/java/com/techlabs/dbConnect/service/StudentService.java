package com.techlabs.dbConnect.service;

import com.techlabs.dbConnect.AddressResponse;
import com.techlabs.dbConnect.PageResponse;
import com.techlabs.dbConnect.StudentPageResponse;
import com.techlabs.dbConnect.entity.Student;
import org.springframework.data.domain.Page;

public interface StudentService {

    Student addStudents(Student student);

    Page<Student> getAllStudent(String studentName, int pageNo, int pageSize);

    PageResponse<StudentPageResponse> getStudent(String studentName, int pageNo, int pageSize);

    AddressResponse getAddressByRollNumber(int rollNumber);
    
    AddressResponse updateAddressByRollNumber(int rollNumber, String cityName, String buildingName, Integer pinCode);

}
