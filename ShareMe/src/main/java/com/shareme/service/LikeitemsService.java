package com.shareme.service;

import com.shareme.entity.Likeitems;
import com.shareme.entity.LikeitemsPK;
import com.shareme.exception.ErrorCode;
import com.shareme.exception.ThrowException;
import com.shareme.repository.LikeItemsRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LikeitemsService {
    LikeItemsRepository likeitemsRepository;

    public List<LikeitemsPK> getAllLikeItems() {
        List<LikeitemsPK> likeitemsPKS = new ArrayList<>();
        likeitemsRepository.findAll().forEach(e-> likeitemsPKS.add(e.getLikeitemsPK()));
        return likeitemsPKS;
    }

    public List<LikeitemsPK> getLikeItemsByUserId(Integer userId) {
        List<LikeitemsPK> likeitemsPKS = new ArrayList<>();
        likeitemsRepository.findByUserId(userId).forEach(e-> likeitemsPKS.add(e.getLikeitemsPK()));
        return likeitemsPKS;
    }

    public List<LikeitemsPK> getLikeItemsByMenuItemsId(Integer menuItemsId) {
        List<LikeitemsPK> likeitemsPKS = new ArrayList<>();
        likeitemsRepository.findByMenuItemsId(menuItemsId).forEach(e-> likeitemsPKS.add(e.getLikeitemsPK()));
        return likeitemsPKS;
    }

    public Boolean checkLikeitems(LikeitemsPK id) {
        Likeitems likeitems = likeitemsRepository.findById(id).orElseThrow(() -> new ThrowException(ErrorCode.LIKEITEMS_NOTEXITED));
        return likeitems != null;
    }

    public LikeitemsPK createLikeItem(LikeitemsPK likeitemsPK) {
        return likeitemsRepository.save(new Likeitems().builder()
                        .likeitemsPK(likeitemsPK)
                .build()).getLikeitemsPK();
    }

    public Likeitems updateLikeItem(Likeitems likeitems) {
        if (likeitemsRepository.existsById(likeitems.getLikeitemsPK())) {
            return likeitemsRepository.save(likeitems);
        }
        return null;
    }

    public void deleteLikeItem(LikeitemsPK id) {
        likeitemsRepository.deleteById(id);
    }


}
