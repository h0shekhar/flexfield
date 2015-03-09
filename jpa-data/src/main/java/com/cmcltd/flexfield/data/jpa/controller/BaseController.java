package com.cmcltd.flexfield.data.jpa.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.MappedSuperclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cmcltd.flexfield.data.jpa.domain.CustomField;
import com.cmcltd.flexfield.data.jpa.domain.EntityCustomFieldData;
import com.cmcltd.flexfield.data.jpa.domain.EntityMaster;
import com.cmcltd.flexfield.data.jpa.repository.EntityCustomFieldDataRepository;
import com.cmcltd.flexfield.data.jpa.repository.EntityCustomFieldRepository;
import com.cmcltd.flexfield.data.jpa.repository.EntityMasterRepository;

@Controller
@MappedSuperclass
public class BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(BaseController.class);

	@Autowired
	private EntityMasterRepository entityDao;

	@Autowired
	protected EntityCustomFieldDataRepository entityCustomDataDao;

	@Autowired
	protected EntityCustomFieldRepository entityCustomFieldDao;

	private Integer entityId = null;
	private EntityMaster em = null;
	private List<CustomField> cfl = null;

	protected void initialize(String name) {
		logger.debug("Passed Entity Name < " + name + " >");
		this.em = entityDao.findByName(name);

		if (this.em != null) {
			logger.debug("EntityMaster is < " + em + " >");
			this.entityId = em.getId();
			this.cfl = em.getCustomField();
			for (CustomField cf : cfl) {
				Integer mapId = entityCustomFieldDao.findByEntityidAndFieldid(
						entityId, cf.getId());

				if (mapId != null) {
					cf.setMapId(mapId);
				}

			}

		} else {
			this.entityId = null;
		}
		logger.debug(" Entity Id is < " + entityId + " >");
		logger.debug(" CustomField is < " + this.cfl + " >");

	}

	protected Integer getEntityId() {
		return this.entityId;
	}

	protected EntityMaster getEntityMaster() {
		return this.em;
	}

	protected List<CustomField> getEmCustomField() {
		return this.cfl;
	}

	protected List<CustomField> getCustomField() {
		List<CustomField> cfs = new ArrayList<CustomField>();
		for (CustomField cf : this.cfl) {
			cfs.add(new CustomField(cf));
		}
		return cfs;

	}

	protected void setCustomField(List<CustomField> cflst) {
		
		logger.debug("Customfield passed <"+ cflst +">");
		this.cfl = new ArrayList<CustomField>();
		this.cfl.addAll(cflst);
		logger.debug("Customfield passed is set. ");
	}

	protected List<CustomField> getCustomFieldWithValue(List<CustomField> cfs,
			Integer txnId) {

		logger.debug("Passed Entity Transaction Id < " + txnId + " >");

		List<CustomField> cfsv = new ArrayList<CustomField>();
		if (entityId != null) {

			if (cfs != null) {

				for (CustomField cf : cfs) {

					logger.debug("Finding Custom Field Data for Map Id < "
							+ cf.getMapId() + " > and Transaction Id < "
							+ txnId + " >");

					String value = entityCustomDataDao.findValueByMapAndTxnId(
							cf.getMapId(), txnId.toString());

					if (value != null) {
						logger.debug("CustomField value found is < " + value
								+ " >");
						cf.setValue(value);
					} else {
						cf.setValue(null);
						logger.debug("Field value not found.");
					}

					logger.debug("Custom Field is < " + cf + " >");
					logger.debug("-------------------------");

				}
			}
			cfsv.addAll(cfs);
		}
		logger.debug("Returing Custom Field Details < " + cfsv + " >");
		logger.debug("-------------------------");

		return cfsv;
	}

	public void deleteCustomFieldData(Integer txnId) {

		Set<EntityCustomFieldData> ecfd = entityCustomDataDao.findByTxnId(txnId
				.toString());
		if (ecfd.size() > 0) {
			logger.debug("Deleting Custom field data < " + ecfd
					+ " > found for < " + txnId + " >");
			entityCustomDataDao.delete(ecfd);
		} else {
			logger.debug("No Custom field data found for < " + txnId + " >");
		}
	}

	public void updateCustomFieldData( Integer txnId) {
		logger.debug("Passed Entity Transaction Id < " + txnId + " >");

		if (entityId != null || cfl != null) {

			for (CustomField cf : cfl) {
				EntityCustomFieldData cfd = null;
				
				logger.debug("Finding Custom Field Data for Map Id < "
						+ cf.getMapId() + " > and Transaction Id < " + txnId
						+ " >");

				cfd = entityCustomDataDao.findByMapAndTxnId(cf.getMapId(),
						txnId.toString());

				if (cfd != null) {
					logger.debug("CustomField new value < " + cf.getValue()
							+ " > old value is < " + cfd.getValue() + " >");
					cfd.setValue(cf.getValue());
					entityCustomDataDao.save(cfd);
				} else {
					cfd = new EntityCustomFieldData(cf.getMapId(),
							txnId.toString(), cf.getValue().toString());
					entityCustomDataDao.save(cfd);

					logger.debug("Field value not found so creating new one.");
				}

				logger.debug("Updated Custom Field is < " + cfd + " >");
				logger.debug("-------------------------");
				entityCustomDataDao.save(cfd);

			}
		} else {
			logger.debug("Entity ID and CustomField is not set.");
		}

		logger.debug("-------------------------");

	}

}
