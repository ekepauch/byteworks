package com.bytework.service;


import com.bytework.dto.TransactionRessponse;
import com.bytework.exceptions.ConflictException;
import com.bytework.exceptions.NotFoundException;
import com.bytework.model.Meal;
import com.bytework.model.Payments;
import com.bytework.model.ServiceProvider;
import com.bytework.notificationservice.Mail;
import com.bytework.notificationservice.MailService;
import com.bytework.paystack.PaymentRequest;
import com.bytework.paystack.PaymentResponse;
import com.bytework.paystack.PaymentService;
import com.bytework.repository.CityRepo;
import com.bytework.repository.MealRepo;
import com.bytework.repository.PaymentRepo;
import com.bytework.repository.ServiceProviderRepo;
import com.bytework.utils.Constants;
import com.bytework.utils.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
public class FoodService {


    @Autowired
    private ServiceProviderRepo serviceProviderRepo;

    @Autowired
    private CityRepo cityRepo;

    @Autowired
    private MealRepo mealRepo;

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private MailService mailService;

    @Autowired
    private PaymentService paymentService;




    public List<ServiceProvider> getRestaurant(String city) {
        return serviceProviderRepo.findByCity(city);
    }


    public Optional<ServiceProvider> getRestaurantById(Long id) {
        return serviceProviderRepo.findById(id);
    }


    public ServiceProvider createRestaurant (ServiceProvider serviceProvider){

        ServiceProvider isRestExist = serviceProviderRepo.findByNameAndEmail(serviceProvider.getName(),serviceProvider.getEmail());
        if(isRestExist == null){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION, "restaurant already exist");
        }
        return serviceProviderRepo.save(serviceProvider);
    }



    public Meal createMenu (Meal meal){

        Optional<ServiceProvider> provider = serviceProviderRepo.findById(meal.getProviderId());
            if(provider == null){
                throw new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, "Invalid provider Id");
            }
        return mealRepo.save(meal);
    }


    public List<Meal> getMealsByRestaurant(Long providerId) {
        return mealRepo.findByProviderId(providerId);
    }


    public TransactionRessponse savePayment (Payments payments) throws IOException {

        TransactionRessponse result = new TransactionRessponse();
        ServiceProvider pay = serviceProviderRepo.getOne(payments.getProviderId());
        payments.setDate(new Date());
        payments.setProvider(pay.getName());
        Payments response = paymentRepo.save(payments);
        if(response !=null){
           PaymentRequest paymentRequest = PaymentRequest.builder()
                   .email(payments.getEmail())
                   .amount(payments.getAmount())
                   .build();
           PaymentResponse resp = paymentService.payment(paymentRequest);
           result.setStatus(resp.getStatus());
           result.setMessage(resp.getMessage());
           result.setData(resp.getData());
        }
        try {
            Mail mail = new Mail();
            mail.setMailSubject(Constants.NOTIFICATION);
            mail.setMailTo(payments.getEmail());
            String msg = "Dear " + " " + payments.getName() + "<br/>"+ " Find below your order details" +
                    " Description " + "   " + payments.getDescription()  + "<br/>"+
                    "Amount" + "   "+ payments.getAmount() + "<br/>"+
                    "Email" + "   "+ payments.getEmail() + "<br/>"+
                    "Restaurant" + "   "+ pay.getName() + "<br/>"+
                    "Thank you for dinning with us </a>";
            mail.setDetails(msg);
            Map<String, Object> model = new HashMap<>();
            model.put("details", mail.getDetails());
            mail.setModel(model);
            mailService.sendEmail(mail);
        } catch(Exception ex){
            log.info(":notification Exception: %s",  ex.getMessage());
        }
        return result;
    }

}


