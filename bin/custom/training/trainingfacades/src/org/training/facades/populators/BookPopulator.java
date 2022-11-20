package org.training.facades.populators;

import de.hybris.platform.catalog.jalo.CatalogUnawareMedia;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.training.core.model.BookModel;
import org.training.core.model.StudentModel;
import org.training.facades.book.data.BookData;
import org.training.facades.student.data.StudentData;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

public class BookPopulator implements Populator<BookModel, BookData> {

    @Resource(name = "studentBasicConverter")
    private Converter<StudentModel, StudentData> studentBasicConverter;

    @Override
    public void populate(final BookModel source, final BookData target) throws ConversionException
    {
        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setGenre(source.getGenre());
        final CatalogUnawareMediaModel picture = source.getImage();
        if (null != picture)
        {
            target.setImage(picture.getURL());
        }
        if (null != source.getStudent()){
            target.setStudent(studentBasicConverter.convert(source.getStudent()));
        }
    }
}
