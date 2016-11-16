package com.faceye.component.questionnaire.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.questionnaire.entity.Rabbit;
import com.faceye.component.questionnaire.repository.mongo.RabbitRepository;
import com.faceye.component.questionnaire.service.RabbitService;
import com.faceye.component.security.web.entity.User;
import com.faceye.component.security.web.service.UserService;
import com.faceye.component.weixin.entity.WeixinUser;
import com.faceye.component.weixin.service.AccountService;
import com.faceye.component.weixin.service.WeixinUserService;
import com.faceye.component.weixin.service.oauth2.OAuth2Service;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.Reporter;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.ServiceException;
import com.faceye.feature.util.bean.BeanContextUtil;
import com.faceye.feature.util.http.HttpUtil;
import com.querydsl.core.types.Predicate;

@Service
public class RabbitServiceImpl extends BaseMongoServiceImpl<Rabbit, Long, RabbitRepository> implements RabbitService {

	@Autowired
	private UserService userService = null;
	@Autowired
	private WeixinUserService weixinUserService = null;
	@Autowired
	private OAuth2Service oAuth2Service = null;

//	@Value("#{property['emergency.organization.weixin.account.id']}")
//	private Long accountId = null;
	@Autowired
	private AccountService accountServie = null;

	@Autowired
	public RabbitServiceImpl(RabbitRepository dao) {
		super(dao);
	}

	@Override
	public Page<Rabbit> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Rabbit> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Rabbit> builder = new PathBuilder<Rabbit>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		if (predicate != null) {
			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		}
		Sort sort = new Sort(Direction.DESC, "id");
		Page<Rabbit> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Rabbit>("id") {
			// })
			List<Rabbit> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Rabbit>(items);

		}
		return res;
	}

	@Override
	public Rabbit getRabbitByWeixinUser(WeixinUser weixinUser) {
		return dao.getRabbitByWeixinUser(weixinUser);
	}

	@Override
	public Rabbit getCurrentLoginRabbit() {
		Rabbit rabbit = null;
		User user = this.userService.getCurrentLoginUser();
		if (user != null) {
			String weixinOpenId = user.getWeixinOpenId();
			if (StringUtils.isNotEmpty(weixinOpenId)) {
				WeixinUser weixinUser = this.weixinUserService.getWeixinUserByOpenid(weixinOpenId);
				if (weixinUser != null) {
					rabbit = this.getRabbitByWeixinUser(weixinUser);
				}
			}
		}
		return rabbit;
	}

	@Override
	public void onWeixinOAuth(HttpServletRequest request, HttpServletResponse response) {
		Map params = HttpUtil.getRequestParams(request);
//		String appid = this.accountServie.get(accountId).getAppId();
		String appid="";
		String code = MapUtils.getString(params, "code");
		BeanContextUtil.getInstance().getBean(Reporter.class).reporter(params);
		if (StringUtils.isNotEmpty(code)&&StringUtils.isNotEmpty(appid)) {
			logger.debug(">>FaceYe --> appid is:" + appid + ",code is:" + code);
			WeixinUser weixinUser = this.oAuth2Service.oauth2(appid, code);
			if (weixinUser != null) {
				logger.debug(">>FaceYe --> weixin user opeid is:" + weixinUser.getOpenid());
				this.userService.weixinOAuth2AndAutoLogin(request, response, weixinUser.getOpenid());
				Rabbit rabbit = this.getRabbitByWeixinUser(weixinUser);
				if (rabbit == null) {
					rabbit = new Rabbit();
					rabbit.setName(weixinUser.getNickname());
					rabbit.setWeixinUser(weixinUser);
				} else {
					if (StringUtils.isEmpty(rabbit.getName())) {
						rabbit.setName(weixinUser.getNickname());
					}
				}
				this.save(rabbit);
				// model.addAttribute("weixinUser", weixinUser);
				// 将用户信息放在session里面
				logger.debug(">>FaceYe --> weixin user headimg is:"+weixinUser.getHeadimgurl()+",weixin nickname:"+weixinUser.getNickname());
				request.getSession().setAttribute("weixinUser", weixinUser);
				request.getSession().setAttribute("rabbit", rabbit);
			} else {
				logger.debug(">>FaceYe --> Have not got weixinUser.");
			}
		} else {
			logger.debug(">>FaceYe appid or code is emty now .............................................");
		}
	}

}/** @generate-service-source@ **/
