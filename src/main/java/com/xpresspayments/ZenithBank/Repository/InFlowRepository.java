package com.xpresspayments.ZenithBank.Repository;

import com.xpresspayments.ZenithBank.model.entity.InFlow1;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InFlowRepository extends PagingAndSortingRepository<InFlow1, Long> {
}
