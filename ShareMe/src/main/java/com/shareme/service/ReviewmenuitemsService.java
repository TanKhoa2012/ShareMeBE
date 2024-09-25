package com.shareme.service;

import com.shareme.dto.request.ReviewmenuitemsRequest;
import com.shareme.dto.response.ReviewmenuitemsResponse;
import com.shareme.entity.Reviewmenuitems;
import com.shareme.mapper.ReviewmenuitemsMapper;
import com.shareme.repository.ReviewmenuitemsRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewmenuitemsService {
    ReviewmenuitemsRepository reviewmenuitemsRepository;
    ReviewmenuitemsMapper reviewmenuitemsMapper;

    public List<ReviewmenuitemsResponse> getAllReviews() {
        return reviewmenuitemsRepository.findAll().stream()
                .map(reviewmenuitemsMapper::toReviewmenuitemsResponse).toList();
    }

    public List<ReviewmenuitemsResponse> getAllReviewsByUserId(Integer userId) {
        return reviewmenuitemsRepository.findByUserId(userId).stream()
                .map(reviewmenuitemsMapper::toReviewmenuitemsResponse).toList();
    }

    public List<ReviewmenuitemsResponse> getAllReviewsByMenuItemId(Integer menuItemId) {
        return reviewmenuitemsRepository.findByUserId(menuItemId).stream()
                .map(reviewmenuitemsMapper::toReviewmenuitemsResponse).toList();
    }

    public ReviewmenuitemsResponse createReview(ReviewmenuitemsRequest request) {
        Reviewmenuitems review = reviewmenuitemsMapper.toEntity(request);
        return reviewmenuitemsMapper.toReviewmenuitemsResponse(reviewmenuitemsRepository.save(review));
    }

    public void deleteReviewById(Integer id) {
        reviewmenuitemsRepository.deleteById(id);
    }


}
