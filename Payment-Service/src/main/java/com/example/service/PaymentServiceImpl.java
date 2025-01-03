package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.BankAccounts;
import com.example.entity.RequestPamentCredential;
import com.example.exception.PaymentException;
import com.example.repository.BankRepository;


@Service
public class PaymentServiceImpl implements PaymentService{
	@Autowired
	private BankRepository bankRepository;
	@Override
	public void createUserAccout(BankAccounts user) {
	    bankRepository.save(user);
	}
 
	@Override
	public  boolean withdraAmmount(BankAccounts bk ,double ammount) {
		if(bk.getBalance()<ammount) throw new PaymentException("Insufficient Ammount in Acc");
		bk.setBalance(bk.getBalance() - ammount);
		if( bankRepository.save(bk) != null)
			return true;
		else return false;
	}

	@Override
	public boolean paymentViaUpi(RequestPamentCredential rqdetails) {
		// TODO Auto-generated method stub
		BankAccounts bankUser = bankRepository.findByUpiId(rqdetails.getUpiId()).orElseThrow(() -> new PaymentException("Check The Account No"));
		if(withdraAmmount(bankUser,rqdetails.getWithdrawAmount())) {
			return true;
			
		}
		return false;
	}

	@Override
	public boolean paymentViaAccNu(RequestPamentCredential rqdetails) {
		// TODO Auto-generated method stub
		BankAccounts bankUser = bankRepository.findById(rqdetails.getAccountNumber()).orElseThrow(() -> new PaymentException("Check The Account No"));
		if(withdraAmmount(bankUser, rqdetails.getWithdrawAmount())) {
			return true;
		}
		return false;
	}
	

	} 