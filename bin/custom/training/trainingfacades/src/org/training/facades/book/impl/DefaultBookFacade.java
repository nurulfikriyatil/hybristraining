package org.training.facades.book.impl;

import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.collections.CollectionUtils;
import org.training.core.book.service.BookService;
import org.training.core.model.BookModel;
import org.training.facades.book.BookFacade;
import org.training.facades.book.data.BookData;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

public class DefaultBookFacade implements BookFacade {

    @Resource(name="defaultBookService")
    private BookService bookService;

    @Resource(name = "bookConverter")
    private Converter<BookModel, BookData> bookConverter;

    @Override
    public List<BookData> getAllBook()
    {
        final List<BookModel> bookModels = bookService.getAllBook();
        if (CollectionUtils.isNotEmpty(bookModels)){
            return bookConverter.convertAll(bookModels);
        }
        return Collections.emptyList();
    }

    @Override
    public BookData getBookId(String id) {
        final BookModel bookModel = bookService.getBookById(id);
        if (null != bookModel)
        {
            return bookConverter.convert(bookModel);
        }
        return null;
    }

    @Override
    public BookData getBookByTitle(String title) {
        final BookModel bookModel = bookService.getBookByTitle(title);
        if (null != bookModel){
            return bookConverter.convert(bookModel);
        }
        return null;
    }
}
