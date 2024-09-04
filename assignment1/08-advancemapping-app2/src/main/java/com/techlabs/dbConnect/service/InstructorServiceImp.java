package com.techlabs.dbConnect.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.PageResponse;
import com.techlabs.dbConnect.dto.CourseDto;
import com.techlabs.dbConnect.dto.InstructorDto;
import com.techlabs.dbConnect.entity.Course;
import com.techlabs.dbConnect.entity.Instructor;
import com.techlabs.dbConnect.repository.CourseRepository;
import com.techlabs.dbConnect.repository.InstructorRepository;

@Service
public class InstructorServiceImp implements InstructorService{

	@Autowired
	private InstructorRepository instructorRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	@Lazy
	private CourseService courseService;
	
//	@Override
//	public Instructor addInstructor(Instructor instructor) {
//		// TODO Auto-generated method stub
//		return 
//	}

	@Override
	public InstructorDto addInstructor(InstructorDto instructordto) {
		// TODO Auto-generated method stub
		
		Instructor instructor = toInstructorMapper(instructordto);
		
		instructor = instructorRepo.save(instructor);
		
		return (toInstructorDtoMapper(instructor));
	}
	
	private Instructor toInstructorMapper(InstructorDto instructordto) {
		Instructor instructor = new Instructor();
		instructor.setInstructorName(instructordto.getInstructorName());
		instructor.setInstructorEmail(instructordto.getInstructorEmail());
		instructor.setQualifications(instructordto.getQualifications());
		return instructor;
	}

	private InstructorDto toInstructorDtoMapper(Instructor instructor) {
		InstructorDto instructordto = new InstructorDto();
		instructordto.setInstructorId(instructor.getInstructorId());
		instructordto.setInstructorEmail(instructor.getInstructorEmail());
		instructordto.setQualifications(instructor.getInstructorName());
		instructordto.setInstructorName(instructor.getInstructorName());
		return instructordto;
	}
	
	@Override
	public Instructor allocateCourses(int instructorId, List<Course> courses) {
		// TODO Auto-generated method stub
		Instructor dbInstructor;
		Optional<Instructor> instructorOptional = instructorRepo.findById(instructorId);
		if(!instructorOptional.isPresent())
			return null;
		dbInstructor = instructorOptional.get();
		
		List<Course> dbCourses = dbInstructor.getCourses();
		
		//List<Course> toUpdateCourses = new ArrayList<>();
		
		courses.forEach((course)->{
			Course temp =courseRepo.findById(course.getCourseId()).get();
			
			temp.setInstructor(dbInstructor);
			dbCourses.add(temp);
		});
		
		dbInstructor.setCourses(dbCourses);
		
		return instructorRepo.save(dbInstructor);
	}

//	@Override
//	public InstructorDto getInstructor(int instructorId) {
//		// TODO Auto-generated method stub
//		
//		Optional<Instructor> optionalInstructor = instructorRepo.findById(instructorId);
//		if(!optionalInstructor.isPresent()) {
//			return null;
//		}
//		
//		Instructor instructorDb = optionalInstructor.get();
//		
//		return toInstructorDtoMapper(instructorDb);
//	}
//	
	@Override
	public InstructorDto getInstructor(int instructorId) {
	    Optional<Instructor> optionalInstructor = instructorRepo.findById(instructorId);
	    if (!optionalInstructor.isPresent()) {
	        System.out.println("Instructor not found for ID: " + instructorId);
	        return null;
	    }
	    
	    Instructor instructorDb = optionalInstructor.get();
	    InstructorDto instructorDto = toInstructorDtoMapper(instructorDb);
	    if (instructorDto == null) {
	        System.out.println("Mapping returned null for Instructor ID: " + instructorId);
	    }
	    return instructorDto;
	}


	@Override
	public List<CourseDto> getInstructorCourses(int instructorId) {
	        return null;
	    }
	@Override
	public PageResponse<InstructorDto> getAllInstructor(int pageNumber, int pageSize){
	    // Fetch the paginated data for instructors
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Instructor> instructorPage = instructorRepo.findAll(pageable);
		
		PageResponse<InstructorDto> instructorDtoPageResponse = new PageResponse<>();
		instructorDtoPageResponse.setSize(instructorPage.getSize());
		instructorDtoPageResponse.setTotalElements(instructorPage.getNumberOfElements());
		instructorDtoPageResponse.setTotalPages(instructorPage.getTotalPages());
	   
		List<InstructorDto> instructorDto = new ArrayList<>();
		instructorPage.getContent().forEach((instructor) ->{
			instructorDto.add(toInstructorDtoMapper(instructor));
		});
		
		instructorDtoPageResponse.setContents(instructorDto);
		return instructorDtoPageResponse;
	}


	// course instructor allocate
	
}
