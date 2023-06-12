package com.driver.services.impl;

import com.driver.model.TripBooking;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;
import com.driver.model.TripStatus;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		customerRepository2.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
         Customer customer=customerRepository2.findById(customerId).get();
		 String mobile=customer.getMobile();
		 customerRepository2.deleteByMobile(mobile);
	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE).
		// If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query
		List<Driver> drivers=driverRepository2.findAll();
		int id=Integer.MAX_VALUE;
		Driver d=null;

		for(Driver driver: drivers){
			if(driver.getDriverId()<id && driver.getCab().getAvailable()==true){
				d=driver;
			}
		}
		if(d==null){
			throw new Exception("No cab available!");
		}
		else{
			Customer customer=customerRepository2.findById(customerId).get();
			TripBooking tripBooking=new TripBooking();
			tripBooking.setCustomer(customer);
			tripBooking.setBill(distanceInKm*100);
			tripBooking.setDriver(d);
			tripBooking.setStatus(TripStatus.CONFIRMED);
			tripBooking.setFromLocation(fromLocation);
			tripBooking.setToLocation(toLocation);
			tripBooking.setDistanceInKm(distanceInKm);

			customer.getTripBookingList().add(tripBooking);
			d.getTripBookingList().add(tripBooking);
			customerRepository2.save(customer);
			driverRepository2.save(d);
			tripBookingRepository2.save(tripBooking);

			return tripBooking;
		}
	}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
		TripBooking tripBooking=tripBookingRepository2.findById(tripId).get();
		tripBooking.setStatus(TripStatus.CANCELED);
		customerRepository2.save(tripBooking.getCustomer());
		driverRepository2.save(tripBooking.getDriver());
		tripBookingRepository2.save(tripBooking);
	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
           TripBooking tripBooking=tripBookingRepository2.findById(tripId).get();
		   tripBooking.setStatus(TripStatus.COMPLETED);
		   customerRepository2.save(tripBooking.getCustomer());
		   driverRepository2.save(tripBooking.getDriver());
		   tripBookingRepository2.save(tripBooking);
	}
}
