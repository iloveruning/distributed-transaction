package com.iflytek.bytetcc.sample.consumer.controller;

import com.iflytek.bytetcc.sample.consumer.client.AccountClient;
import com.iflytek.bytetcc.sample.consumer.mapper.AccountMapper;
import com.iflytek.bytetcc.sample.consumer.service.AccountService;
import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Compensable(interfaceClass = AccountService.class, confirmableKey = "transferServiceConfirm", cancellableKey = "transferServiceCancel")
@RestController
public class TransferController {
	@Autowired
	private AccountMapper transferDao;

	@Autowired
	private AccountClient acctService;

	@ResponseBody
	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	@Transactional
	public void transfer(@RequestParam String sourceAcctId, @RequestParam String targetAcctId, @RequestParam double amount) {
		this.acctService.decreaseAmount(sourceAcctId, amount);
		this.increaseAmount(targetAcctId, amount);
	}

	private void increaseAmount(String acctId, double amount) {
		int value = this.transferDao.increaseAmount(acctId, amount);
		if (value != 1) {
			throw new IllegalStateException("ERROR!");
		}
		System.out.printf("exec increase: acct= %s, amount= %7.2f%n", acctId, amount);
	}

	/**
	 * ������������ʾ�� TransferController�п��Դ���@Compensable.interfaceClassָ���ӿ���δ����ķ���. <br />
	 * ��Ҫע�����, δ��interfaceClass�ӿ��ж���ķ���, �����ڿɲ�����ҵ�����, ����TCCȫ������. <br />
	 */
	@ResponseBody
	@RequestMapping(value = "/getAmount", method = RequestMethod.POST)
	public Double getAmount(@RequestParam String targetAcctId) {
		return this.transferDao.getAcctAmount(targetAcctId);
	}

}
