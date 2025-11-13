package com.cipfpmislata.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {
    BookMapper mapper = BookMapper.getInstance();
    @Test
    void fromBookEntityToBook() {
    }

    @Test
    void fromBookDtoToBook() {
    }

    @Test
    void fromBookToBookDto() {
    }

    @Test
    void fromBookToBookEntity() {
    }
    @Test
    @DisplayName("Mapping a null book returns null")
    void allMethodsReturnNull_WhenGivenNullBooks(){
        assertAll(
                ()-> assertNull(mapper.fromBookDtoToBook(null)),
                ()-> assertNull(mapper.fromBookEntityToBook(null)),
                ()-> assertNull(mapper.fromBookToBookDto(null)),
                ()-> assertNull(mapper.fromBookToBookEntity(null))
        );
    }
}