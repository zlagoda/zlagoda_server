package zlagoda.server.company.service;

import javax.management.InvalidAttributeValueException;

import zlagoda.server.company.entity.Check;

public interface CheckService {
    void insertNewCheck(Check check) throws InvalidAttributeValueException;
}
