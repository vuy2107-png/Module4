package com.codegym.customermanagementstoredprocedure.service;

import com.codegym.customermanagementstoredprocedure.model.Customer;

public interface ICustomerService {
    boolean saveWithStoredProcedure(Customer customer);
}
