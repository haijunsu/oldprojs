/*
 * @(#)SearchServiceImpl.java  2005-2-27
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header$
 * $Log$
 */
package application.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.dao.AuthorDao;
import application.dao.CoauthorDao;
import application.dao.PaperDao;
import application.dao.PaperPresentedDao;
import application.dao.ParticipantDao;
import application.dao.RelationBetweenAuthorAndPaperDao;
import application.dao.RelationBetweenParticipantAndPaperDao;
import application.dao.UserAccountDao;
import application.entities.Tauthor;
import application.entities.Tcoauthor;
import application.entities.Tpaper;
import application.entities.TpaperPresented;
import application.entities.Tparticipant;
import application.entities.TuserAccount;
import application.exception.BusinessServiceException;
import application.service.SearchService;
import application.util.EntitiesTransUtil;
import application.viewdata.PaperView;
import application.viewdata.SmallAuthorView;
import application.viewdata.SmallPaperView;
import application.viewdata.SmallUserView;
import framework.exception.DaoException;
import framework.service.impl.JdbcServiceImpl;

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
public class SearchServiceJdbcImpl extends JdbcServiceImpl implements
		SearchService {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(SearchServiceJdbcImpl.class);

	private PaperDao m_paperDao;

	private UserAccountDao m_userAccountDao;

	private ParticipantDao m_participantDao;

	private AuthorDao m_authorDao;

	private CoauthorDao m_coauthorDao;

	private PaperPresentedDao m_paperPresentedDao;

	private RelationBetweenParticipantAndPaperDao m_relationBetweenParticipantAndPaperDao;

	private RelationBetweenAuthorAndPaperDao m_relationBetweenAuthorAndPaperDao;

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

	public RelationBetweenParticipantAndPaperDao getRelationBetweenParticipantAndPaperDao() {
		return this.m_relationBetweenParticipantAndPaperDao;
	}

	public void setRelationBetweenParticipantAndPaperDao(
			RelationBetweenParticipantAndPaperDao relationBetweenParticipantAndPaperDao) {
		this.m_relationBetweenParticipantAndPaperDao = relationBetweenParticipantAndPaperDao;
	}

	public PaperPresentedDao getPaperPresentedDao() {
		return this.m_paperPresentedDao;
	}

	public void setPaperPresentedDao(PaperPresentedDao paperPresentedDao) {
		this.m_paperPresentedDao = paperPresentedDao;
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

	public PaperView serachByPaperLNumber(String paperLNumber)
			throws BusinessServiceException {
		if (logger.isDebugEnabled()) {
			logger.debug("serachByPaperLNumber(String) - start");
		}

		PaperView _paperView = new PaperView();
		try {
			Tpaper _paper = getPaperDao().findByPaperLNumber(paperLNumber);
			if (_paper == null) {
				return null;
			}
			SmallPaperView _smallPaperView = new SmallPaperView();
			BeanUtils.copyProperties(_smallPaperView, _paper);
			_paperView.setSmallPaperView(_smallPaperView);
			filledPaperViewOtherInfo(_paperView);

		} catch (DaoException e) {
			logger.error("serachByPaperLNumber(String)", e);

			throw new BusinessServiceException(99, e.getMessage());
		} catch (Exception e) {
			logger.error("serachByPaperLNumber(String)", e);

			throw new BusinessServiceException(97, e.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("serachByPaperLNumber(String) - end");
		}
		return _paperView;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see application.service.SearchService#serachByPaperNumber(java.lang.String)
	 */
	public PaperView serachByPaperNumber(String paperNumber)
			throws BusinessServiceException {
		if (logger.isDebugEnabled()) {
			logger.debug("serachByPaperNumber(String) - start");
		}

		PaperView _paperView = new PaperView();
		try {
			Tpaper _paper = getPaperDao().findByPaperNumber(paperNumber);
			if (_paper == null) {
				return null;
			}
			SmallPaperView _smallPaperView = new SmallPaperView();
			BeanUtils.copyProperties(_smallPaperView, _paper);
			_paperView.setSmallPaperView(_smallPaperView);
			filledPaperViewOtherInfo(_paperView);

		} catch (DaoException e) {
			logger.error("serachByPaperNumber(String)", e);

			throw new BusinessServiceException(99, e.getMessage());
		} catch (Exception e) {
			logger.error("serachByPaperNumber(String)", e);

			throw new BusinessServiceException(97, e.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("serachByPaperNumber(String) - end");
		}
		return _paperView;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see application.service.SearchService#searchByPaperTitle(java.lang.String)
	 */
	public SmallPaperView[] searchByPaperTitle(String paperTitle)
			throws BusinessServiceException {
		if (logger.isDebugEnabled()) {
			logger.debug("searchByPaperTitle(String) - start");
		}

		SmallPaperView[] _arraySmallPaperView = new SmallPaperView[0];

		try {
			Tpaper[] _arrayPaper = getPaperDao().findByPaperTitle(paperTitle);
			if (_arrayPaper.length > 0) {
				_arraySmallPaperView = new SmallPaperView[_arrayPaper.length];
				for (int i = 0; i < _arrayPaper.length; i++) {
					_arraySmallPaperView[i] = new SmallPaperView();
					BeanUtils.copyProperties(_arraySmallPaperView[i],
							_arrayPaper[i]);
				}
			}

		} catch (DaoException e) {
			logger.error("searchByPaperTitle(String)", e);

			throw new BusinessServiceException(99, e.getMessage());
		} catch (Exception e) {
			logger.error("searchByPaperTitle(String)", e);

			throw new BusinessServiceException(97, e.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("searchByPaperTitle(String) - end");
		}
		return _arraySmallPaperView;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see application.service.SearchService#searchByUserAccount(java.lang.String)
	 */
	public SmallUserView[] searchByUserAccount(String userAccount)
			throws BusinessServiceException {
		if (logger.isDebugEnabled()) {
			logger.debug("searchByUserAccount(String) - start");
		}

		SmallUserView[] returnSmallUserViewArray = searchByEmail(userAccount);
		if (logger.isDebugEnabled()) {
			logger.debug("searchByUserAccount(String) - end");
		}
		return returnSmallUserViewArray;
	}

	protected SmallUserView[] searchByEmail(String email, boolean isAll)
			throws BusinessServiceException {
		if (logger.isDebugEnabled()) {
			logger.debug("searchByEmail(String, boolean) - start");
		}

		SmallUserView[] _arrayUserView = new SmallUserView[0];
		try {
			Tparticipant[] _arrayParticipant = getParticipantDao().findByEmail(
					email);
			TuserAccount[] _arrayUserAccount = new TuserAccount[0];
			Tcoauthor[] _arrayCoauthor = new Tcoauthor[0];
			Tauthor[] _arrayAuthor = new Tauthor[0];
			if (isAll) {
				_arrayUserAccount = getUserAccountDao().findByEmail(email);
				_arrayCoauthor = getCoauthorDao().findByEmail(email);
				_arrayAuthor = getAuthorDao().findByEmail(email);
			}
			_arrayUserView = combineUserView(_arrayParticipant, _arrayAuthor,
					_arrayUserAccount, _arrayCoauthor);

		} catch (DaoException e) {
			logger.error("searchByEmail(String, boolean)", e);

			throw new BusinessServiceException(99, e.getMessage());
		} catch (Exception e) {
			logger.error("searchByEmail(String, boolean)", e);

			throw new BusinessServiceException(97, e.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("searchByEmail(String, boolean) - end");
		}
		return _arrayUserView;
	}

	protected SmallUserView[] searchByFirstName(String firstName, boolean isAll)
			throws BusinessServiceException {
		if (logger.isDebugEnabled()) {
			logger.debug("searchByFirstName(String, boolean) - start");
		}

		SmallUserView[] _arrayUserView = new SmallUserView[0];
		try {
			Tparticipant[] _arrayParticipant = getParticipantDao()
					.findByFirstName(firstName);
			TuserAccount[] _arrayUserAccount = new TuserAccount[0];
			Tcoauthor[] _arrayCoauthor = new Tcoauthor[0];
			Tauthor[] _arrayAuthor = new Tauthor[0];
			if (isAll) {
				_arrayUserAccount = getUserAccountDao().findByFirstName(
						firstName);
				_arrayCoauthor = getCoauthorDao().findByFirstName(firstName);
				_arrayAuthor = getAuthorDao().findByFirstName(firstName);
			}
			_arrayUserView = combineUserView(_arrayParticipant, _arrayAuthor,
					_arrayUserAccount, _arrayCoauthor);

		} catch (DaoException e) {
			logger.error("searchByFirstName(String, boolean)", e);

			throw new BusinessServiceException(99, e.getMessage());
		} catch (Exception e) {
			logger.error("searchByFirstName(String, boolean)", e);

			throw new BusinessServiceException(97, e.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("searchByFirstName(String, boolean) - end");
		}
		return _arrayUserView;
	}

	protected SmallUserView[] searchByLastName(String lastName, boolean isAll)
			throws BusinessServiceException {
		if (logger.isDebugEnabled()) {
			logger.debug("searchByLastName(String, boolean) - start");
		}

		SmallUserView[] _arrayUserView = new SmallUserView[0];
		try {
			Tparticipant[] _arrayParticipant = getParticipantDao()
					.findByLastName(lastName);
			TuserAccount[] _arrayUserAccount = new TuserAccount[0];
			Tcoauthor[] _arrayCoauthor = new Tcoauthor[0];
			Tauthor[] _arrayAuthor = new Tauthor[0];
			if (isAll) {
				_arrayUserAccount = getUserAccountDao()
						.findByLastName(lastName);
				_arrayCoauthor = getCoauthorDao().findByLastName(lastName);
				_arrayAuthor = getAuthorDao().findByLastName(lastName);
			}
			_arrayUserView = combineUserView(_arrayParticipant, _arrayAuthor,
					_arrayUserAccount, _arrayCoauthor);
		} catch (DaoException e) {
			logger.error("searchByLastName(String, boolean)", e);

			throw new BusinessServiceException(99, e.getMessage());
		} catch (Exception e) {
			logger.error("searchByLastName(String, boolean)", e);

			throw new BusinessServiceException(97, e.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("searchByLastName(String, boolean) - end");
		}
		return _arrayUserView;
	}

	protected SmallUserView[] combineUserView(Tparticipant[] participants,
			Tauthor[] authors, TuserAccount[] userAccounts,
			Tcoauthor[] coauthors) throws Exception {
		int _nArraySize = 0;
		if (participants != null) {
			_nArraySize = participants.length;
		}
		if (authors != null) {
			_nArraySize += authors.length;
		}
		if (userAccounts != null) {
			_nArraySize += userAccounts.length;
		}
		if (coauthors != null) {
			_nArraySize += coauthors.length;
		}
		SmallUserView[] _arraySmallUserView = new SmallUserView[_nArraySize];
		Tparticipant _participant = new Tparticipant();
		int _nIndex = 0;
		if (participants != null) {
			for (int i = 0; i < participants.length; i++) {
				_arraySmallUserView[_nIndex] = new SmallUserView();
				BeanUtils.copyProperties(_arraySmallUserView[_nIndex],
						participants[i]);
				_arraySmallUserView[_nIndex].setRegistered(true);
				_nIndex++;
			}
		}
		if (authors != null) {
			for (int i = 0; i < authors.length; i++) {
				_arraySmallUserView[_nIndex] = new SmallUserView();
				_participant = EntitiesTransUtil
						.authorTransToParticipant(authors[i]);
				BeanUtils.copyProperties(_arraySmallUserView[_nIndex],
						_participant);
				_arraySmallUserView[_nIndex].setRegistered(false);
				_nIndex++;
			}
		}
		if (userAccounts != null) {
			for (int i = 0; i < userAccounts.length; i++) {
				_arraySmallUserView[_nIndex] = new SmallUserView();
				_participant = EntitiesTransUtil
						.userAccountTransToParticipant(userAccounts[i]);
				BeanUtils.copyProperties(_arraySmallUserView[_nIndex],
						_participant);
				_arraySmallUserView[_nIndex].setRegistered(false);
				_arraySmallUserView[_nIndex].setIntUserID(userAccounts[i]
						.getIntUserID());
				_nIndex++;
			}
		}
		if (coauthors != null) {
			for (int i = 0; i < coauthors.length; i++) {
				_arraySmallUserView[_nIndex] = new SmallUserView();
				_participant = EntitiesTransUtil
						.coauthorTransToParticipant(coauthors[i]);
				BeanUtils.copyProperties(_arraySmallUserView[_nIndex],
						_participant);
				_arraySmallUserView[_nIndex].setRegistered(false);
				_arraySmallUserView[_nIndex].setChvPaperNumber(coauthors[i]
						.getChvPaperNumber());
				_arraySmallUserView[_nIndex].setInyAuthorRank(coauthors[i]
						.getInyAuthorRank());
				_nIndex++;
			}
		}
		_arraySmallUserView = EntitiesTransUtil
				.removeDuplicateArray(_arraySmallUserView);

		return _arraySmallUserView;
	}

	protected SmallAuthorView[] combineAuthorView(Tparticipant[] participants,
			TuserAccount[] userAccounts, Tcoauthor[] coauthors,
			Tauthor[] authors, String paperNumber) throws Exception {
		int _nArraySize = 0;
		if (participants != null) {
			_nArraySize = participants.length;
		}
		if (userAccounts != null) {
			_nArraySize += userAccounts.length;
		}
		if (coauthors != null) {
			_nArraySize += coauthors.length;
		}
		if (authors != null) {
			_nArraySize += authors.length;
		}
		SmallAuthorView[] _arraySmallUserView = new SmallAuthorView[_nArraySize];
		Tparticipant _participant = new Tparticipant();
		int _nIndex = 0;
		if (participants != null) {
			for (int i = 0; i < participants.length; i++) {
				_arraySmallUserView[_nIndex] = new SmallAuthorView();
				BeanUtils.copyProperties(_arraySmallUserView[_nIndex],
						participants[i]);
				_arraySmallUserView[_nIndex].setRegistered(true);
				_arraySmallUserView[_nIndex].setChvPaperNumber(paperNumber);
				_arraySmallUserView[_nIndex]
						.setInyAuthorRank(getRelationBetweenParticipantAndPaperDao()
								.findAuthorRankByPartiNoAndPaperNumber(
										paperNumber,
										_arraySmallUserView[_nIndex]
												.getChrPartiNo()));
				_nIndex++;
			}
		}
		if (authors != null) {
			for (int i = 0; i < authors.length; i++) {
				_arraySmallUserView[_nIndex] = new SmallAuthorView();
				_participant = EntitiesTransUtil
							.authorTransToParticipant(authors[i]);
				BeanUtils.copyProperties(_arraySmallUserView[_nIndex],
						_participant);
				_arraySmallUserView[_nIndex].setRegistered(false);
				_arraySmallUserView[_nIndex].setChvPaperNumber(paperNumber);
				_arraySmallUserView[_nIndex]
						.setInyAuthorRank(getRelationBetweenAuthorAndPaperDao()
								.findAuthorRankByAuthorNoAndPaperNumber(
										authors[i].getChrAuthorNo(),
										paperNumber));
				_nIndex++;
			}
		}
		if (userAccounts != null) {
			for (int i = 0; i < userAccounts.length; i++) {
				_arraySmallUserView[_nIndex] = new SmallAuthorView();
				_participant = EntitiesTransUtil
						.userAccountTransToParticipant(userAccounts[i]);
				BeanUtils.copyProperties(_arraySmallUserView[_nIndex],
						_participant);
				_arraySmallUserView[_nIndex].setRegistered(false);
				_arraySmallUserView[_nIndex].setIntUserID(userAccounts[i]
						.getIntUserID());
				_arraySmallUserView[_nIndex].setInyAuthorRank(getPaperDao()
						.findAuthorRankByPaperNumberAndUserID(paperNumber,
								_arraySmallUserView[_nIndex].getIntUserID()));
				_nIndex++;
			}
		}
		if (coauthors != null) {
			for (int i = 0; i < coauthors.length; i++) {
				_arraySmallUserView[_nIndex] = new SmallAuthorView();
				_participant = EntitiesTransUtil
						.coauthorTransToParticipant(coauthors[i]);
				BeanUtils.copyProperties(_arraySmallUserView[_nIndex],
						_participant);
				_arraySmallUserView[_nIndex].setRegistered(false);
				_arraySmallUserView[_nIndex].setChvPaperNumber(coauthors[i]
						.getChvPaperNumber());
				_arraySmallUserView[_nIndex].setInyAuthorRank(coauthors[i]
						.getInyAuthorRank());
				_nIndex++;
			}
		}
		_arraySmallUserView = EntitiesTransUtil
				.removeDuplicateArray(_arraySmallUserView);
//		Arrays.sort(_arraySmallUserView);
		return _arraySmallUserView;
	}

	public void filledPaperViewOtherInfo(PaperView paperView)
			throws DaoException, Exception {

		TpaperPresented _paperPresented = getPaperPresentedDao()
				.findByPaperNumber(
						paperView.getSmallPaperView().getChvPaperNumber());
		paperView.setPaperPresented(_paperPresented);
		Tparticipant[] _arrayParticipant = getParticipantDao()
				.findByPaperNumber(
						paperView.getSmallPaperView().getChvPaperNumber());
		if (_paperPresented != null) {
			boolean _isFoundParti = false;
			if (_arrayParticipant != null) {
				for (int i = 0; i < _arrayParticipant.length; i++) {
					if (_arrayParticipant[i].getChrPartiNo().equals(
							_paperPresented.getChrPresenterPartiNo())) {
						paperView.setBitIsBiography(_arrayParticipant[i]
								.getBitIsBiography());
						paperView.setBitIsCheckin(_arrayParticipant[i]
								.getBitIsCheckin());
						_isFoundParti = true;
					}
				}
			}
			if (!_isFoundParti) {
				Tparticipant _tparticipant = getParticipantDao()
						.findByParticipantNo(
								_paperPresented.getChrPresenterPartiNo());
				paperView.setBitIsBiography(_tparticipant.getBitIsBiography());
				paperView.setBitIsCheckin(_tparticipant.getBitIsCheckin());
			}
		}
		TuserAccount[] _arrayUserAccount = new TuserAccount[] { getUserAccountDao()
				.findByUserid(paperView.getSmallPaperView().getIntUserID()) };
		Tcoauthor[] _arrayCoauthor = getCoauthorDao().findByPaperNumber(
				paperView.getSmallPaperView().getChvPaperNumber());
		Tauthor[] _arrayAuthor = getAuthorDao().findByPaperNumber(
				paperView.getSmallPaperView().getChvPaperNumber());
		SmallAuthorView[] _arraySmallAuthorView = combineAuthorView(
				_arrayParticipant, _arrayUserAccount, _arrayCoauthor,
				_arrayAuthor, paperView.getSmallPaperView().getChvPaperNumber());
		paperView.setAuthors(_arraySmallAuthorView);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see application.service.SearchService#searchRegisterByEmail(java.lang.String)
	 */
	public SmallUserView[] searchRegisterByEmail(String email)
			throws BusinessServiceException {
		SmallUserView[] _arraySmallUserView = searchByEmail(email, false);
		return _arraySmallUserView;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see application.service.SearchService#searchRegisterByFirstName(java.lang.String)
	 */
	public SmallUserView[] searchRegisterByFirstName(String firstName)
			throws BusinessServiceException {
		SmallUserView[] _arraySmallUserView = searchByFirstName(firstName,
				false);
		return _arraySmallUserView;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see application.service.SearchService#searchRegisterByLastName(java.lang.String)
	 */
	public SmallUserView[] searchRegisterByLastName(String lastName)
			throws BusinessServiceException {
		SmallUserView[] _arraySmallUserView = searchByLastName(lastName, false);
		return _arraySmallUserView;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see application.service.SearchService#searchByEmail(java.lang.String)
	 */
	public SmallUserView[] searchByEmail(String email)
			throws BusinessServiceException {
		SmallUserView[] _arraySmallUserView = searchByEmail(email, true);
		return _arraySmallUserView;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see application.service.SearchService#searchByFirstName(java.lang.String)
	 */
	public SmallUserView[] searchByFirstName(String firstName)
			throws BusinessServiceException {
		SmallUserView[] _arraySmallUserView = searchByFirstName(firstName, true);
		return _arraySmallUserView;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see application.service.SearchService#searchByLastName(java.lang.String)
	 */
	public SmallUserView[] searchByLastName(String lastName)
			throws BusinessServiceException {
		SmallUserView[] _arraySmallUserView = searchByLastName(lastName, true);
		return _arraySmallUserView;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see application.service.SearchService#searchByAffiliation(java.lang.String)
	 */
	public SmallUserView[] searchByAffiliation(String affiliation)
			throws BusinessServiceException {
		return searchByAffiliation(affiliation, true);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see application.service.SearchService#searchRegisterByAffiliation(java.lang.String)
	 */
	public SmallUserView[] searchRegisterByAffiliation(String affiliation)
			throws BusinessServiceException {
		return searchByAffiliation(affiliation, false);
	}

	protected SmallUserView[] searchByAffiliation(String affiliation,
			boolean isAll) throws BusinessServiceException {
		SmallUserView[] _arrayUserView = new SmallUserView[0];
		try {
			Tparticipant[] _arrayParticipant = getParticipantDao()
					.findByAffiliation(affiliation);
			TuserAccount[] _arrayUserAccount = new TuserAccount[0];
			Tcoauthor[] _arrayCoauthor = new Tcoauthor[0];
			Tauthor[] _arrayAuthor = new Tauthor[0];
			if (isAll) {
				_arrayUserAccount = getUserAccountDao().findByAffiliation(
						affiliation);
				_arrayCoauthor = getCoauthorDao()
						.findByAffiliation(affiliation);
				_arrayAuthor = getAuthorDao().findByAffiliation(affiliation);
			}
			_arrayUserView = combineUserView(_arrayParticipant, _arrayAuthor,
					_arrayUserAccount, _arrayCoauthor);

		} catch (DaoException e) {
			logger.error("searchByAffiliation(String, boolean)", e);

			throw new BusinessServiceException(99, e.getMessage());
		} catch (Exception e) {
			logger.error("searchByAffiliation(String, boolean)", e);

			throw new BusinessServiceException(97, e.getMessage());
		}
		return _arrayUserView;
	}


}