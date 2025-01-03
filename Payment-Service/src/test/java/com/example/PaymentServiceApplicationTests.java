package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.entity.BankAccounts;
import com.example.entity.RequestPamentCredential;
import com.example.exception.PaymentException;
import com.example.repository.BankRepository;
import com.example.service.PaymentService;

@SpringBootTest
class PaymentServiceApplicationTests {

    @Mock
    private BankRepository bankRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUserAccount() {
        BankAccounts user = new BankAccounts(1, "John Doe", 10000.0, "john@upi");
        when(bankRepository.save(user)).thenReturn(user);

        paymentService.createUserAccout(user);

        verify(bankRepository, times(1)).save(user);
    }

    @Test
    void testWithdrawAmount_Success() {
        BankAccounts bankAccount = new BankAccounts(1, "Jane Doe", 5000.0, "jane@upi");
        when(bankRepository.save(bankAccount)).thenReturn(bankAccount);

        boolean result = paymentService.withdraAmmount(bankAccount, 1000.0);

        assertTrue(result);
        assertEquals(4000.0, bankAccount.getBalance());
        verify(bankRepository, times(1)).save(bankAccount);
    }

    @Test
    void testWithdrawAmount_InsufficientFunds() {
        BankAccounts bankAccount = new BankAccounts(1, "Jane Doe", 500.0, "jane@upi");

        Exception exception = assertThrows(PaymentException.class, () -> paymentService.withdraAmmount(bankAccount, 1000.0));
        assertEquals("Insufficient Ammount in Acc", exception.getMessage());
        verify(bankRepository, never()).save(bankAccount);
    }

    @Test
    void testPaymentViaUpi_Success() {
        RequestPamentCredential rqdetails = new RequestPamentCredential(1, 1000.0, "jane@upi");
        BankAccounts bankAccount = new BankAccounts(1, "Jane Doe", 5000.0, "jane@upi");

        when(bankRepository.findByUpiId("jane@upi")).thenReturn(Optional.of(bankAccount));
        when(bankRepository.save(bankAccount)).thenReturn(bankAccount);

        boolean result = paymentService.paymentViaUpi(rqdetails);

        assertTrue(result);
        assertEquals(4000.0, bankAccount.getBalance());
        verify(bankRepository, times(1)).findByUpiId("jane@upi");
        verify(bankRepository, times(1)).save(bankAccount);
    }

    @Test
    void testPaymentViaUpi_AccountNotFound() {
        RequestPamentCredential rqdetails = new RequestPamentCredential(1, 1000.0, "unknown@upi");

        when(bankRepository.findByUpiId("unknown@upi")).thenReturn(Optional.empty());

        Exception exception = assertThrows(PaymentException.class, () -> paymentService.paymentViaUpi(rqdetails));
        assertEquals("Check The Account No", exception.getMessage());
        verify(bankRepository, times(1)).findByUpiId("unknown@upi");
    }

    @Test
    void testPaymentViaAccNu_Success() {
        RequestPamentCredential rqdetails = new RequestPamentCredential(1, 1000.0, null);
        BankAccounts bankAccount = new BankAccounts(1, "Jane Doe", 5000.0, "jane@upi");

        when(bankRepository.findById(1)).thenReturn(Optional.of(bankAccount));
        when(bankRepository.save(bankAccount)).thenReturn(bankAccount);

        boolean result = paymentService.paymentViaAccNu(rqdetails);

        assertTrue(result);
        assertEquals(4000.0, bankAccount.getBalance());
        verify(bankRepository, times(1)).findById(1);
        verify(bankRepository, times(1)).save(bankAccount);
    }

    @Test
    void testPaymentViaAccNu_AccountNotFound() {
        RequestPamentCredential rqdetails = new RequestPamentCredential(1, 1000.0, null);

        when(bankRepository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(PaymentException.class, () -> paymentService.paymentViaAccNu(rqdetails));
        assertEquals("Check The Account No", exception.getMessage());
        verify(bankRepository, times(1)).findById(1);
    }

    @Test
    void contextLoads() {
    }
}
