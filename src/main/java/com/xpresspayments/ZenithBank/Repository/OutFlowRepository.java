package com.xpresspayments.ZenithBank.Repository;

import com.xpresspayments.ZenithBank.model.entity.OutFlow1;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutFlowRepository extends PagingAndSortingRepository<OutFlow1, Long> {

}
