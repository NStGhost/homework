package com.shadow.homework22.TestHomeWork22.Interface;

import com.shadow.homework22.TestHomeWork22.Entity.Book;

public interface IBookDAO {

    public Book findBookById(int id);

    public void addBook(Book book);
    public void deleteBook(Book book);

}
