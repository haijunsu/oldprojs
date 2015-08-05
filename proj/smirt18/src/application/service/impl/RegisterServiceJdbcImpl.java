/*
 * @(#)RegisterServiceJdbcImpl.java  2005-3-4
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.dao.AccompanyPersonDao;
import application.dao.AuthorDao;
import application.dao.CoauthorDao;
import application.dao.PaperDao;
import application.dao.ParticipantDao;
import application.dao.RegistrationFeeDao;
import application.dao.RelationBetweenAuthorAndPaperDao;
import application.dao.RelationBetweenParticipantAndPaperDao;
import application.dao.UserAccountDao;
import application.entities.TaccompanyPerson;
import application.entities.Tauthor;
import application.entities.Tcoauthor;
import application.entities.Tpaper;
import application.entities.Tparticipant;
import application.entities.TregistrationFee;
import application.entities.TrelationBetweenAuthorAndPaper;
import application.entities.TrelationBetweenParticipantAndPaper;
import application.entities.TuserAccount;
import application.exception.BusinessServiceException;
import application.service.RegisterService;
import application.util.EntitiesTransUtil;
import application.viewdata.AccompanyPersonView;
import application.viewdata.RegistrationView;
import application.viewdata.SmallPaperView;
import framework.exception.DaoException;
import framework.service.impl.JdbcServiceImpl;
import framework.util.StringUtil;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * </p>
 * 
 * $Revision$
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class RegisterServiceJdbcImpl extends JdbcServiceImpl implements
		RegisterService {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(RegisterServiceJdbcImpl.class);

	private ParticipantDao m_participantDao;

	private CoauthorDao m_coauthorDao;

	private UserAccountDao m_userAccountDao;

	private PaperDao m_paperDao;

	private AuthorDao m_authorDao;

	private AccompanyPersonDao m_accompanyPersonDao;

	private RelationBetweenParticipantAndPaperDao m_relationBetweenParticipantAndPaperDao;

	private RelationBetweenAuthorAndPaperDao m_relationBetweenAuthorAndPaperDao;

	private RegistrationFeeDao m_registrationFeeDao;

	/**
	 * @return Returns the authorDao.
	 */
	public AuthorDao getAuthorDao() {
		return m_authorDao;
	}

	/**
	 * @param authorDao
	 *            The authorDao to set.
	 */
	public void setAuthorDao(AuthorDao authorDao) {
		m_authorDao = authorDao;
	}

	/**
	 * @return Returns the relationBetweenAuthorAndPaperDao.
	 */
	public RelationBetweenAuthorAndPaperDao getRelationBetweenAuthorAndPaperDao() {
		return m_relationBetweenAuthorAndPaperDao;
	}

	/**
	 * @param relationBetweenAuthorAndPaperDao
	 *            The relationBetweenAuthorAndPaperDao to set.
	 */
	public void setRelationBetweenAuthorAndPaperDao(
			RelationBetweenAuthorAndPaperDao relationBetweenAuthorAndPaperDao) {
		m_relationBetweenAuthorAndPaperDao = relationBetweenAuthorAndPaperDao;
	}

	public RegistrationFeeDao getRegistrationFeeDao() {
		return this.m_registrationFeeDao;
	}

	public void setRegistrationFeeDao(RegistrationFeeDao registrationFeeDao) {
		this.m_registrationFeeDao = registrationFeeDao;
	}

	public RelationBetweenParticipantAndPaperDao getRelationBetweenParticipantAndPaperDao() {
		return this.m_relationBetweenParticipantAndPaperDao;
	}

	public void setRelationBetweenParticipantAndPaperDao(
			RelationBetweenParticipantAndPaperDao relationBetweenParticipantAndPaperDao) {
		this.m_relationBetweenParticipantAndPaperDao = relationBetweenParticipantAndPaperDao;
	}

	public AccompanyPersonDao getAccompanyPersonDao() {
		return this.m_accompanyPersonDao;
	}

	public void setAccompanyPersonDao(AccompanyPersonDao accompanyPersonDao) {
		this.m_accompanyPersonDao = accompanyPersonDao;
	}

	public CoauthorDao getCoauthorDao() {
		return this.m_coauthorDao;
	}

	public void setCoauthorDao(CoauthorDao coauthorDao) {
		this.m_coauthorDao = coauthorDao;
	}

	public PaperDao getPaperDao() {
		return this.m_paperDao;
	}

	public void setPaperDao(PaperDao paperDao) {
		this.m_paperDao = paperDao;
	}

	public ParticipantDao getParticipantDao() {
		return this.m_participantDao;
	}

	public void setParticipantDao(ParticipantDao participantDao) {
		this.m_participantDao = participantDao;
	}

	public UserAccountDao getUserAccountDao() {
		return this.m_userAccountDao;
	}

	public void setUserAccountDao(UserAccountDao userAccountDao) {
		this.m_userAccountDao = userAccountDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.service.RegisterService#loadRegistrationByParticipantNo(java.lang.String)
	 */
	public RegistrationView loadRegistrationByParticipantNo(String participantNo)
			throws BusinessServiceException {
		if (logger.isDebugEnabled()) {
			logger.debug("loadRegistrationByParticipantNo(String) - start");
		}

		try {
			RegistrationView _registrationView = new RegistrationView();
			Tparticipant _participant = getParticipantDao()
					.findByParticipantNo(participantNo);
			_registrationView.setParticipant(_participant);
			TaccompanyPerson[] _arrayAccompanyPerson = getAccompanyPersonDao()
					.findByParticipantNo(participantNo);
			AccompanyPersonView[] _arrayAccompanyPersonView = new AccompanyPersonView[_arrayAccompanyPerson.length];
			for (int i = 0; i < _arrayAccompanyPersonView.length; i++) {
				_arrayAccompanyPersonView[i] = new AccompanyPersonView();
				BeanUtils.copyProperties(_arrayAccompanyPersonView[i],
						_arrayAccompanyPerson[i]);
			}
			_registrationView.setAccompanyPerson(_arrayAccompanyPersonView);
			Tpaper[] _arrayPaper = getPaperDao().findByPartiNo(participantNo);
			SmallPaperView[] _arraySmallPaperView = new SmallPaperView[_arrayPaper.length];
			for (int i = 0; i < _arraySmallPaperView.length; i++) {
				_arraySmallPaperView[i] = new SmallPaperView();
				BeanUtils.copyProperties(_arraySmallPaperView[i],
						_arrayPaper[i]);
			}
			_registrationView.setPapers(_arraySmallPaperView);

			if (logger.isDebugEnabled()) {
				logger.debug("loadRegistrationByParticipantNo(String) - end");
			}
			return _registrationView;
		} catch (DaoException e) {
			logger
					.error(
							"loadRegistrationByParticipantNo(String) - DaoException",
							e);

			throw new BusinessServiceException(99, e.getMessage());
		} catch (Exception e) {
			logger.error("loadRegistrationByParticipantNo(String) - Exception",
					e);

			throw new BusinessServiceException(97, e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.service.RegisterService#loadRegistrationByUserID(java.lang.Integer)
	 */
	public RegistrationView loadRegistrationByUserID(Integer userid)
			throws BusinessServiceException {
		if (logger.isDebugEnabled()) {
			logger.debug("loadRegistrationByUserID(Integer) - start");
		}

		try {
			RegistrationView _registrationView = new RegistrationView();
			TuserAccount _userAccount = getUserAccountDao()
					.findByUserid(userid);
			Tparticipant _participant = EntitiesTransUtil
					.userAccountTransToParticipant(_userAccount);
			_registrationView.setParticipant(_participant);
			TaccompanyPerson[] _arrayAccompanyPerson = new TaccompanyPerson[0];
			AccompanyPersonView[] _arrayAccompanyPersonView = new AccompanyPersonView[_arrayAccompanyPerson.length];
			for (int i = 0; i < _arrayAccompanyPersonView.length; i++) {
				_arrayAccompanyPersonView[i] = new AccompanyPersonView();
				BeanUtils.copyProperties(_arrayAccompanyPersonView[i],
						_arrayAccompanyPerson[i]);
			}
			_registrationView.setAccompanyPerson(_arrayAccompanyPersonView);
			Tpaper[] _arrayPaper = getPaperDao().findAllPapersByUserid(userid);
			SmallPaperView[] _arraySmallPaperView = new SmallPaperView[_arrayPaper.length];
			for (int i = 0; i < _arraySmallPaperView.length; i++) {
				_arraySmallPaperView[i] = new SmallPaperView();
				BeanUtils.copyProperties(_arraySmallPaperView[i],
						_arrayPaper[i]);
			}
			_registrationView.setPapers(_arraySmallPaperView);

			if (logger.isDebugEnabled()) {
				logger.debug("loadRegistrationByUserID(String) - end");
			}
			return _registrationView;
		} catch (DaoException e) {
			logger.error("loadRegistrationByUserID(String) - DaoException", e);

			throw new BusinessServiceException(99, e.getMessage());
		} catch (Exception e) {
			logger.error("loadRegistrationByUserID(String) - Exception", e);

			throw new BusinessServiceException(97, e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.service.RegisterService#loadRegistrationByPaperNumberAndEmail(java.lang.String,
	 *      java.lang.String)
	 */
	public RegistrationView loadRegistrationByPaperNumberAndEmail(
			String paperNumber, String email) throws BusinessServiceException {
		if (logger.isDebugEnabled()) {
			logger.debug("loadRegistrationByUserID(Integer) - start");
		}

		try {
			RegistrationView _registrationView = new RegistrationView();
			Tcoauthor _coauthor = getCoauthorDao().findCoauthor(paperNumber,
					email);
			Tparticipant _participant = EntitiesTransUtil
					.coauthorTransToParticipant(_coauthor);
			_registrationView.setParticipant(_participant);
			TaccompanyPerson[] _arrayAccompanyPerson = new TaccompanyPerson[0];
			AccompanyPersonView[] _arrayAccompanyPersonView = new AccompanyPersonView[_arrayAccompanyPerson.length];
			for (int i = 0; i < _arrayAccompanyPersonView.length; i++) {
				_arrayAccompanyPersonView[i] = new AccompanyPersonView();
				BeanUtils.copyProperties(_arrayAccompanyPersonView[i],
						_arrayAccompanyPerson[i]);
			}
			_registrationView.setAccompanyPerson(_arrayAccompanyPersonView);
			Tpaper[] _arrayPaper = getPaperDao().findAllPapersByEmail(email);
			SmallPaperView[] _arraySmallPaperView = new SmallPaperView[_arrayPaper.length];
			for (int i = 0; i < _arraySmallPaperView.length; i++) {
				_arraySmallPaperView[i] = new SmallPaperView();
				BeanUtils.copyProperties(_arraySmallPaperView[i],
						_arrayPaper[i]);
			}
			_registrationView.setPapers(_arraySmallPaperView);

			if (logger.isDebugEnabled()) {
				logger.debug("loadRegistrationByUserID(String) - end");
			}
			return _registrationView;
		} catch (DaoException e) {
			logger.error("loadRegistrationByUserID(String) - DaoException", e);

			throw new BusinessServiceException(99, e.getMessage());
		} catch (Exception e) {
			logger.error("loadRegistrationByUserID(String) - Exception", e);

			throw new BusinessServiceException(97, e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.service.RegisterService#updateParticipant(application.entities.Tparticipant)
	 */
	public void updateParticipant(Tparticipant participant)
			throws BusinessServiceException {
		try {
			getParticipantDao().update(participant);
		} catch (DaoException e) {
			throw new BusinessServiceException(99, e.getMessage());
		} catch (Exception e) {
			throw new BusinessServiceException(97, e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.service.RegisterService#createParticipant(application.entities.Tparticipant)
	 */
	public void createParticipant(Tparticipant participant)
			throws BusinessServiceException {
		try {
			if (participant.getChvPartiEmail() == null) {
				participant.setChvPartiEmail("");
			}
			Tparticipant _tparticipant = getParticipantDao()
					.findByEmailFirstNameLastName(
							participant.getChvPartiEmail(),
							participant.getChvPartiFirstName(),
							participant.getChvPartiLastName());
			if (_tparticipant == null) {
				_tparticipant = getParticipantDao().create(participant);
				TregistrationFee _tregistrationFee = new TregistrationFee();
				_tregistrationFee.setChrPartiNo(participant.getChrPartiNo());
				_tregistrationFee.setChrPaymentMethodNo("1");
				getRegistrationFeeDao().create(_tregistrationFee);
			} else {
				getRelationBetweenParticipantAndPaperDao()
						.remove(
								" chrPartiNo = '"
										+ _tparticipant.getChrPartiNo() + "'");
			}

			if (StringUtil.isNotBlank(_tparticipant.getChvPartiEmail())) {
				Tcoauthor[] _arrayCoauthor = getCoauthorDao().findByEmail(
						participant.getChvPartiEmail(), true);
				TuserAccount[] _arrayUserAccount = getUserAccountDao()
						.findByUserAccount(participant.getChvPartiEmail());
				Tpaper[][] _arrayPaper = new Tpaper[_arrayUserAccount.length][0];
				int _nPaperCount = _arrayCoauthor.length;
				for (int i = 0; i < _arrayPaper.length; i++) {
					_arrayPaper[i] = getPaperDao().findAllPapersByUserid(
							_arrayUserAccount[i].getIntUserID());
					_nPaperCount += _arrayPaper[i].length;
				}

				TrelationBetweenParticipantAndPaper[] _relationBetweenParticipantAndPapers = new TrelationBetweenParticipantAndPaper[_nPaperCount];
				int _nIndex = 0;
				for (int i = 0; i < _arrayCoauthor.length; i++) {
					_relationBetweenParticipantAndPapers[_nIndex] = new TrelationBetweenParticipantAndPaper();
					_relationBetweenParticipantAndPapers[_nIndex]
							.setChrPartiNo(participant.getChrPartiNo());
					_relationBetweenParticipantAndPapers[_nIndex]
							.setChvPaperNumber(_arrayCoauthor[i]
									.getChvPaperNumber());
					_relationBetweenParticipantAndPapers[_nIndex]
							.setInyAuthorRank(_arrayCoauthor[i]
									.getInyAuthorRank());
					_nIndex++;
				}
				for (int i = 0; i < _arrayPaper.length; i++) {
					for (int j = 0; j < _arrayPaper[i].length; j++) {
						_relationBetweenParticipantAndPapers[_nIndex] = new TrelationBetweenParticipantAndPaper();
						_relationBetweenParticipantAndPapers[_nIndex]
								.setChrPartiNo(participant.getChrPartiNo());
						_relationBetweenParticipantAndPapers[_nIndex]
								.setChvPaperNumber(_arrayPaper[i][j]
										.getChvPaperNumber());
						_relationBetweenParticipantAndPapers[_nIndex]
								.setInyAuthorRank(_arrayPaper[i][j]
										.getInyAuthorRank());
						_relationBetweenParticipantAndPapers[_nIndex]
								.setIntUserID(_arrayPaper[i][j].getIntUserID());
						_nIndex++;
					}
				}
				_relationBetweenParticipantAndPapers = EntitiesTransUtil
						.removeDuplicateArray(_relationBetweenParticipantAndPapers);
				for (int i = 0; i < _relationBetweenParticipantAndPapers.length; i++) {
					getRelationBetweenParticipantAndPaperDao().create(
							_relationBetweenParticipantAndPapers[i]);
				}
			}
			if (StringUtil.isNotBlank(_tparticipant.getChrAuthorNo())) {
				TrelationBetweenAuthorAndPaper[] _authorAndPapers = getRelationBetweenAuthorAndPaperDao()
						.findByAuthorNo(_tparticipant.getChrAuthorNo());
				for (int i = 0; i < _authorAndPapers.length; i++) {

					TrelationBetweenParticipantAndPaper _betweenParticipantAndPaper = getRelationBetweenParticipantAndPaperDao()
							.findByPartiNoAndPaperNumber(
									_tparticipant.getChrPartiNo(),
									_authorAndPapers[i].getChvPaperNumber());
					if (_betweenParticipantAndPaper != null) {
						continue;
					}
					_betweenParticipantAndPaper = new TrelationBetweenParticipantAndPaper();
					_betweenParticipantAndPaper.setChrPartiNo(_tparticipant
							.getChrPartiNo());
					_betweenParticipantAndPaper
							.setChvPaperNumber(_authorAndPapers[i]
									.getChvPaperNumber());
					_betweenParticipantAndPaper
							.setInyAuthorRank(_authorAndPapers[i]
									.getIntAuthorRank());
					getRelationBetweenParticipantAndPaperDao().create(
							_betweenParticipantAndPaper);
				}
			}

		} catch (DaoException e) {
			throw new BusinessServiceException(99, e.getMessage());
		} catch (Exception e) {
			throw new BusinessServiceException(97, e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.service.RegisterService#removeParticipant(application.entities.Tparticipant)
	 */
	public void removeParticipant(Tparticipant participant)
			throws BusinessServiceException {
		try {
			getParticipantDao().remove(participant);
		} catch (DaoException e) {
			throw new BusinessServiceException(99, e.getMessage());
		} catch (Exception e) {
			throw new BusinessServiceException(97, e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.service.RegisterService#loadRegistrationByAuthorNo(java.lang.String)
	 */
	public RegistrationView loadRegistrationByAuthorNo(String authorNo)
			throws BusinessServiceException {
		try {
			RegistrationView _registrationView = new RegistrationView();
			Tauthor _userAccount = getAuthorDao().findByAuthorNo(authorNo);
			Tparticipant _participant = EntitiesTransUtil
					.authorTransToParticipant(_userAccount);
			_registrationView.setParticipant(_participant);
			AccompanyPersonView[] _arrayAccompanyPersonView = new AccompanyPersonView[0];
			_registrationView.setAccompanyPerson(_arrayAccompanyPersonView);
			Tpaper[] _arrayPaper = getPaperDao().findAllPapersByAuthorNo(
					authorNo);
			SmallPaperView[] _arraySmallPaperView = new SmallPaperView[_arrayPaper.length];
			for (int i = 0; i < _arraySmallPaperView.length; i++) {
				_arraySmallPaperView[i] = new SmallPaperView();
				BeanUtils.copyProperties(_arraySmallPaperView[i],
						_arrayPaper[i]);
			}
			_registrationView.setPapers(_arraySmallPaperView);

			if (logger.isDebugEnabled()) {
				logger.debug("loadRegistrationByUserID(String) - end");
			}
			return _registrationView;
		} catch (DaoException e) {
			logger.error("loadRegistrationByUserID(String) - DaoException", e);

			throw new BusinessServiceException(99, e.getMessage());
		} catch (Exception e) {
			logger.error("loadRegistrationByUserID(String) - Exception", e);

			throw new BusinessServiceException(97, e.getMessage());
		}
	}

}