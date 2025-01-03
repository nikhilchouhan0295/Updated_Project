package com.example.service;

import com.example.entity.BankAccounts;
import com.example.entity.RequestPamentCredential;

public interface PaymentService {
	void createUserAccout(BankAccounts user);

	boolean withdraAmmount(BankAccounts bk, double ammount);

	boolean paymentViaUpi(RequestPamentCredential rqdetails);

	boolean paymentViaAccNu(RequestPamentCredential rqdetails);

}