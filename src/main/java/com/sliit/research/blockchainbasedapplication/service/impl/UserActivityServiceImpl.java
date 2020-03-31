package com.sliit.research.blockchainbasedapplication.service.impl;

import com.sliit.research.blockchainbasedapplication.constants.LogTypes;
import com.sliit.research.blockchainbasedapplication.model.Review;
import com.sliit.research.blockchainbasedapplication.model.User;
import com.sliit.research.blockchainbasedapplication.model.UserActivity;
import com.sliit.research.blockchainbasedapplication.repository.ReviewRepository;
import com.sliit.research.blockchainbasedapplication.repository.UserActivityRepository;
import com.sliit.research.blockchainbasedapplication.service.UserActivityService;
import com.sliit.research.blockchainbasedapplication.utils.HttpUtils;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service("userActivityService")
public class UserActivityServiceImpl implements UserActivityService {

    @Autowired
    UserActivityRepository userActivityRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public boolean isUserReal(User user) throws IOException, ParseException {
        List<UserActivity> userActivities = userActivityRepository.findByUserId(user.getId());
        int loginCount = 0;
        int purchasesCount = 0;
        int positiveCommentsCount = 0;
        int negativeCommentsCount = 0;

        for (UserActivity userActivity : userActivities){
            if (userActivity.getLogType() == LogTypes.LOGIN){
                loginCount++;
            }else if (userActivity.getLogType() == LogTypes.PURCHASE){
                purchasesCount++;
            }else if (userActivity.getLogType() == LogTypes.REVIEW){
                try {
                    Review review = reviewRepository.getOne(userActivity.getReviewId());
                    if ("Positive".equals(review.getSentiment())){
                        positiveCommentsCount++;
                    }else if ("Negative".equals(review.getSentiment())){
                        negativeCommentsCount++;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        String userValid = HttpUtils.sendMLRequest(loginCount, purchasesCount, positiveCommentsCount, negativeCommentsCount);
        if ("1".equals(userValid)){
            return true;
        }else {
            return false;
        }
    }
}
