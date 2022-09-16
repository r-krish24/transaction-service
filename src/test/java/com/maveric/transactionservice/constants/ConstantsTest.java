package com.maveric.transactionservice.constants;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


import static com.maveric.transactionservice.util.Common.getCurrentDateTime;
import static org.junit.jupiter.api.Assertions.*;

class ConstantsTest {


    @Test
    void toTestgetCurrentDateTime() {
        LocalDateTime time = java.time.LocalDateTime.now();
        assertEquals(getCurrentDateTime().toLocalDate(),time.toLocalDate());
    }
}