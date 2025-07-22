package com.osttra.crds.services;

import com.osttra.crds.dtos.ReviewTaskRequestDto;
import com.osttra.crds.dtos.ReviewTaskResponseDto;
import com.osttra.crds.entities.enums.RequestStatus;

public interface ReviewTaskService {
    ReviewTaskResponseDto getReviewTaskById(Long id);
    ReviewTaskResponseDto createReviewTask(ReviewTaskRequestDto reviewTaskRequestDto);
    ReviewTaskResponseDto createReviewTask(ReviewTaskRequestDto reviewTaskRequestDto,String taskId);
    void updateRequestStatus(Long id, RequestStatus requestStatus);
}
