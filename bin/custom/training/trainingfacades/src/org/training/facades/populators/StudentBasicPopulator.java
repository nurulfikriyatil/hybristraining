package org.training.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.training.core.model.StudentModel;
import org.training.facades.student.data.StudentData;

public class StudentBasicPopulator implements Populator<StudentModel, StudentData> {


    @Override
    public void populate(StudentModel source, StudentData target) throws ConversionException {
        target.setId(source.getId());
        target.setFullName(source.getFullName());
        target.setPhonNumber(source.getPhonNumber());
    }
}
