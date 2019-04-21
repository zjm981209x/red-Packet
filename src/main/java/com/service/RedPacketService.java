package com.service;

import com.pojo.RedPacket;



public interface RedPacketService {
	public RedPacket getRedPacket(Long id);
	public int decreaseRedPacket(Long id, Integer version);
}