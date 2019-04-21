package com.dao;

import org.springframework.stereotype.Repository;

import com.pojo.RedPacket;



@Repository
public interface RedPacketDao {
	
	public RedPacket getRedPacket(Long id);
	
	public int decreaseRedPacket(Long id, Integer version);
}
