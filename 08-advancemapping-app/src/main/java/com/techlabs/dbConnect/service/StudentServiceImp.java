package com.techlabs.dbConnect.service;

import com.techlabs.dbConnect.AddressResponse;
import com.techlabs.dbConnect.PageResponse;
import com.techlabs.dbConnect.StudentPageResponse;

import com.techlabs.dbConnect.entity.Student;
import com.techlabs.dbConnect.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentRepository studentRepo;

    @Override
    public Student addStudents(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public Page<Student> getAllStudent(String studentName, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        if (studentName != null && !studentName.isEmpty()) {
            return studentRepo.findByStudentName(studentName, pageable);
        } else {
            return studentRepo.findAll(pageable);
        }
    }

    @Override
    public PageResponse<StudentPageResponse> getStudent(String studentName, int pageNo, int pageSize) {
        Page<Student> studentPage = getAllStudent(studentName, pageNo, pageSize);

        List<StudentPageResponse> studentDTOs = studentPage.getContent()
                .stream()
                .map(student -> new StudentPageResponse(
                        student.getRollNumber(),
                        student.getStudentName(),
                        student.getSAge()))
                .collect(Collectors.toList());

        return new PageResponse<>(
                studentPage.getTotalPages(),
                studentPage.getTotalElements(),
                studentPage.getSize(),
                studentDTOs,
                studentPage.isLast()
        );
    }

    @Override
    public AddressResponse getAddressByRollNumber(int rollNumber) {
        Optional<Student> studentOptional = studentRepo.findById(rollNumber);

        if (studentOptional.isPresent() && studentOptional.get().getStudentAddress() != null) {
            var address = studentOptional.get().getStudentAddress();
            return new AddressResponse(
                    address.getAddressId(),
                    address.getBuildingName(),
                    address.getCityName(),
                    address.getPinCode()
            );
        } else {
            return null; // Will be handled appropriately in the controller
        }
    }

    public AddressResponse updateAddressByRollNumber(int rollNumber, String cityName, String buildingName, Integer pinCode) {
        Optional<Student> studentOptional = studentRepo.findById(rollNumber);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            var address = student.getStudentAddress();

            if (address != null) {
                if (cityName != null && !cityName.isEmpty()) {
                    address.setCityName(cityName);
                }
                if (buildingName != null && !buildingName.isEmpty()) {
                    address.setBuildingName(buildingName);
                }
                if (pinCode != null) {
                    address.setPinCode(pinCode);
                }
                studentRepo.save(student);

                return new AddressResponse(
                        address.getAddressId(),
                        address.getBuildingName(),
                        address.getCityName(),
                        address.getPinCode()
                );
            } else {
                throw new IllegalArgumentException("Address not found for the given roll number.");
            }
        } else {
            throw new IllegalArgumentException("Student not found for the given roll number.");
        }
    }
}
