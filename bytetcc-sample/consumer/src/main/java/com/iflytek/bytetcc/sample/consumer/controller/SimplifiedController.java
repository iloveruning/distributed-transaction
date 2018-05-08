//package com.iflytek.bytetcc.sample.consumer.controller;
//
//import com.iflytek.bytetcc.sample.consumer.client.AccountClient;
//import com.iflytek.bytetcc.sample.consumer.mapper.AccountMapper;
//
//import org.bytesoft.compensable.Compensable;
//import org.bytesoft.compensable.CompensableCancel;
//import org.bytesoft.compensable.CompensableConfirm;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//
//
//@Compensable(interfaceClass = AccountClient.class, simplified = true)
//@RestController
//public class SimplifiedController {
//	@Autowired
//	private AccountMapper accountMapper;
//
//	@Autowired
//	private AccountClient accountClient;
//
//	@ResponseBody
//	@RequestMapping(value = "/simplified/transfer", method = RequestMethod.POST)
//	@Transactional
//	public void transfer(@RequestParam String sourceAcctId, @RequestParam String targetAcctId, @RequestParam double amount) {
//		this.accountClient.decreaseAmount(sourceAcctId, amount);
//
//		int value = this.accountMapper.increaseAmount(targetAcctId, amount);
//		if (value != 1) {
//			throw new IllegalStateException("ERROR!");
//		}
//		System.out.printf("exec increase: acct= %s, amount= %7.2f%n", targetAcctId, amount);
//		// throw new IllegalStateException("rollback!");
//	}
//
//
//	@CompensableConfirm
//	@Transactional
//	public void confirmTransfer(String sourceAcctId, String targetAcctId, double amount) {
//		int value = this.accountMapper.confirmIncrease(targetAcctId, amount);
//		if (value != 1) {
//			throw new IllegalStateException("ERROR!");
//		}
//		System.out.printf("done increase: acct= %s, amount= %7.2f%n", targetAcctId, amount);
//	}
//
//	@CompensableCancel
//	@Transactional
//	public void cancelTransfer(String sourceAcctId, String targetAcctId, double amount) {
//		int value = this.accountMapper.cancelIncrease(targetAcctId, amount);
//		if (value != 1) {
//			throw new IllegalStateException("ERROR!");
//		}
//		System.out.printf("exec decrease: acct= %s, amount= %7.2f%n", targetAcctId, amount);
//	}
//
//}
