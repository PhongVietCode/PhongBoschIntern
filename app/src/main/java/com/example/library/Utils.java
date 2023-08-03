package com.example.library;

import java.util.ArrayList;
/*
* Util is a database
* */

public class Utils {
    private static Utils instance;
    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyBook;
    private static ArrayList<Book> wanttoBook;
    private static ArrayList<Book> currentlyBook;
    private static ArrayList<Book> favorieBook;
    // if another thread call this method, it will be called one by one.
    public static synchronized Utils getInstance() {
        if(instance == null){
            instance = new Utils();
        }
        return instance;
    }

    private Utils() {
        if(allBooks == null){
            allBooks = new ArrayList<>();
            initData();
        }

        if(alreadyBook == null){
            alreadyBook = new ArrayList<>();
        }
        if(wanttoBook == null){
            wanttoBook  = new ArrayList<>();
        }
        if(currentlyBook == null){
            currentlyBook = new ArrayList<>();
        }
        if(favorieBook== null){
            favorieBook = new ArrayList<>();
        }
    }

    private void initData() {
        //todo: init data
        allBooks.add(new Book(1,"Homes 1","KingD",1020,"https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg","Long time age","try this for free"));
        allBooks.add(new Book(2,"Aka Tomb","KingD",1010,"https://img.freepik.com/photos-premium/arbre-vie-par-personne_901275-2749.jpg","Long time age","try this for free"));
        allBooks.add(new Book(3,"Auther Going","KingD",132,"https://st3.depositphotos.com/11124864/14059/i/450/depositphotos_140594620-stock-photo-asian-girls-jump-from-a.jpg","Long time age","try this for free"));
        allBooks.add(new Book(4,"Jk Rail","KingD",321,"https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg","Long time age","try this for free"));
        allBooks.add(new Book(5,"Hackot","KingD",842,"https://previews.123rf.com/images/crazymedia/crazymedia1601/crazymedia160100006/51392867-victory-at-the-peak-of-happiness.jpg","Long time age","try this for free"));
        allBooks.add(new Book(6,"Alaos locase","KingD",371,"https://previews.123rf.com/images/wrangel/wrangel1605/wrangel160500063/56913283-harbor-seal-phoca-vitulina-also-known-as-the-common-seal-wild-life-animal.jpg","Long time age","try this for free"));
        allBooks.add(new Book(7,"Trick me better solo iff you can't","KingD",1231,"https://previews.123rf.com/images/wrangel/wrangel1609/wrangel160900335/63666586-harbor-seal-phoca-vitulina-also-known-as-the-common-seal-wildlife-animal.jpg","Long time age","try this for free"));
        allBooks.add(new Book(8,"On Top Queen","KingD",4127,"https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg","Long time age","try this for free"));

    }

    public static ArrayList<Book> getAllBooks()     {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyBook() {
        return alreadyBook;
    }

    public static ArrayList<Book> getWanttoBook() {
        return wanttoBook;
    }

    public static ArrayList<Book> getCurrentlyBook() {
        return currentlyBook;
    }

    public static ArrayList<Book> getFavorieBook() {
        return favorieBook;
    }

    public Book getBookbyID(int id){
        for (Book b:
             allBooks) {
            if(b.getId() == id) return b;
        }
        return  null;
    }
    public boolean addtoAlreadyBook(Book book){
        return alreadyBook.add(book);
    }
    public boolean addtoCurrentBook(Book book){
        return currentlyBook.add(book);
    } public boolean addtoFavoriteBook(Book book){
        return favorieBook.add(book);
    } public boolean addtoWanttoBook(Book book){
        return wanttoBook.add(book);
    }
    public boolean rmvFromalready(Book book){
        return alreadyBook.remove(book);
    }
}
