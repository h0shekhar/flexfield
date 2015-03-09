package com.cmcltd.flexfield.data.jpa.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmcltd.flexfield.data.jpa.domain.CustomField;
import com.cmcltd.flexfield.data.jpa.domain.Employee;
import com.cmcltd.flexfield.data.jpa.domain.EntityCustomFieldData;
import com.cmcltd.flexfield.data.jpa.repository.EmployeeRepository;

/**
 * A class to test the interaction with Oracle database using the empDao class.
 *
 * @author CMCLTD
 */

@Controller
public class EmployeeController extends BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(EmployeeController.class);
	// ==============
	// PRIVATE FIELDS
	// ==============

	@Autowired
	private EmployeeRepository empDao;

	// ==============
	// PUBLIC METHODS
	// ==============

	/**
	 * Delete the Employee having the passed id.
	 * 
	 * @param id
	 *            the id of the Employee to delete
	 * @return a string describing if the Employee is successfully deleted or
	 *         not.
	 */
	@RequestMapping(value = "/delete/Employee/Id/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public String delete(@PathVariable Long id) {
		try {
			logger.debug("Employee no passed = " + id.toString());
			Employee employee = empDao.findById(id.intValue());
			Set<EntityCustomFieldData> ecfd = entityCustomDataDao
					.findByTxnId(employee.getId().toString());
			if (ecfd.size() > 0)
				entityCustomDataDao.delete(ecfd);
			empDao.delete(employee);

		} catch (Exception ex) {
			logger.error("Employee not found with Employee No : " + id);
			logger.error("Exception : " + ex.getLocalizedMessage());
			return "Error deleting the Employee:" + ex.toString();
		}

		return "Employee succesfully deleted!";
	}

	/**
	 * Return the Employees .
	 * 
	 * 
	 * @return all the Employees or a message error if the Employee is not
	 *         found.
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/Employees", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Set<Employee> getAll() {
		Set<Employee> employees = null;

		try {
			Iterable<Employee> EmployeeList = empDao.findAll();

			logger.debug("Employees Found : " + EmployeeList);
			for (Employee employee : EmployeeList) {

				logger.debug("Adding Employee :" + employee);
				logger.debug("Adding of Department :" + employee.getDept());

				if (employees == null) {
					employees = new HashSet<Employee>();
					initialize(employee.getEntityName());
				}

				employee.setCustomField(getCustomField());

				employee.setCustomField(getCustomFieldWithValue(
						employee.getCustomField(), employee.getId()));

				employees.add(employee);

				logger.debug("Added.");

			}
			logger.debug("No of Employees found : " + employees.size());
		} catch (Exception ex) {
			logger.debug("Employee not found.");
			logger.error("Exception : " + ex.getLocalizedMessage());
			return employees;
		}
		logger.debug("No of Employees: " + employees.size() + "\n Details: "
				+ employees);
		return employees;
	}

	/**
	 * Return the Employee for the passed name.
	 * 
	 * @param name
	 *            the name to search in the database.
	 * @return the Employees or a message error if the Employee is not found.
	 */

	@RequestMapping(value = "/Employee/Name/{name}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Employee getByName(@PathVariable String name) {
		Employee employee = null;

		try {
			logger.debug("Name passed =" + name);
			employee = empDao.findByEmployeeName(name);
			initialize(employee.getEntityName());

			employee.setCustomField(getCustomField());
			List<CustomField> cf = getCustomFieldWithValue(
					employee.getCustomField(), employee.getId());
			employee.setCustomField(cf);

			logger.debug("Employee found for Employee no" + employee.getId());
			logger.debug("Employee belongs to Dept No : "
					+ employee.getDept().getId());

		} catch (Exception ex) {
			logger.error("Employee Data not found for Employee Name : " + name);
			logger.error("Exception : " + ex.getLocalizedMessage());
			return employee;
		}
		return employee;

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/Employee/Id/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Employee getById(@PathVariable Long id) {
		Employee employee = null;

		logger.debug("ID passed = " + id.toString());
		try {

			employee = empDao.findById(id.intValue());

			initialize(employee.getEntityName());
			employee.setCustomField(getCustomField());
			employee.setCustomField(getCustomFieldWithValue(
					employee.getCustomField(), employee.getId()));

			logger.debug("Employee found for Employee no : " + id.toString());
			logger.debug("Employee belongs to Dept No:"
					+ employee.getDept().getId());

		} catch (Exception ex) {

			logger.error("Employee not found for " + id.toString());
			logger.error("Exception : " + ex.getLocalizedMessage());
			return employee;
		}
		logger.debug("The Employee Name is : " + employee.getEname()
				+ " whose employee no is " + employee.getId());
		return employee;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/Employee/Deptno/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Set<Employee> getByDeptno(@PathVariable Long id) {
		Set<Employee> employees = null;

		logger.debug("Searching employees for Dept No = " + id.toString());
		try {

			Iterable<Employee> EmployeeList = empDao
					.findByDeptno(id.intValue());

			logger.debug("Employees Found : " + EmployeeList);

			for (Employee employee : EmployeeList) {

				logger.debug("Adding Employee :" + employee.getEname()
						+ " Of Department " + employee.getDept());

				if (employees == null) {
					employees = new HashSet<Employee>();
					initialize(employee.getEntityName());
				}
				employee.setCustomField(getCustomField());
				employee.setCustomField(getCustomFieldWithValue(
						employee.getCustomField(), employee.getId()));

				employees.add(employee);
				logger.info("Added.");

			}
			logger.debug("No of Employees found : " + employees.size());

		} catch (Exception ex) {
			logger.error("Employee not found for Dept No " + id.toString());
			logger.error("Exception : " + ex.getLocalizedMessage());
			return employees;
		}
		logger.debug("The Employee Found are : " + employees);
		return employees;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/Employee/Manager/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Set<Employee> getByManager(@PathVariable Long id) {
		Set<Employee> employees = null;

		logger.debug("Manager's Employee ID passed = " + id.toString());
		try {

			Iterable<Employee> EmployeeList = empDao.findByManager(id
					.intValue());

			logger.debug("Employees Found" + EmployeeList);

			for (Employee employee : EmployeeList) {

				logger.info("Adding Employee :" + employee.getEname());
				logger.info("Adding of Department :" + employee.getDept());

				if (employees == null) {
					initialize(employee.getEntityName());
					employees = new HashSet<Employee>();
				}

				employee.setCustomField(getCustomField());
				employee.setCustomField(getCustomFieldWithValue(
						employee.getCustomField(), employee.getId()));
				employees.add(employee);

				logger.trace("Added");

			}
			logger.debug("Total number of Employees found : "
					+ employees.size());

		} catch (Exception ex) {

			logger.error("Exception : " + ex.getLocalizedMessage());
			logger.error("Employee not found for Manager ID : "
					+ id.toString());

			return employees;
		}
		logger.debug("The Employee Found are : " + employees);
		return employees;
	}

} // class EmployeeController
