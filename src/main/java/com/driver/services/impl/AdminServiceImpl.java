package com.driver.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Admin;
import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.AdminRepository;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository1;

	@Autowired
	DriverRepository driverRepository1;

	@Autowired
	CustomerRepository customerRepository1;

	@Override
	public void adminRegister(Admin admin) {
		//Save the admin in the database
		adminRepository1.save(admin);
	}

	@Override
	public Admin updatePassword(Integer adminId, String password) {
		//Update the password of admin with given id
        Admin admin=adminRepository1.findById(adminId).get();
		admin.setPassword(password);

		Admin savedAdmin=adminRepository1.save(admin);
		return savedAdmin;
	}

	@Override
	public void deleteAdmin(int adminId){
		// Delete admin without using deleteById function
          Admin admin=adminRepository1.findById(adminId).get();
		  adminRepository1.delete(admin);
//		  String name=admin.getUsername();
//		  adminRepository1.deleteByUsername(name);
	}

	@Override
	public List<Driver> getListOfDrivers() {
		//Find the list of all drivers

		List<Driver> drivers=driverRepository1.findAll();
		return drivers;
//         List<Driver> drivers=new ArrayList<>();
//		 for(Driver driver: driverRepository1.findAll()){
//			 drivers.add(driver);
//		 }

	}

	@Override
	public List<Customer> getListOfCustomers() {
		//Find the list of all customers
//		List<Customer> customers=new ArrayList<>();
//		for(Customer customer:customerRepository1.findAll()){
//			customers.add(customer);
//		}
		List<Customer> customers=customerRepository1.findAll();
		return customers;
	}
}
