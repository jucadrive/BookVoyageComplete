package com.kdt.BookVoyage.Book;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookTest {

    @Autowired
    private BookService bookService;

    @Test
    public void getBookListTest() {

        AladinItemListReq aladinItemListReq = new AladinItemListReq();
        aladinItemListReq.setQueryType("BestSeller");

        var result = bookService.getBookList(aladinItemListReq);
        System.out.println(result);
    }

    @Test
    public void aladinGetBookDetailTest() {
        AladinBookDetailReq aladinBookDetailReq = new AladinBookDetailReq();
        aladinBookDetailReq.setItemId("9791169811378");

        var result = bookService.getDetails(aladinBookDetailReq);
        System.out.println(result);
    }

    @Test
    @Transactional
    public void getBookDetails() {
        var result = bookService.getBookDetails("9791198173898");
        System.out.println(result);
    }


}
