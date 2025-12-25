package com.codegym.customermanagementstoredprocedure.repository;

import com.codegym.customermanagementstoredprocedure.model.Customer;

public interface ICustomerRepository {
    boolean saveWithStoredProcedure(Customer customer);
}