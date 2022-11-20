package org.training.facades.student.impl;

import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.collections.CollectionUtils;
import org.training.core.book.service.BookService;
import org.training.core.model.BookModel;
import org.training.core.model.StudentModel;
import org.training.core.student.service.StudentService;
import org.training.facades.book.data.BookData;
import org.training.facades.student.StudentFacade;
import org.training.facades.student.data.StudentData;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

public class DefaultStudentFacade implements StudentFacade {

    @Resource(name="defaultStudentService")
    private StudentService studentService;

    @Resource(name = "studentConverter")
    private Converter<StudentModel, StudentData> studentConverter;

    @Override
    public List<StudentData> getAllStudent() {
        final List<StudentModel> studentModels = studentService.getAllStudent();
        if (CollectionUtils.isNotEmpty(studentModels)){
            return studentConverter.convertAll(studentModels);
        }
        return Collections.emptyList();
    }

    @Override
    public StudentData getStudentById(String id) {
        final StudentModel studentModel = studentService.getStudentById(id);
        if (null != studentModel)
        {
            return studentConverter.convert(studentModel);
        }
        return null;
    }

    @Override
    public StudentData getStudentByName(String fullName) {
        final StudentModel studentModel = studentService.getStudentById(fullName);
        if (null != studentModel)
        {
            return studentConverter.convert(studentModel);
        }
        return null;
    }
}
