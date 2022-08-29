package com.maveric.transactionservice.service;
import com.maveric.transactionservice.constants.Type;
import com.maveric.transactionservice.dto.TransactionDto;
import com.maveric.transactionservice.mapper.TransactionMapper;
import com.maveric.transactionservice.model.Transaction;
import com.maveric.transactionservice.repository.TransactionRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.List;
import static com.maveric.transactionservice.TransactionServiceApplicationTests.getTransaction;
import static com.maveric.transactionservice.TransactionServiceApplicationTests.getTransactionDto;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionServiceImpl service;


    @Mock
    private TransactionRepository repository;

    @Mock
    private TransactionMapper mapper;

    @Test
    public void testCreateTransaction() throws Exception{
        when(repository.save(any(Transaction.class))).thenReturn(getTransaction());

        TransactionDto transactionDto = service.createTransaction(getTransactionDto());
        System.out.println("Test->"+transactionDto.getAccountId());
        assertSame(transactionDto.getAccountId(), getTransaction().getAccountId());
    }

    @Test
    public void testGetTransactions() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Transaction("1","1234", Type.CREDIT,2000,null),
                new Transaction("2","1235", Type.DEBIT,1000,null)));
        List<TransactionDto> transactions = service.getTransactions(1,10);

        assertEquals("1234", transactions.get(0).getAccountId());
        assertEquals("1235", transactions.get(1).getAccountId());
    }



}
