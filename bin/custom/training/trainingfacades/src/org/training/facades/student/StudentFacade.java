package org.training.facades.student;

import org.training.core.model.StudentModel;
import org.training.facades.student.data.StudentData;

import java.util.List;

public interface StudentFacade {

    List<StudentData> getAllStudent();
    StudentData getStudentById (final String id);
    StudentData getStudentByName (final String fullName);
}
