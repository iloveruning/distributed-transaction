package com.iflytek.bytetcc.sample.provider.controller;

import com.iflytek.bytetcc.sample.provider.service.AccountService;
import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



/**
 * ByteTCC��������Ϊ: ʹ��SpringCloudʱ, ֱ�Ӷ����ṩ�����ControllerӦ����ȷ�滮��������ͨ������TCC����.<br />
 * ���, 0.4.x�汾ǿ�ƶ����ṩTCC�����Controller�����@Compensableע��(��û��ʵ��ҵ��, Ҳ���Բ���ָ��confirmableKey��cancellableKey).<br />
 * ������@Compensableע��, ��ByteTCC���䵱����ͨ����Դ�, ������Consumer�˴���������������. ������������TCC����, �򽫿����µ�TCCȫ������.
 */
@Compensable(interfaceClass = AccountService.class, confirmableKey = "accountServiceConfirm", cancellableKey = "accountServiceCancel")
@RestController
public class AccountController implements AccountService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@ResponseBody
	@RequestMapping(value = "/increase", method = RequestMethod.POST)
	@Transactional
	@Override
	public void increaseAmount(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount) {
		int value = this.jdbcTemplate.update("update tb_account_one set frozen = frozen + ? where acct_id = ?", amount, acctId);
		if (value != 1) {
			throw new IllegalStateException("ERROR!");
		}
		System.out.printf("exec increase: acct= %s, amount= %7.2f%n", acctId, amount);
	}

	@ResponseBody
	@RequestMapping(value = "/decrease", method = RequestMethod.POST)
	@Transactional
	@Override
	public void decreaseAmount(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount) {
		int value = this.jdbcTemplate.update(
				"update tb_account_one set amount = amount - ?, frozen = frozen + ? where acct_id = ?", amount, amount, acctId);
		if (value != 1) {
			throw new IllegalStateException("ERROR!");
		}
		System.out.printf("exec decrease: acct= %s, amount= %7.2f%n", acctId, amount);

		// throw new IllegalStateException("error");
	}

}
