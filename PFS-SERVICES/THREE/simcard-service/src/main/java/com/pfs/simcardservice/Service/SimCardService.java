package com.pfs.simcardservice.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.pfs.simcardservice.DTO.CheckActiveNumber;
import com.pfs.simcardservice.DTO.MessageDTO;
import com.pfs.simcardservice.DTO.MobileNumberDTO;
import com.pfs.simcardservice.DTO.NewSimCardRequest;
import com.pfs.simcardservice.DTO.SimCardActivationRequest;
import com.pfs.simcardservice.DTO.SimCardDTO;
import com.pfs.simcardservice.Entity.SimCard;
import com.pfs.simcardservice.Repository.AvailableMobileNumRepo;
import com.pfs.simcardservice.Repository.SimCardRepository;

@Service
public class SimCardService {
    @Autowired
    private SimCardRepository simCardRepository;

    @Autowired
    private AvailableMobileNumRepo availableMobileNumRepo;

    public ResponseEntity<MessageDTO> activateSimCard(SimCardActivationRequest activationRequest) {
        String simCardNumber = activationRequest.getSimCardNumber();
        Optional<SimCard> simCardOptional = simCardRepository.findBySimCardNumber(simCardNumber);
        MessageDTO messageDTO = new MessageDTO();

        if (simCardOptional.isPresent()) {
            SimCard simCard = simCardOptional.get();
            if (!simCard.isActivated()) {
                simCard.setActivated(true);
                simCardRepository.save(simCard);

                messageDTO.setMessage("SIM card activated successfully.");
                return ResponseEntity.ok(messageDTO);
            } else {
                messageDTO.setMessage("SIM card is already activated.");
                return ResponseEntity.ok(messageDTO);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<MobileNumberDTO> getMobileById(String orderId) {
        Optional<SimCard> simCardOptional = simCardRepository.findByOrderId(orderId);
        MobileNumberDTO mobileNumberDTO = new MobileNumberDTO();
    
        if (simCardOptional.isPresent()) {
            SimCard simCard = simCardOptional.get();
            System.out.println(simCard.getSimCardNumber() + "gauuuu");
            mobileNumberDTO.setMobileNumber(simCard.getSimCardNumber());
            return ResponseEntity.ok(mobileNumberDTO);
        } else {
            String errorMessage = "Mobile not found for order ID: " + orderId;
            mobileNumberDTO.setMessage(errorMessage);
            return ResponseEntity.ok(mobileNumberDTO);
        }
    }    

    public ResponseEntity<SimCardDTO> getSimCardDetails(CheckActiveNumber checkActiveNumber) {
        String simCardNumber = checkActiveNumber.getSimCardNumber();

        Optional<SimCard> simCardOptional = simCardRepository.findBySimCardNumber(simCardNumber);

        if (simCardOptional.isPresent()) {
            SimCard simCard = simCardOptional.get();
            SimCardDTO simCardDTO = mapSimCardToDTO(simCard);

            return ResponseEntity.ok(simCardDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private SimCardDTO mapSimCardToDTO(SimCard simCard) {
        SimCardDTO simCardDTO = new SimCardDTO();
        simCardDTO.setSimCardNumber(simCard.getSimCardNumber());
        simCardDTO.setICCID(simCard.getICCID());
        simCardDTO.setIMSI(simCard.getIMSI());
        simCardDTO.setName(simCard.getName());
        simCardDTO.setAddress(simCard.getAddress());
        simCardDTO.setSimCompanyName(simCard.getSimCompanyName());
        simCardDTO.setDob(simCard.getDob());
        simCardDTO.setAdhaarNumber(simCard.getAdhaarNumber());
        simCardDTO.setActivated(simCard.isActivated());

        return simCardDTO;
    }

    public ResponseEntity<Boolean> isSimCardActivated(CheckActiveNumber checkActiveNumber) {
        String simCardNumber = checkActiveNumber.getSimCardNumber();
        Optional<SimCard> simCardOptional = simCardRepository.findBySimCardNumber(simCardNumber);
        System.out.println(simCardOptional);
        if (simCardOptional.isPresent()) {
            SimCard simCard = simCardOptional.get();
            return ResponseEntity.ok(simCard.isActivated());
        } else {
            return ResponseEntity.ok(false);
        }
    }

    public ResponseEntity<MessageDTO> requestNewSimCard(NewSimCardRequest newSimCardRequest) {
        String simCardNumber = generateNewSimCardNumber();
        String ICCID = generateICCID();
        String IMSI = generateIMSI();
        String orderId = generateOrderId();

        SimCard newSimCard = new SimCard();
        newSimCard.setICCID(ICCID);
        newSimCard.setIMSI(IMSI);
        newSimCard.setSimCardNumber(simCardNumber);
        newSimCard.setName(newSimCardRequest.getName());
        newSimCard.setAddress(newSimCardRequest.getAddress());
        newSimCard.setSimCompanyName(newSimCardRequest.getSimCompanyName());
        newSimCard.setDob(newSimCardRequest.getDob());
        newSimCard.setAdhaarNumber(newSimCardRequest.getAdhaarNumber());
        newSimCard.setOrderId(orderId);

        simCardRepository.save(newSimCard);

        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setMessage("SIM card activated successfully.");
        messageDTO.setOrderId(orderId);
        return ResponseEntity.ok(messageDTO);
    }

    public static String generateICCID() {

        Random rand = new Random();
        StringBuilder iccid = new StringBuilder("8901");

        for (int i = 0; i < 14; i++) {
            iccid.append(rand.nextInt(10));
        }

        return iccid.toString();
    }

    public String generateIMSI() {

        Random rand = new Random();
        StringBuilder imsi = new StringBuilder();

        imsi.append(String.format("%03d", rand.nextInt(1000)));
        imsi.append(String.format("%02d", rand.nextInt(100)));

        for (int i = 0; i < 10; i++) {
            imsi.append(rand.nextInt(10));
        }

        return imsi.toString();
    }

    public String generateOrderId() {
        Random rand = new Random();
        int orderId = rand.nextInt(900) + 100; 
        return String.valueOf(orderId);
    }

    public String generateNewSimCardNumber() {

        List<String> availablePhoneNumbers = availableMobileNumRepo.findAvailableMobileNumbers();

        if (availablePhoneNumbers.isEmpty()) {
            throw new RuntimeException("No available phone numbers in the database.");
        }

        int randomIndex = new Random().nextInt(availablePhoneNumbers.size());
        String newSimCardNumber = availablePhoneNumbers.get(randomIndex);

        return newSimCardNumber;
    }
}
