package kr.or.reservation.jdbc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.reservation.config.RootApplicationContextConfig;
import kr.or.reservation.dao.ReservationDao;
import kr.or.reservation.dao.ReservationInfoDao;
import kr.or.reservation.domain.ReservationInfo;
import kr.or.reservation.dto.ReservationDTO;
import kr.or.reservation.service.ReservationInfoService;
import kr.or.reservation.service.ReservationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
//@Transactional
public class ReservationInfoTest {

	@Autowired
	ReservationInfoDao dao ;
	
	@Autowired
	ReservationInfoService service;
	
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Test
	public void insert() {
		ReservationInfo reservationInfo 
		= new ReservationInfo(2,1,3,4,2,"장철운","010-4156-0938","email",new Timestamp(System.currentTimeMillis()),0,new Timestamp(System.currentTimeMillis()),null);
		service.insert(reservationInfo);
		
	}
	

}
