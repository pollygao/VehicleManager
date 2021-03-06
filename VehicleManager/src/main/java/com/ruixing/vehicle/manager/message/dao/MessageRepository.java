package com.ruixing.vehicle.manager.message.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ruixing.vehicle.manager.domain.MessageInfo;

public interface MessageRepository extends JpaRepository<MessageInfo, Integer> {

	public MessageInfo findById(Integer id);

	@Query("select new MessageInfo(c.id as id,c.recordTime as recordTime, c.messageState as messageState,c.carNumber as carNumber,c.placeName as placeName,c.goodType as goodType,c.sendStatus as sendStatus, c.successList as successList, c.failList as failList, c.runType as runType) from MessageInfo c where c.messageState=?1 ")
	public List<MessageInfo> findAllByMessageState(Boolean messageState);

	@Query("select new MessageInfo(c.id as id,c.recordTime as recordTime, c.messageState as messageState,c.carNumber as carNumber,c.placeName as placeName,c.goodType as goodType,c.sendStatus as sendStatus, c.successList as successList, c.failList as failList, c.runType as runType) from MessageInfo c where c.messageState=?1 ")
	public Page<MessageInfo> findViewByMessageState(Boolean messageState, Pageable pageable);

	/**
	 * 按时间范围分页查询
	 * 
	 * @param startTime
	 * @param endTime
	 * @param pageable
	 * @return
	 */
	@Query("select new MessageInfo(c.id as id,c.recordTime as recordTime, c.messageState as messageState,c.carNumber as carNumber,c.placeName as placeName,c.goodType as goodType,c.sendStatus as sendStatus, c.successList as successList, c.failList as failList, c.runType as runType) from MessageInfo c where c.recordTime>?1 and c.recordTime<?2  and c.messageState=?3")
	public Page<MessageInfo> findByRecordTime(Date startTime, Date endTime, Boolean messageState, Pageable pageable);

	/**
	 * 按短信内容模糊匹配分页查询
	 * 
	 * @param messageContent
	 * @param pageable
	 * @return
	 */
//	Page<MessageInfo> findByMessageContentContaining(String messageContent, Pageable pageable);

	List<MessageInfo> findByRecordTimeAfter(Date startTime);

	@Query("select new MessageInfo(c.id as id,c.recordTime as recordTime, c.messageState as messageState,c.carNumber as carNumber,c.placeName as placeName,c.goodType as goodType,c.sendStatus as sendStatus, c.successList as successList, c.failList as failList, c.runType as runType) from MessageInfo c where sendStatus = 0 ")
	List<MessageInfo> findeBySendStatus();
	
	/**
	 * 分页查询
	 * @param spc
	 * @param pageable
	 * @return
	 */
	Page<MessageInfo> findAll(Specification<MessageInfo> spc, Pageable pageable);
}
