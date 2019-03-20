package com.yiifaa.mirana.exam.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.yiifaa.mirana.exam.domain.ExamState;
import com.yiifaa.mirana.exam.helper.ExamStateHelper;
import com.yiifaa.mirana.exam.repository.ExamStateRepository;
import com.yiifaa.mirana.exam.service.ExamStateService;
import com.yiifaa.mirana.persistence.GenericRepository;

@Service
public class ExamStateServiceImpl extends JdbcDaoSupport implements ExamStateService {
	
	private ExamStateRepository examRepository;

	@Inject
	public ExamStateServiceImpl(DataSource dataSource, ExamStateRepository examRepository) {
		this.setDataSource(dataSource);
		this.examRepository = examRepository;
	}
	
	@Override
	public List<ExamStateHelper> findByUserId(String userId) {
		String sql = "select ex.pk \"PK\", ex.EXAM_NAME, es.PK \"SID\", es.A_STATE "
				+ "from t_risk_exam ex LEFT OUTER JOIN t_exam_state es "
				+ "ON ex.PK = es.ex_id and es.USER_ID= ? "
				+ "order by es.CREATE_TIME desc";
		List<Map<String, Object>> results = this.getJdbcTemplate().queryForList(sql, userId);
		List<ExamStateHelper> values = Lists.newArrayList();
		for(Map<String, Object> item: results) {
			ExamStateHelper value = new ExamStateHelper();
			value.setId((Long)item.get("PK"));
			value.setName((String)item.get("EXAM_NAME"));
			value.setSid((Long)item.get("SID"));
			value.setState((Integer)item.get("A_STATE"));
			values.add(value);
		}
		return values;
	}

	@Override
	public GenericRepository<ExamState, Long> getRepository() {
		return this.examRepository;
	}

	@Override
	@Transactional(readOnly = false)
	public ExamState start(String userId, Long exId) {
		ExamState st = new ExamState();
		st.setUserId(userId);
		st.setState(1);
		st.setCreateTime(new Date());
		st.setExId(exId);
		return this.examRepository.save(st);
	}

	@Override
	public ExamState complete(Long sid) {
		Optional<ExamState> st = this.examRepository.findById(sid);
		if(st.isPresent()) {
			ExamState es = st.get();
			es.setState(2);
			return this.examRepository.save(es);
		}
		return null;
	}

}
