package com.bracelet.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.bracelet.dto.FenceDto;
import com.bracelet.dto.HttpBaseDto;
import com.bracelet.dto.SosDto;
import com.bracelet.entity.Fence;
import com.bracelet.entity.FenceStatus;
import com.bracelet.entity.OddShape;
import com.bracelet.entity.UserInfo;
import com.bracelet.service.IFenceService;
import com.bracelet.service.IFencelogService;
import com.bracelet.service.IPushlogService;
import com.bracelet.service.ISmslogService;
import com.bracelet.service.ITokenInfoService;
import com.bracelet.service.IUserInfoService;
import com.bracelet.util.PushUtil;
import com.bracelet.util.SmsUtil;
import com.bracelet.util.Utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class FenceServiceImpl implements IFenceService {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	IUserInfoService userInfoService;
	@Autowired
	IFencelogService fencelogService;
	@Autowired
	ISmslogService smslogService;
	@Autowired
	IPushlogService pushlogService;
	@Autowired
	ITokenInfoService tokenInfoService;

	@Override
	public Fence getOne(Long user_id) {
		String sql = "select * from fence where user_id=? LIMIT 1";
		List<Fence> list = jdbcTemplate.query(sql, new Object[] { user_id },
				new BeanPropertyRowMapper<Fence>(Fence.class));

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			logger.info("get return null.user_id:" + user_id);
		}
		return null;
	}

	@Override
	public FenceStatus getFenceStatus(Long user_id) {
		String sql = "select * from fence_status where user_id=? LIMIT 1";
		List<FenceStatus> list = jdbcTemplate.query(sql, new Object[] { user_id },
				new BeanPropertyRowMapper<FenceStatus>(FenceStatus.class));

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			logger.info("get return null.user_id:" + user_id);
		}
		return null;
	}

	@Override
	public boolean insert(Long user_id, String lat, String lng, Integer radius, String name) {
		/*
		 * Fence fence = this.getOne(user_id); if (fence != null) {
		 * logger.warn("用户[" + user_id + "]已经设置过电子围栏[" + lat + "][" + lng + "]["
		 * + radius + "]!"); return false; }
		 */
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"insert into fence (user_id, lat, lng, radius, createtime, updatetime,name) values (?,?,?,?,?,?,?)",
				new Object[] { user_id, lat, lng, radius, now, now, name }, new int[] { Types.INTEGER, Types.VARCHAR,
						Types.VARCHAR, Types.INTEGER, Types.TIMESTAMP, Types.TIMESTAMP, Types.VARCHAR });
		return i == 1;
	}

	@Override
	public boolean insertFenceStaus(Long user_id) {
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"insert into fence_status (user_id, status,createtime, updatetime) values (?,?,?,?)",
				new Object[] { user_id, 0, now, now },
				new int[] { Types.INTEGER, Types.INTEGER, Types.TIMESTAMP, Types.TIMESTAMP });
		return i == 1;
	}

	@Override
	public boolean update(Long id, Long user_id, String lat, String lng, Integer radius,String name) {
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"update fence set lat = ?, lng = ?, radius = ?, updatetime = ?, name = ? where id = ? and user_id = ?",
				new Object[] { lat, lng, radius, now, name,id, user_id }, new int[] { Types.VARCHAR, Types.VARCHAR,
						Types.INTEGER, Types.TIMESTAMP,Types.VARCHAR, Types.INTEGER, Types.INTEGER });
		return i == 1;
	}

	@Override
	public boolean delete(Long id, Long user_id) {
		int i = jdbcTemplate.update("delete from fence where id = ? and user_id = ?", new Object[] { id, user_id },
				new int[] { Types.INTEGER, Types.INTEGER });
		return i == 1;
	}

	@Override
	public void checkFenceArea(Long user_id, String imei, String lat, String lng, long time) {
		FenceStatus fenceStatus = this.getFenceStatus(user_id);
		if (fenceStatus == null) {
			this.insertFenceStaus(user_id);
			fenceStatus = this.getFenceStatus(user_id);
		}
		List<Fence> roundList = getRoundFenceList(user_id);
		boolean needReport = false;
		int newStatus = 0;
		int yuanYiXing = 0;
		String name = "";
		// 对比圆
		// if (fence != null && !StringUtils.isEmpty(fence.getLng()) &&
		// !StringUtils.isEmpty(fence.getLat())) {
		if (roundList!=null&&roundList.size() > 0) {
			for (Fence fence : roundList) {
				double distance = Utils.calcDistance(Double.parseDouble(lng), Double.parseDouble(lat),
						Double.parseDouble(fence.getLng()), Double.parseDouble(fence.getLat()));
				if (distance < fence.getRadius()) {
					// 电子围栏内
					if (fenceStatus.getStatus() != 1) {
						newStatus = 1;
						needReport = true;
						yuanYiXing = 1;
					}
				} else {
					// 电子围栏外
					if (fenceStatus.getStatus() != 2) {
						newStatus = 2;
						needReport = true;
						yuanYiXing = 1;
					}
				}
				if (needReport) {
					name = fence.getName();
					break;
				}
			}

			// 对比圆形电子围栏
			logger.info("圆形电子围栏检查=" + imei + "---checkFenceArea=newStatus=" + newStatus + ",needReport=" + needReport
					+ ",yuanYiXing" + yuanYiXing);
		}

		if (needReport == false) {
			logger.info(imei + "--圆形电子围栏不需要报道-开始检查异形电子围栏");
			List<OddShape> list = this.getOddShapeList(user_id);
			if (list != null) {
				for (OddShape ood : list) {
					String pointt = ood.getPoint();
					if (!StringUtils.isEmpty(pointt)) {
						Point2D.Double point = new Point2D.Double(Double.parseDouble(lng), Double.parseDouble(lat));
						// 经度lng,纬度lat..
						List<Point2D.Double> pts = new ArrayList<Point2D.Double>();
						String[] locations = pointt.split(",");
						for (int g = 0; g < locations.length; g++) {
							if (g % 2 == 0) {
								pts.add(new Point2D.Double(Double.parseDouble(locations[g]),
										Double.parseDouble(locations[g + 1])));
							}
						}

						if (Utils.IsPtInPoly(point, pts)) {
							System.out.println("点在多边形内"); // 在电子围栏内
							if (fenceStatus.getStatus() != 1) {
								newStatus = 1;
								needReport = true;
								yuanYiXing = 2;
							}
						} else {
							System.out.println("点在多边形外"); // 离开了电子围栏
							if (fenceStatus.getStatus() != 2) {
								newStatus = 2;
								needReport = true;
								yuanYiXing = 2;
							}
						}
						if (needReport) {
							name = ood.getName();
							break;
						}
					}
				}
			}
		}
		logger.info("异形电子围栏检查=" + imei + "---checkFenceArea=newStatus=" + newStatus + ",needReport=" + needReport
				+ ",yuanYiXing=" + yuanYiXing);

		if (needReport) {
			// 更新status
			this.updateFenceStatus(fenceStatus.getId(), newStatus);
			// 报警
			UserInfo userInfo = this.userInfoService.getUserInfoById(user_id);
			if (userInfo != null && !StringUtils.isEmpty(userInfo.getUsername())) {
				String type = newStatus == 1 ? "进入" : "离开";
				String shapeType = yuanYiXing == 1 ? "圆形" : "异形";
				String timeStr = Utils.format14DateString(time);
			
				String title = "电子围栏";
				FenceDto fenDto = new FenceDto();
				fenDto.setLat(lat);
				fenDto.setLng(lng);
				String notifyContent = imei+"设备于" +timeStr + type + "名字叫" + name + "的" + shapeType + "电子围栏";
				fenDto.setTimestamp(time);
				fenDto.setContent(notifyContent);
				String content = JSON.toJSONString(fenDto);
				String target = tokenInfoService.getTokenByUserId(user_id);
				PushUtil.push(target, title, content, notifyContent);
				this.pushlogService.insert(user_id, imei, 0, target, title, content);
				
				SendSmsResponse result = SmsUtil.sendSms("电子围栏报警", userInfo.getUsername(), "SMS_99420011",
						"{\"type\":\"" + type + "\", \"time\":\"" + timeStr + "\"}");

				smslogService.insert("电子围栏报警", userInfo.getUsername(), "SMS_99420011",
						"{\"type\":\"" + type + "\", \"time\":\"" + timeStr + "\"}", 0, result.getMessage());

				// save fencelog
				/*if (yuanYiXing == 1) {
					this.fencelogService.insert(user_id, imei, fence.getLat(), fence.getLng(), fence.getRadius(), lat,
							lng, newStatus, "设备" + type + shapeType + "电子围栏", new Timestamp(time));
				} else if (yuanYiXing == 2) {*/
					this.fencelogService.insert(user_id, imei, "0", "0", 0, lat, lng, newStatus,
							"设备" + type + "名字叫" + name + "的" + shapeType + "电子围栏", new Timestamp(time));
				//}
			}
		}

	}

	@Override
	public boolean updateStatus(Long id, Integer status) {
		int i = jdbcTemplate.update("update fence set status = ? where id = ?", new Object[] { status, id },
				new int[] { Types.INTEGER, Types.INTEGER });
		return i == 1;
	}

	@Override
	public boolean insertOddShape(Long user_id, String point, String name, String type) {
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"insert into odd_shape (user_id, point, createtime, updatetime,name,type) values (?,?,?,?,?,?)",
				new Object[] { user_id, point, now, now, name, type }, new int[] { Types.INTEGER, Types.VARCHAR,
						Types.TIMESTAMP, Types.TIMESTAMP, Types.VARCHAR, Types.INTEGER });
		return i == 1;
	}

	@Override
	public OddShape getOddShape(Long user_id) {
		String sql = "select * from odd_shape where user_id=? LIMIT 1";
		List<OddShape> list = jdbcTemplate.query(sql, new Object[] { user_id },
				new BeanPropertyRowMapper<OddShape>(OddShape.class));

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			logger.info("get odd_shape return null.user_id:" + user_id);
		}
		return null;
	}

	@Override
	public boolean deleteOddShape(Long id, Long user_id) {
		int i = jdbcTemplate.update("delete from odd_shape where id = ? and user_id = ?", new Object[] { id, user_id },
				new int[] { Types.INTEGER, Types.INTEGER });
		return i == 1;
	}

	@Override
	public boolean updateOddShapefence(Long id, Long user_id, String point, String name) {
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"update odd_shape set point = ?, updatetime = ? ,name = ? where id = ? and user_id = ?",
				new Object[] { point, now, name, id, user_id },
				new int[] { Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR, Types.INTEGER, Types.INTEGER });
		return i == 1;
	}

	@Override
	public List<OddShape> getOddShapeList(Long user_id) {
		String sql = "select * from odd_shape where user_id=?";
		List<OddShape> list = jdbcTemplate.query(sql, new Object[] { user_id },
				new BeanPropertyRowMapper<OddShape>(OddShape.class));
		if (list != null && !list.isEmpty()) {
			return list;
		} else {
			logger.info("查询sos电话号码结果为空:" + user_id);
		}
		return null;
	}

	@Override
	public boolean updateFenceStatus(Long id, Integer status) {
		int i = jdbcTemplate.update("update fence_status set status = ? where id = ?", new Object[] { status, id },
				new int[] { Types.INTEGER, Types.INTEGER });
		return i == 1;
	}

	@Override
	public List<Fence> getRoundFenceList(Long user_id) {
		String sql = "select * from fence where user_id=?";
		List<Fence> list = jdbcTemplate.query(sql, new Object[] { user_id },
				new BeanPropertyRowMapper<Fence>(Fence.class));
		if (list != null && !list.isEmpty()) {
			return list;
		} else {
			logger.info("查询电子围栏结果为空:" + user_id);
		}
		return null;
	}

}
