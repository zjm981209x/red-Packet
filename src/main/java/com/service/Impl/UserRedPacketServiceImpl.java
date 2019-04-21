package com.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.RedPacketDao;
import com.dao.UserRedPacketDao;
import com.pojo.RedPacket;
import com.pojo.UserRedPacket;
import com.service.UserRedPacketService;



@Service
public class UserRedPacketServiceImpl implements UserRedPacketService{
	
	@Autowired 
	private UserRedPacketDao userRedPacketDao = null;
	
	@Autowired
	private RedPacketDao redPacketDao = null;
	
	private static final int FAIL = 0;
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public int grapRedPacket(Long redPacketId, Long userId) {
		for(int i=0;i<3;i++){
		RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
		if(redPacket.getStock() > 0){
			int update = redPacketDao.decreaseRedPacket(redPacketId, redPacket.getVersion());
			if(update == 0){
				continue;
			}
			UserRedPacket userRedPacket = new UserRedPacket();
			userRedPacket.setRedPacketId(redPacketId);
			userRedPacket.setUserId(userId);
			userRedPacket.setAmount(redPacket.getUnitAmount());
			userRedPacket.setNote("抢红包" + redPacketId);
			int result = userRedPacketDao.grapRedPacket(userRedPacket);
			return result;
		}else{
			return FAIL;
		}
	}
		return FAIL;
	}

}
