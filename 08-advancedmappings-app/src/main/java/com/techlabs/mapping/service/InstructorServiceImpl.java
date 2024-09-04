package com.techlabs.mapping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.mapping.dto.InstructorDto;
import com.techlabs.mapping.dto.PageResponse;
import com.techlabs.mapping.entity.Course;
import com.techlabs.mapping.entity.Instructor;
import com.techlabs.mapping.repository.CourseRepository;
import com.techlabs.mapping.repository.InstructorRepository;

@Service
public class InstructorServiceImpl implements InstructorService {

	@Autowired
	private InstructorRepository instructorRepo;
	@Autowired
	private CourseRepository courseRepo;

	@Override
	public InstructorDto addInstrcutor(InstructorDto instructorDto) {

		Instructor instructor = toInstructorMapper(instructorDto);
		instructor = instructorRepo.save(instructor);

		return toInstructorDtoMapper(instructor);
	}

	public InstructorDto toInstructorDtoMapper(Instructor instructor) {
		InstructorDto instructorDto = new InstructorDto();
		instructorDto.setEmail(instructor.getEmail());
		instructorDto.setInstructorId(instructor.getInstructorId());
		instructorDto.setQualification(instructor.getQualification());
		instructorDto.setName(instructor.getName());
		return instructorDto;

	}

	public Instructor toInstructorMapper(InstructorDto instructorDto) {
		Instructor instructor = new Instructor();
		instructor.setEmail(instructorDto.getEmail());
		instructor.setName(instructorDto.getName());
		instructor.setQualification(instructorDto.getQualification());
		return instructor;

	}

	@Override
	public InstructorDto getInstructorById(int instructorId) {

		Optional<Instructor> optionalInstructor = instructorRepo.findById(instructorId);
		if (!optionalInstructor.isPresent())
			return null;
		Instructor dbInstructor = optionalInstructor.get();
		return toInstructorDtoMapper(dbInstructor);
	}

	@Override
	public PageResponse<InstructorDto> getAllInstructors(int pageNumber, int pageSize) {

		Pageable pagebale = PageRequest.of(pageNumber, pageSize);
		Page<Instructor> dbInstructorPage = instructorRepo.findAll(pagebale);

		PageResponse<InstructorDto> instructorDtoPageResponse = new PageResponse<>();
		instructorDtoPageResponse.setSize(dbInstructorPage.getSize());
		instructorDtoPageResponse.setTotalElements(dbInstructorPage.getNumberOfElements());
		instructorDtoPageResponse.setTotalPages(dbInstructorPage.getTotalPages());

		List<InstructorDto> instructorDtos = new ArrayList<>();

		dbInstructorPage.getContent().forEach((instructor) -> {

			instructorDtos.add(toInstructorDtoMapper(instructor));

		}); 

		instructorDtoPageResponse.setContents(instructorDtos);

		return instructorDtoPageResponse;
	}

	@Override
	public InstructorDto allocateCourses(int instructorId, List<Integer> courseIds) {

		Instructor dbInstructor = toInstructorMapper(getInstructorById(instructorId));
		dbInstructor.setInstructorId(instructorId);
		List<Course> dbCourses = dbInstructor.getCourses();
		if (dbCourses == null)
			dbCourses = new ArrayList<>();

		List<Course> coursesToadd = new ArrayList<>();

		courseIds.forEach((courseId) -> {

			Course course = courseRepo.findById(courseId)
					.orElseThrow(() -> new NullPointerException("Course does not exists"));

			course.setInstructor(dbInstructor);
			coursesToadd.add(course);

		});

		dbCourses.addAll(coursesToadd);
		dbInstructor.setCourses(dbCourses);

		return toInstructorDtoMapper(instructorRepo.save(dbInstructor));
	}

}
