package com.example.certificatesystem.service;

import com.example.certificatesystem.domain.HouseholdCompositionResidentDto;
import com.example.certificatesystem.domain.HouseholdDto;
import com.example.certificatesystem.domain.HouseholdMovementAddressDto;
import com.example.certificatesystem.entity.Household;
import com.example.certificatesystem.entity.HouseholdMovementAddress;
import com.example.certificatesystem.repository.HouseholdCompositionResidentRepository;
import com.example.certificatesystem.repository.HouseholdMovementAddressRepository;
import com.example.certificatesystem.repository.HouseholdRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HouseholdServiceImpl implements HouseholdService {
    private final HouseholdRepository householdRepository;
    private final HouseholdMovementAddressRepository movementAddressRepository;

    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;

    public HouseholdServiceImpl(HouseholdRepository householdRepository,
                                HouseholdMovementAddressRepository movementAddressRepository,
                                HouseholdCompositionResidentRepository householdCompositionResidentRepository) {
        this.householdRepository = householdRepository;
        this.movementAddressRepository = movementAddressRepository;
        this.householdCompositionResidentRepository = householdCompositionResidentRepository;
    }

    @Override
    public Household saveHousehold(Household household) {

        return householdRepository.save(household);
    }

    @Override
    public Household findHousehold(Integer householdSerialNumber) {
        return householdRepository.findByHouseholdSerialNumber(householdSerialNumber);
    }

    @Override
    public void deleteHousehold(Integer householdSerialNumber) {
        householdRepository.deleteHouseholdByHouseholdSerialNumber(householdSerialNumber);
    }

    @Override
    public HouseholdMovementAddress saveHouseholdMovementAddress(HouseholdMovementAddress householdMovementAddress) {
        return movementAddressRepository.save(householdMovementAddress);
    }

    @Override
    public HouseholdMovementAddress findHouseholdMovementAddress(String houseMovementReportDate,
                                                                 Integer householdSerialNumber) {
        return movementAddressRepository.findByPk_HouseMovementReportDateAndPk_HouseholdSerialNumber(
                houseMovementReportDate, householdSerialNumber);
    }

    @Override
    public void deleteHouseholdMovementAddress(String houseMovementReportDate, Integer householdSerialNumber) {
        movementAddressRepository.deleteHouseholdMovementAddressByPk_HouseMovementReportDateAndPk_HouseholdSerialNumber(
                houseMovementReportDate, householdSerialNumber);
    }

    @Override
    public HouseholdDto getHouseholdDtoByResidentSerialNumber(Integer residentSerialNumber) {
        return householdRepository.getHouseholdDtoByResidentSerialNumber(residentSerialNumber);
    }

    @Override
    public List<HouseholdCompositionResidentDto> getHouseholdCompositionResidentList(Integer residentSerialNumber) {
        return householdCompositionResidentRepository.getHouseholdCompositionResidentList(residentSerialNumber);
    }

    @Override
    public List<HouseholdMovementAddressDto> getHouseholdMovementAddresssDtoList(Integer householdSerialNumber) {
        return movementAddressRepository.getHouseholdMovementAddresssDtoList(householdSerialNumber);
    }
}
