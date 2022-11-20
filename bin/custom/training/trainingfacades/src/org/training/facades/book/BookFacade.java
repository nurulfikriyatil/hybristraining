package org.training.facades.book;

import org.training.facades.book.data.BookData;

import java.util.List;

public interface BookFacade {

    List<BookData> getAllBook();

    BookData getBookId(final String id);

    BookData getBookByTitle(final String title);
}
