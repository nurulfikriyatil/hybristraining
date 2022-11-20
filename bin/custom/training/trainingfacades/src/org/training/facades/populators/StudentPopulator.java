package org.training.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.collections.CollectionUtils;
import org.training.core.model.BookModel;
import org.training.core.model.StudentModel;
import org.training.facades.book.data.BookData;
import org.training.facades.student.data.StudentData;

import javax.annotation.Resource;

public class StudentPopulator extends StudentBasicPopulator {

    @Resource(name = "bookConverter")
    private Converter<BookModel, BookData> bookConverter;

    @Override
    public void populate(final StudentModel source, final StudentData target)
    {
        super.populate(source,target);
        if(CollectionUtils.isNotEmpty(source.getBook())){
            target.setBook(bookConverter.convertAll(source.getBook()));
        }
    }

}
