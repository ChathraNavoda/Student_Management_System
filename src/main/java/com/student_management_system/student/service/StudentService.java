package com.student_management_system.student.service;

import com.student_management_system.student.exception.StudentNotFoundException;
import com.student_management_system.student.model.Student;
import com.student_management_system.student.repository.StudentRepository;
import com.student_management_system.student.exception.StudentAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService{
    private final StudentRepository studentRepository;

    @Override
    public List<Student> getStudents() {

        return studentRepository.findAll();
    }
    @Override
    public Student addStudent(Student student) {
        if (studentAlreadyExists(student.getEmail())){
            throw new StudentAlreadyExistsException(student.getEmail()+ "Already exists!");
        }
        return studentRepository.save(student);
    }


    @Override
    public Student updateStudent(Student student, Long id) {

        return studentRepository.findById(id).map(st ->{
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());

            return studentRepository.save(st);
        }).orElseThrow(()-> new StudentNotFoundException("Sorry 🥺, this student could not be found!"));
    }

    @Override
    public Student getStudentById(Long id) {
        return  studentRepository.findById(id)
                .orElseThrow(()-> new StudentNotFoundException("Sorry 🥺,student with this id :" +id +" could not be found!"));
    }

    @Override
    public void deleteStudent(Long id) {


    }

    private boolean studentAlreadyExists(String email) {
        return studentRepository.findAllByEmail(email).isPresent();
    }


}
