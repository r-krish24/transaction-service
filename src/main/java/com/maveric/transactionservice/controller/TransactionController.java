package com.maveric.transactionservice.controller;
import com.maveric.transactionservice.dto.BalanceDto;
import com.maveric.transactionservice.dto.PairClassDto;
import com.maveric.transactionservice.dto.TransactionDto;
import com.maveric.transactionservice.feignconsumer.BalanceServiceConsumer;
import com.maveric.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    BalanceServiceConsumer balanceServiceConsumer;


    @GetMapping("accounts/{accountId}/transaction")
    public ResponseEntity<List<TransactionDto>> getTransactions(@PathVariable String accountId,@RequestParam(defaultValue = "0") Integer page,
                                                          @RequestParam(defaultValue = "10") Integer pageSize)  {
        List<TransactionDto> transactionDtoResponse = transactionService.getTransactions(page,pageSize);
        return new ResponseEntity<>(transactionDtoResponse, HttpStatus.OK);
    }

    @GetMapping("accounts/{accountId}/transactions")
    public ResponseEntity<List<TransactionDto>> getTransactionsByAccountId(@PathVariable String accountId,@RequestParam(defaultValue = "0") Integer page,
                                                                           @RequestParam(defaultValue = "5") Integer pageSize)  {
        List<TransactionDto> transactionDtoResponse = transactionService.getTransactionsByAccountId(page,pageSize,accountId);
        return new ResponseEntity<>(transactionDtoResponse, HttpStatus.OK);
    }

    @PostMapping("accounts/{accountId}/transactions")
    public ResponseEntity<TransactionDto> createTransaction(@PathVariable String accountId, @Valid @RequestBody TransactionDto transactionDto) throws NullPointerException{
        BalanceDto balanceDto = new BalanceDto();
        try {
              ResponseEntity<BalanceDto> responseEntity = balanceServiceConsumer.getBalances(accountId);
                 balanceDto = responseEntity.getBody();
            }
            catch(Exception ex)
            {
                System.out.println("Error with Balance service-"+ex.getMessage());
            }
        PairClassDto createResponse = transactionService.createTransaction(accountId,transactionDto,balanceDto);
        try {
             balanceServiceConsumer.updateBalance(accountId,balanceDto.get_id(),createResponse.getBalanceDto());
        }
        catch(NullPointerException ex)
        {
            System.out.println("Error with Balance service, failed to update the balance-"+ex.getMessage());
        }
        return new ResponseEntity<>(createResponse.getTransactionDto(), HttpStatus.CREATED);
    }

    @GetMapping("accounts/{accountId}/transactions/{transactionId}")
    public ResponseEntity<TransactionDto> getTransactionDetails(@PathVariable String accountId,@PathVariable String transactionId) {
        TransactionDto transactionDtoResponse = transactionService.getTransactionById(transactionId);
        return new ResponseEntity<>(transactionDtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("accounts/{accountId}/transactions/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable String accountId,@PathVariable String transactionId) {
        String result = transactionService.deleteTransaction(transactionId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("accounts/{accountId}/transactions")
    public ResponseEntity<String> deleteTransactionByAccountId(@PathVariable String accountId)
    {
        String result = transactionService.deleteTransactionByAccountId(accountId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
