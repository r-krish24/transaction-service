package com.maveric.transactionservice.controller;

import com.maveric.transactionservice.dto.BalanceDto;
import com.maveric.transactionservice.dto.PairClassDto;
import com.maveric.transactionservice.feignconsumer.BalanceServiceConsumer;
import com.maveric.transactionservice.service.TransactionService;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.maveric.transactionservice.TransactionServiceApplicationTests.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes=TransactionController.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(TransactionController.class)
@Tag("Integeration Tests")
public class TransactionControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    BalanceServiceConsumer balanceServiceConsumer;


    @Test
    public void shouldGetStatus200WhenRequestMadeTogetTransactions() throws Exception
    {
        mock.perform(get(apiV1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldGetStatus200WhenRequestMadeTogetTransactionsByAccountId() throws Exception
    {
        mock.perform(get("/api/v1/accounts/1/transaction")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /*@Test
    public void shouldGetStatus201WhenRequestMadeToCreateTransaction() throws Exception
    {
        ResponseEntity<BalanceDto> responseEntity = new ResponseEntity<>(getBalanceDto(), HttpStatus.OK);
        when(balanceServiceConsumer.getBalances(any(String.class))).thenReturn(responseEntity);

        PairClassDto createResponse = new PairClassDto(getTransactionDto(),getBalanceDto());
        when(transactionService.createTransaction("1234",getTransactionDto(),getBalanceDto())).thenReturn(createResponse);

        mock.perform(post(apiV1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getTransactionDto()))
                )
                .andExpect(status().isCreated())
                .andDo(print());
    } */

    @Test
    public void shouldGetStatus200WhenRequestMadeToGetTransactionDetails() throws Exception
    {
        mock.perform(get(apiV1+"/transactionId1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldGetStatus200WhenRequestMadeToDeleteTransaction() throws Exception
    {
        mock.perform(delete(apiV1+"/transactionId1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldGetStatus200WhenRequestMadeToDeleteTransactionByAccountId() throws Exception
    {
        mock.perform(delete(apiV1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }


}
