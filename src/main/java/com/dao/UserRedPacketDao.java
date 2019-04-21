package com.dao;

import org.springframework.stereotype.Repository;

import com.pojo.UserRedPacket;



@Repository
public interface UserRedPacketDao {

		public int grapRedPacket(UserRedPacket userPacket);
}
