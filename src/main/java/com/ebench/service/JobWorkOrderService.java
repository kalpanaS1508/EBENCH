package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.entity.JobWorkOrder;
import com.ebench.exception.BadReqException;
import com.ebench.repository.JobWorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobWorkOrderService {

    @Autowired
    JobWorkOrderRepository jobWorkOrderRepository;

    public JobWorkOrder createWorkOrder(JobWorkOrder jobWorkOrder) {
        JobWorkOrder jobWorkOrder1 = new JobWorkOrder();

        jobWorkOrder1.setCandidateId(jobWorkOrder.getCandidateId());
        jobWorkOrder1.setVendorId(jobWorkOrder.getVendorId());
        jobWorkOrder1.setBilledTo(jobWorkOrder.getBilledTo());
        jobWorkOrder1.setJobWorkOrderDate(jobWorkOrder.getJobWorkOrderDate());
        jobWorkOrder1.setContractDate(jobWorkOrder.getContractDate());
        jobWorkOrder1.setProjectName(jobWorkOrder.getProjectName());


        JobWorkOrder workOrder = jobWorkOrderRepository.save(jobWorkOrder1);
        return workOrder;

    }

    public JobWorkOrder updateWorkOrder(JobWorkOrder jobWorkOrder) throws RuntimeException {
        Optional<JobWorkOrder> id = jobWorkOrderRepository.findById(jobWorkOrder.getJobWorkOrderId());

        JobWorkOrder order = null;

        if (id.isPresent()) {
            order = id.get();
            order.setJobWorkOrderId(jobWorkOrder.getJobWorkOrderId());
            order.setCandidateId(jobWorkOrder.getCandidateId());
            order.setVendorId(jobWorkOrder.getVendorId());
            order.setProjectName(jobWorkOrder.getProjectName());
            order.setJobWorkOrderDate(jobWorkOrder.getJobWorkOrderDate());
            order.setBilledTo(jobWorkOrder.getBilledTo());
            order.setProjectName(jobWorkOrder.getProjectName());

            JobWorkOrder workOrder = jobWorkOrderRepository.save(order);
            return workOrder;
        } else {
            throw new BadReqException(ApiMessage.WORK_ORDER_IS_NOT_PRESENT);
        }
    }



    public JobWorkOrder deleteWorkOrder(Long jobWorkOrderId) throws RuntimeException {
        jobWorkOrderRepository.deleteById(jobWorkOrderId);
        return null;
    }
}



